package com.robguy21.tournamentstandings.tournament.domain.league;

import com.robguy21.tournamentstandings.tournament.core.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Slf4j
public class Fixture extends AbstractEntity
{

    // let's use "home" and "away" to distinguish our teams,
    private Team homeTeam;

    private Team awayTeam;

    // let
    private List<FixtureEvent> events;

    /**
     * Different Tournaments and Leagues will have different rule sets to define a winner
     * ie: Premier League a winner is simply the one with the most goals
     * ie: Champions League a winner in the knockout stages is the one with the most goals across two legs
     *
     * This Entity doesn't care about that though- since from what I can see, in "league" matches
     * The winner is also considered in isolation in the league rounds
     * counter example: NFL has a knockout tournament at the end of the league to decide who wins the league
     *
     * To solve this issue in future - have a service for each tournament format...
     *  ie: premier league extending league rules
     *  ie: champions league extending 2-legged ties extended knockout format
     *  which consider the special logic involved for that situation
     */
    public Team getWinningTeam() {
        // go through this.FixtureEvents and filter by Action.SCORE_UPDATE

        List<FixtureEvent> resultImpactingEvents = events.stream()
                .filter(event -> event.getAction().equals(Action.SCORE_UPDATE))
                .collect(Collectors.toList());

        AtomicInteger homeScore = new AtomicInteger();
        AtomicInteger awayScore = new AtomicInteger();

        resultImpactingEvents.forEach(event -> {
           if (event.getTeam().equals(this.homeTeam)) {
               homeScore.addAndGet(event.getFixtureAffect());
           } else if (event.getTeam().equals(this.awayTeam)) {
               awayScore.addAndGet(event.getFixtureAffect());
           }
        });

        if (homeScore.get() > awayScore.get()) {
            return homeTeam;
        } else if (awayScore.get() > homeScore.get()) {
            return awayTeam;
        }
        return null;
    }

    public FixtureResultState getFixtureResultStateForTeam(Team team) {
        Team winners = this.getWinningTeam();

        if (winners == null) {
            return FixtureResultState.DRAW;
        }

        if (winners.equals(team)) {
            return FixtureResultState.WIN;
        }

        return FixtureResultState.LOSS;
    }
}
