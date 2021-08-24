package com.robguy21.tournamentstandings;

import com.robguy21.tournamentstandings.tournament.application.ImportAllResultsCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import picocli.CommandLine;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TournamentStandingsApplication implements CommandLineRunner {

    private final ImportAllResultsCommand importAllResultsCommand;

    public TournamentStandingsApplication(ImportAllResultsCommand importAllResultsCommand) {
        this.importAllResultsCommand = importAllResultsCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(TournamentStandingsApplication.class, args);
    }

    @Override
    public void run(String... args) {
        CommandLine commandLine = new CommandLine(importAllResultsCommand);
        commandLine.execute(args);
    }
}
