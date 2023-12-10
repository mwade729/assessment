package org.mwade.assessment.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Outcome implements Serializable {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Transport")
    private String transport;

    @JsonProperty("Top Speed")
    private Double topSpeed;

    public static Outcome fromEntry(Entry entry) {
        return new Outcome(entry.getName(), entry.getTransport(), entry.getTopSpeed());
    }
}
