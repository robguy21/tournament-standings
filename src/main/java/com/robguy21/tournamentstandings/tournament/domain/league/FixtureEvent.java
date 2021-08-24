package com.robguy21.tournamentstandings.tournament.domain.league;

import com.robguy21.tournamentstandings.tournament.core.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class FixtureEvent extends AbstractEntity {
    private Team team;

    @Enumerated(value = EnumType.STRING)
    private Action action;

    private OffsetDateTime time;

    // @todo Make this an object that can handle different types of actions (send-off / event_cancelled / etc)
    private int fixtureAffect;
}
