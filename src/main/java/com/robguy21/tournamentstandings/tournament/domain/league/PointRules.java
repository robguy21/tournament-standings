package com.robguy21.tournamentstandings.tournament.domain.league;

public class PointRules {
    public static Integer pointsFromState(FixtureResultState state) {
        return switch (state) {
            case WIN -> 3;
            case DRAW -> 1;
            case LOSS -> 0;
        };
    }
}
