package com.robguy21.tournamentstandings.tournament.application.league.dtos;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class FixtureDto {

    private TeamDto homeTeam;

    private TeamDto awayTeam;

    private List<FixtureEventDto> fixtureEvents;

    public void addFixtureEvent(FixtureEventDto fixtureEventDto) {
        if (fixtureEvents == null) {
            this.fixtureEvents = new ArrayList<>();;
        }
        fixtureEvents.add(fixtureEventDto);
    }
}
