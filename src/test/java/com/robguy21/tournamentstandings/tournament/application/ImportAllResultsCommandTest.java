package com.robguy21.tournamentstandings.tournament.application;

import com.robguy21.tournamentstandings.TournamentStandingsApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TournamentStandingsApplicationTests
class ImportAllResultsCommandTest {
    @Autowired
    private ImportAllResultsCommand importAllResultsCommand;

    @Test
    public void testFixturesFromFileAreShownInCorrectOrder()
    {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        String expectedOutput = """
                1. Tarantulas, 6 pts
                2. Lions, 5 pts
                3. Schalke 04, 3 pts
                4. FC Awesome, 1 pt
                5. Snakes, 1 pt
                6. Bayern Munich, 0 pt
                7. Grouches, 0 pt
                """;

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/league-fixtures-basic.txt");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }

    @Test
    public void testFixturesFromFileWithNumbersInNamesOrderedCorrectly()
    {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);


        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        String expectedOutput = """
                1. FC Schalke 04, 4 pts
                2. 1. FC Nürnberg, 3 pts
                3. 1. FC Union Berlin, 1 pt
                4. Hannover 96, 1 pt
                5. TSG 1899 Hoffenheim, 1 pt
                6. 1. FSV Mainz 05, 0 pt
                """;

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/league-fixtures-names-with-numbers.txt");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }

    @Test
    public void testPremierLeague20_21FixturesFromFileResults()
    {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);


        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        /*
         * ::Warning::
         * Premier League uses Goal Difference as a tie-breaker on positions whereas this application uses
         * Alphabetical ordering to break ties on positions, so no disrespect to Leeds is intended
         * by putting them 10th instead of 9th :)
         */
        String expectedOutput = """
                1. Man City, 86 pts
                2. Man Utd, 74 pts
                3. Liverpool, 69 pts
                4. Chelsea, 67 pts
                5. Leicester, 66 pts
                6. West Ham, 65 pts
                7. Spurs, 62 pts
                8. Arsenal, 61 pts
                9. Everton, 59 pts
                10. Leeds, 59 pts
                11. Aston Villa, 55 pts
                12. Newcastle, 45 pts
                13. Wolves, 45 pts
                14. Crystal Palace, 44 pts
                15. Southampton, 43 pts
                16. Brighton, 41 pts
                17. Burnley, 39 pts
                18. Fulham, 28 pts
                19. West Brom, 26 pts
                20. Sheffield Utd, 23 pts
                """;

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/premier-league-fixture-results.txt");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }
    @Test
    public void testPremierLeague20_21FixturesFromStdinResults() throws IOException {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);


        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        Path path = Paths.get("./src/test/resources/premier-league-fixture-results.txt");
        List<String> readList = Files.readAllLines(path);
        String read = String.join("\n", readList);
        /*
         * ::Warning::
         * Premier League uses Goal Difference as a tie-breaker on positions whereas this application uses
         * Alphabetical ordering to break ties on positions, so no disrespect to Leeds is intended
         * by putting them 10th instead of 9th :)
         */
        String expectedOutput = """
                1. Man City, 86 pts
                2. Man Utd, 74 pts
                3. Liverpool, 69 pts
                4. Chelsea, 67 pts
                5. Leicester, 66 pts
                6. West Ham, 65 pts
                7. Spurs, 62 pts
                8. Arsenal, 61 pts
                9. Everton, 59 pts
                10. Leeds, 59 pts
                11. Aston Villa, 55 pts
                12. Newcastle, 45 pts
                13. Wolves, 45 pts
                14. Crystal Palace, 44 pts
                15. Southampton, 43 pts
                16. Brighton, 41 pts
                17. Burnley, 39 pts
                18. Fulham, 28 pts
                19. West Brom, 26 pts
                20. Sheffield Utd, 23 pts
                """;

        // act
        int exitCode = cmd.execute(read);

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }
}