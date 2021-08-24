package com.robguy21.tournamentstandings.tournament.domain.league;

import com.robguy21.tournamentstandings.tournament.core.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class League extends AbstractEntity {
    private String name;

    private List<Fixture> fixtures;

    public LeagueTable getLeagueTable() {
        LeagueTable leagueTable = new LeagueTable();
        this.fixtures.forEach(fixture -> {
            leagueTable.addResult(fixture.getHomeTeam(), fixture.getFixtureResultStateForTeam(fixture.getHomeTeam()));
            leagueTable.addResult(fixture.getAwayTeam(), fixture.getFixtureResultStateForTeam(fixture.getAwayTeam()));
        });

        return leagueTable;
    }
}
