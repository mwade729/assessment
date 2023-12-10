package org.mwade.assessment.services;

import org.junit.jupiter.api.Test;
import org.mwade.assessment.domain.Entry;
import org.mwade.assessment.domain.Outcome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OutcomeServiceTest {

    @Autowired
    OutcomeService outcomeService;

    @Autowired
    EntryService entryService;

    @Test
    public void fromEntriesTest() {
        Entry entry1 = entryService.createEntry(
                "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1".split("\\|")
        );

        Entry entry2 = entryService.createEntry(
                "3ce2d17b-e66a-4c1e-bca3-40eb1c9222c7|2X2D24|Mike Smith|Likes Grape|Drives an SUV|35.0|95.5".split("\\|")
        );

        List<Entry> entries = List.of(entry1, entry2);

        Outcome outcome1 = new Outcome("John Smith", "Rides A Bike", 12.1d);
        Outcome outcome2 = new Outcome("Mike Smith", "Drives an SUV", 95.5d);

        List<Outcome> expected = List.of(outcome1, outcome2);
        List<Outcome> actual = outcomeService.fromEntries(entries);

        assertEquals(expected, actual);
    }

}