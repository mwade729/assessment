package org.mwade.assessment.services;

import org.mwade.assessment.domain.Entry;
import org.mwade.assessment.domain.Outcome;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OutcomeService {

    public List<Outcome> fromEntries(List<Entry> entries) {
        return entries.stream().map(Outcome::fromEntry).collect(Collectors.toList());
    }
}
