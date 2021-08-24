package com.robguy21.tournamentstandings.tournament.domain.league;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "position",
        "name",
        "points"
})
public class LeagueTableStanding {
    @JsonIgnore
    private Team team;

    @JsonProperty("points")
    private Integer points;

    @JsonProperty("position")
    private Integer position;

    @JsonProperty("name")
    public String getTeamName() {
        return this.team.getName();
    }

    public void addPoints(Integer points) {
        if (this.points == null) {
            this.points = 0;
        }

        this.points += points;
    }

}
