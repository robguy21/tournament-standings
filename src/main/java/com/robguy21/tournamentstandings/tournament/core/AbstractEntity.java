package com.robguy21.tournamentstandings.tournament.core;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AbstractEntity {
    @Id
    protected UUID id;

    @CreatedDate
    protected OffsetDateTime createdDate;

    protected boolean deleted = false;
}
