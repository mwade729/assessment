package org.mwade.assessment.services;

import org.junit.jupiter.api.Test;
import org.mwade.assessment.domain.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EntryServiceTest {

    @Autowired
    EntryService entryService;

    String[] valid = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1".split("\\|");

    @Test
    public void verifyLineTest() {
        String[] tooShort = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2".split("\\|");
        String[] invalidUUID = "18148426-89e1-11ee-b9d1|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1".split("\\|");
        String[] invalidDouble1 = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2a|12.1".split("\\|");
        String[] invalidDouble2 = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1b".split("\\|");

        assertDoesNotThrow(() -> entryService.verifyLine(valid, 0));

        Exception tooShortException = assertThrows(ParseException.class, () -> entryService.verifyLine(tooShort, 0));
        assertEquals("Line 0: Incorrect number of values.", tooShortException.getMessage());

        Exception invalidUUIDException = assertThrows(ParseException.class, () -> entryService.verifyLine(invalidUUID, 0));
        assertEquals("Line 0: UUID parse error.", invalidUUIDException.getMessage());

        Exception invalidDoubleException1 = assertThrows(ParseException.class, () -> entryService.verifyLine(invalidDouble1, 1));
        assertEquals("Line 1: Number parse error.", invalidDoubleException1.getMessage());

        Exception invalidDoubleException2 = assertThrows(ParseException.class, () -> entryService.verifyLine(invalidDouble2, 2));
        assertEquals("Line 2: Number parse error.", invalidDoubleException2.getMessage());
    }

    @Test
    public void createEntryTest() {
        Entry expected = Entry.builder()
                .uuid(UUID.fromString("18148426-89e1-11ee-b9d1-0242ac120002"))
                .id("1X1D14")
                .name("John Smith")
                .likes("Likes Apricots")
                .transport("Rides A Bike")
                .avgSpeed(6.2d)
                .topSpeed(12.1d)
                .build();

        Entry actual = entryService.createEntry(valid);

        assertEquals(expected, actual);
    }
}