package org.mwade.assessment.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EntryServiceTest {

    @Autowired
    EntryService entryService;

    @Test
    public void verifyLineTest() {
        String[] valid = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1".split("\\|");
        String[] tooShort = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2".split("\\|");
        String[] invalidUUID = "18148426-89e1-11ee-b9d1|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1".split("\\|");
        String[] invalidDouble1 = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2a|12.1".split("\\|");
        String[] invalidDouble2 = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1b".split("\\|");

        assertDoesNotThrow(() -> entryService.verifyLine(valid, 0));

        Exception tooShortException = assertThrows(ParseException.class, () -> entryService.verifyLine(tooShort, 0));
        assertEquals(tooShortException.getMessage(), "Line 0: Incorrect number of values.");

        Exception invalidUUIDException = assertThrows(ParseException.class, () -> entryService.verifyLine(invalidUUID, 0));
        assertEquals(invalidUUIDException.getMessage(), "Line 0: UUID parse error.");

        Exception invalidDoubleException1 = assertThrows(ParseException.class, () -> entryService.verifyLine(invalidDouble1, 1));
        assertEquals(invalidDoubleException1.getMessage(), "Line 1: Number parse error.");

        Exception invalidDoubleException2 = assertThrows(ParseException.class, () -> entryService.verifyLine(invalidDouble2, 2));
        assertEquals(invalidDoubleException2.getMessage(), "Line 2: Number parse error.");
    }
}