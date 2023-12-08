package org.mwade.assessment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Outcome {

    private String name;
    private String transport;
    private Double topSpeed;

    public Outcome fromEntry(Entry entry) {
        return new Outcome(entry.getName(), entry.getTransport(), entry.getTopSpeed());
    }
}
