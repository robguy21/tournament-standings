package com.robguy21.tournamentstandings.tournament.application.league.dtos;

import com.robguy21.tournamentstandings.tournament.domain.league.Team;

public class TeamDtos {
    public static Team to(TeamDto teamDto) {
        Team.TeamBuilder team = Team.builder()
                .name(teamDto.getName());

        return team.build();
    }
}
