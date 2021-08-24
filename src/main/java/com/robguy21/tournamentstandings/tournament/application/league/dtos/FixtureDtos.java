package com.robguy21.tournamentstandings.tournament.application.league.dtos;

import com.robguy21.tournamentstandings.tournament.domain.league.Fixture;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FixtureDtos {
    // @todo not super happy with the number of parameters
    public static FixtureDto from(String homeTeamName, String awayTeamName, Integer homeTeamScore, Integer awayTeamScore) {
        FixtureDto fixture = new FixtureDto();

        TeamDto homeTeam = new TeamDto();
        homeTeam.setName(homeTeamName);
        fixture.setHomeTeam(homeTeam);

        TeamDto awayTeam = new TeamDto();
        awayTeam.setName(awayTeamName);
        fixture.setAwayTeam(awayTeam);

        FixtureEventDto homeTeamEvent = new FixtureEventDto();
        homeTeamEvent.setTeam(homeTeam);
        homeTeamEvent.setScore(homeTeamScore);
        fixture.addFixtureEvent(homeTeamEvent);

        FixtureEventDto awayTeamEvent = new FixtureEventDto();
        awayTeamEvent.setTeam(awayTeam);
        awayTeamEvent.setScore(awayTeamScore);
        fixture.addFixtureEvent(awayTeamEvent);

        return fixture;
    }

    public static List<Fixture> toList(List<FixtureDto> fixtureDtoList) {
        return fixtureDtoList.stream()
                .map(FixtureDtos::to)
                .collect(Collectors.toList());
    }

    public static Fixture to(FixtureDto fixtureDto) {
        Fixture.FixtureBuilder fixture = Fixture.builder()
                .awayTeam(TeamDtos.to(fixtureDto.getAwayTeam()))
                .homeTeam(TeamDtos.to(fixtureDto.getHomeTeam()))
                .events(FixtureEventDtos.toList(fixtureDto.getFixtureEvents()));
        return fixture.build();
    }
}
