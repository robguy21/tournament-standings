package com.robguy21.tournamentstandings.tournament.application;

import com.robguy21.tournamentstandings.TournamentStandingsApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import picocli.CommandLine;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TournamentStandingsApplicationTests
public class ImportAllResultsCommandOutputFormatTest {
    @Autowired
    ImportAllResultsCommand importAllResultsCommand;

    @Test
    public void testSimpleOutputShowsAsPrettyJson() {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        String expectedOutput = "[ {\n" +
                "  \"position\" : 1,\n" +
                "  \"name\" : \"Team A\",\n" +
                "  \"points\" : 4\n" +
                "}, {\n" +
                "  \"position\" : 2,\n" +
                "  \"name\" : \"Team B\",\n" +
                "  \"points\" : 1\n" +
                "}, {\n" +
                "  \"position\" : 3,\n" +
                "  \"name\" : \"Team C\",\n" +
                "  \"points\" : 0\n" +
                "} ]\n";

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/league-fixtures-small.txt", "-o=json");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }

    @Test
    public void testSimpleOutputShowsAsCsv() {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        String expectedOutput = "position,name,points\n" +
                "1,\"Team A\",4\n" +
                "2,\"Team B\",1\n" +
                "3,\"Team C\",0\n\n"; // @todo unnecessary \n ending

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/league-fixtures-small.txt", "-o=csv");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }

    @Test
    public void testSimpleOutputShowsAsText() {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        String expectedOutput = "1. Team A, 4 pts\n" +
                "2. Team B, 1 pt\n" +
                "3. Team C, 0 pt\n";

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/league-fixtures-small.txt", "-o=text");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }
    @Test
    public void testSimpleOutputShowsAsTextByDefault() {
        // arrange
        CommandLine cmd = new CommandLine(this.importAllResultsCommand);

        StringWriter sw = new StringWriter();
        cmd.setOut(new PrintWriter(sw));

        String expectedOutput = "1. Team A, 4 pts\n" +
                "2. Team B, 1 pt\n" +
                "3. Team C, 0 pt\n";

        // act
        int exitCode = cmd.execute("-f=./src/test/resources/league-fixtures-small.txt");

        // assert
        assertEquals(0, exitCode);
        assertEquals(expectedOutput, sw.toString());
    }
}
