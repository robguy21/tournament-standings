package com.robguy21.tournamentstandings.tournament.application.league.dtos;

import com.robguy21.tournamentstandings.tournament.domain.league.Action;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class FixtureEventDto {
    private TeamDto team;

    private Integer score;

    @Enumerated(value = EnumType.STRING)
    private Action action;

    /**
     * @note Only support SCORE_UPDATE actions for now
     * @return
     */
    public Action getAction() {
        return Action.SCORE_UPDATE;
    }
}
