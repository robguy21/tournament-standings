package com.robguy21.tournamentstandings.tournament.domain.league;

import java.util.List;
import java.util.UUID;

public class Leagues {
    public static League importAllFixturesForNewLeague(List<Fixture> fixtureList) {
        League.LeagueBuilder league = League.builder()
                .name(UUID.randomUUID().toString())
                .fixtures(fixtureList);

        return league.build();
    }
}
