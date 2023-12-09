package org.mwade.assessment.domain;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Entry {

    private UUID uuid;
    private String id;
    private String name;
    private String likes;
    private String transport;
    private Double avgSpeed;
    private Double topSpeed;

}
