package com.robguy21.tournamentstandings.tournament.domain.league;

import com.robguy21.tournamentstandings.tournament.core.AbstractEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Team extends AbstractEntity {
    private String name;

    // later on we can add the concept of a team has many players
    // private List<Player> players;
}
