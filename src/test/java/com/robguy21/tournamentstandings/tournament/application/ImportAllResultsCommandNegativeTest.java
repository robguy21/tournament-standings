package com.robguy21.tournamentstandings.tournament.application;

import com.robguy21.tournamentstandings.TournamentStandingsApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@TournamentStandingsApplicationTests
public class ImportAllResultsCommandNegativeTest {
    @Autowired
    private ImportAllResultsCommand importAllResultsCommand;


    @Test
    public void testFailsElegantlyWhenFileDoesntExist()
    {
//         @todo
    }

    @Test
    public void testFailsElegantlyWhenFileIsNotInExpectedFormat()
    {
//         @todo
    }

    @Test
    public void testFailsElegantlyWhenStdinIsNotInExpectedFormat()
    {
//         @todo
    }

    @Test
    public void testFailsElegantlyWhenMultipleInputOptionsAreGiven()
    {
//         @todo
    }

}
