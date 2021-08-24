package com.robguy21.tournamentstandings.tournament.application.league.dtos;

import com.robguy21.tournamentstandings.tournament.domain.league.FixtureEvent;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FixtureEventDtos {
    public static FixtureEvent to(FixtureEventDto fixtureEventDto) {
        FixtureEvent.FixtureEventBuilder fixtureEvent = FixtureEvent.builder();
        fixtureEvent.team(TeamDtos.to(fixtureEventDto.getTeam()))
                .fixtureAffect(fixtureEventDto.getScore())
                .time(OffsetDateTime.now())
                .action(fixtureEventDto.getAction());

        return fixtureEvent.build();
    }

    public static List<FixtureEvent> toList(List<FixtureEventDto> fixtureEventDtoList) {
        return fixtureEventDtoList.stream()
                .map(FixtureEventDtos::to)
                .collect(Collectors.toList());
    }
}
