package org.mwade.assessment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mwade.assessment.domain.Entry;
import org.mwade.assessment.domain.Outcome;
import org.mwade.assessment.services.EntryService;
import org.mwade.assessment.services.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
public class EntryController {

    EntryService entryService;

    OutcomeService outcomeService;

    @Autowired
    public EntryController(EntryService entryService, OutcomeService outcomeService) {
        this.entryService = entryService;
        this.outcomeService = outcomeService;
    }

    @PostMapping("/process")
    public ResponseEntity processFile(@RequestParam("file") MultipartFile entryFile, @RequestParam("validate") boolean validate) {
        try {
            List<Entry> entries = entryService.handleFile(entryFile, validate);
            List<Outcome> outcomes = outcomeService.fromEntries(entries);
            ObjectMapper mapper = new ObjectMapper();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mapper.writeValue(outputStream, outcomes);
            ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"OutcomeFile.json\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
