package org.mwade.assessment.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Entry {

    private UUID uuid;
    private String Id;
    private String name;
    private String likes;
    private String transport;
    private Double avgSpeed;
    private Double topSpeed;

}
