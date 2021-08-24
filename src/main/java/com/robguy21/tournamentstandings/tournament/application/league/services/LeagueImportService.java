package com.robguy21.tournamentstandings.tournament.application.league.services;

import com.robguy21.tournamentstandings.tournament.domain.league.LeagueTable;
import com.robguy21.tournamentstandings.tournament.domain.league.LeagueTableStanding;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public interface LeagueImportService {
    public LeagueTable getLeagueTableFromFileImport(File file) throws IOException;

    public LeagueTable getLeagueTableFromTextArrayImport(String[] textArray);
}
