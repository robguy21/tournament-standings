package com.robguy21.tournamentstandings.tournament.domain.league;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.robguy21.tournamentstandings.tournament.core.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class LeagueTable extends AbstractEntity {
    private List<LeagueTableStanding> standings;

    public void addResult(Team team, FixtureResultState state) {
        if (this.standings == null) {
            this.standings = new ArrayList<>();
        }
        LeagueTableStanding leagueTableRow;
        Optional<LeagueTableStanding> leagueTableRowOptional = standings.stream()
                .filter(standing -> Objects.equals(standing.getTeam().getName(), team.getName()))
                .findFirst();
        if (leagueTableRowOptional.isPresent()) {
            leagueTableRow = leagueTableRowOptional.get();
        } else {
            // create new row if no team was found
            leagueTableRow = new LeagueTableStanding();
            leagueTableRow.setTeam(team);
            standings.add(leagueTableRow);
        }
        Integer points = PointRules.pointsFromState(state);
        leagueTableRow.addPoints(points);
    }
    
    public List<LeagueTableStanding> getStandings() {
        AtomicReference<Integer> currentPosition = new AtomicReference<>(0);
        return this.standings.stream()
                .sorted(Comparator.comparingInt(LeagueTableStanding::getPoints).reversed().thenComparing(LeagueTableStanding::getTeamName))
                .map(leagueTableRow -> {
                    currentPosition.updateAndGet(v -> v + 1);

                    leagueTableRow.setPosition(currentPosition.get());
                    return leagueTableRow;
                })
                .collect(Collectors.toList());
    }


    public String asPrettyJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<LeagueTableStanding> standings = this.getStandings();

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(standings);
    }

    public String asCsv() throws JsonProcessingException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.schemaFor(LeagueTableStanding.class).withHeader();

        List<LeagueTableStanding> standings = this.getStandings();

        return csvMapper
                .writer(csvSchema)
                .writeValueAsString(standings);
    }

    public String asText() {
        return this.getStandings()
                .stream()
                .map(standing -> {
                    Integer points = standing.getPoints();
                    String pointPrefix = "pt";
                    if (points > 1) {
                        pointPrefix += "s";
                    }
                    return String.format("%d. %s, %d %s", standing.getPosition(), standing.getTeam().getName(), points, pointPrefix);
                })
                .collect(Collectors.joining("\n"));
    }
}
