package com.robguy21.tournamentstandings.tournament.application.league.services;

import com.robguy21.tournamentstandings.tournament.application.league.dtos.FixtureDto;
import com.robguy21.tournamentstandings.tournament.application.league.dtos.FixtureDtos;
import com.robguy21.tournamentstandings.tournament.domain.league.League;
import com.robguy21.tournamentstandings.tournament.domain.league.LeagueTable;
import com.robguy21.tournamentstandings.tournament.domain.league.LeagueTableStanding;
import com.robguy21.tournamentstandings.tournament.domain.league.Leagues;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueImportServiceImpl implements LeagueImportService {
    @Override
    public LeagueTable getLeagueTableFromFileImport(File file) throws IOException {
        List<String> input = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        List<FixtureDto> fixtures = this.listToFixtureDtos(input);

        League league = this.saveLeague(fixtures);
        return league.getLeagueTable();
    }

    @Override
    public LeagueTable getLeagueTableFromTextArrayImport(String[] textArray) {
        List<String> input = List.of(textArray[0].split("\\r?\\n"));
        List<FixtureDto> fixtures = this.listToFixtureDtos(input);

        League league = this.saveLeague(fixtures);
        return league.getLeagueTable();
    }

    private League saveLeague(List<FixtureDto> fixtures) {
        return Leagues.importAllFixturesForNewLeague(FixtureDtos.toList(fixtures));
    }

    private List<FixtureDto> listToFixtureDtos(List<String> list) {
        return list.stream().map(this::lineToFixtureDto).collect(Collectors.toList());
    }

    private FixtureDto lineToFixtureDto(String line) {
        String[] sections = line.split(",");

        String home = sections[0];
        String away = sections[1];

        String homeTeamName = getNameFromString(home);
        Integer homeTeamScore = getScoreFromString(home);

        String awayTeamName = getNameFromString(away);
        Integer awayTeamScore = getScoreFromString(away);
        return FixtureDtos.from(homeTeamName, awayTeamName, homeTeamScore, awayTeamScore);
    }

    private String getNameFromString(String stringWithName) {
        return stringWithName.trim().substring(0, stringWithName.lastIndexOf(" ")).trim();
    }

    private Integer getScoreFromString(String stringWithScore) {

        String score = stringWithScore.trim().substring(stringWithScore.lastIndexOf(" "));
        return Integer.valueOf(score.trim());
    }
}
