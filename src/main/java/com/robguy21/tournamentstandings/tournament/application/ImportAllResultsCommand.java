package com.robguy21.tournamentstandings.tournament.application;

import com.robguy21.tournamentstandings.tournament.application.league.services.LeagueImportService;
import com.robguy21.tournamentstandings.tournament.domain.league.LeagueTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.util.concurrent.Callable;

@Component
@CommandLine.Command(name = "import-all-results")
@Slf4j
public class ImportAllResultsCommand implements Callable<Integer> {
    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    @CommandLine.Option(names = { "-f", "--file" }, paramLabel = "Filepath", description = "the path to the file")
    File file;

    @CommandLine.Option(names = { "-o", "--output" }, paramLabel = "OutputFormat", description = "the desired output format - csv, json, or text (default)")
    String outputFormat;

    @CommandLine.Parameters(paramLabel = "results", description = "one or more results in a tournament or league")
    String[] results;

    LeagueImportService leagueImportService;

    ImportAllResultsCommand(
            LeagueImportService leagueImportService
    ) {
        this.outputFormat = "";
        this.leagueImportService = leagueImportService;
    }

//    public static void main(String[] args) {
//        int exitCode = new CommandLine(new ImportAllResultsCommand(this.leagueService)).execute(args);
//        System.exit(exitCode);
//    }

    private Integer shoutAndLeave(String message) {
        System.out.println("---------------");
        System.out.println(String.format("Oops: %s", message));
        System.out.println("---------------");
        return 1;
    }

    @Override
    public Integer call() throws Exception {
        Integer validationCheck = validateOptions();

        if (validationCheck != null) {
            return validationCheck;
        }

        LeagueTable leagueTable = null;

        if (this.file != null && this.file.exists()) {
            leagueTable = this.leagueImportService.getLeagueTableFromFileImport(this.file);
        } else if (this.results != null && this.results.length > 0) {
            leagueTable = this.leagueImportService.getLeagueTableFromTextArrayImport(this.results);
        } else {
            shoutAndLeave("No valid input found.");
        }

        assert leagueTable != null;

        String output;
        switch(this.outputFormat) {
            case "csv" -> output = leagueTable.asCsv();
            case "json" -> output = leagueTable.asPrettyJson();
            default -> output = leagueTable.asText();
        }

        spec.commandLine().getOut().println(output); // @todo use print(output) to remove unnecessary line endings
        return 0;
    }

    private Integer validateOptions() {
        if (file == null && results.length < 1) {
            return shoutAndLeave("Please either use the --file option or list the results as arguments");
        }

        return null;
    }
}