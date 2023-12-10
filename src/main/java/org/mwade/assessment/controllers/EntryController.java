package org.mwade.assessment.controllers;

import org.mwade.assessment.domain.Entry;
import org.mwade.assessment.domain.Outcome;
import org.mwade.assessment.services.EntryService;
import org.mwade.assessment.services.OutcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class EntryController {

    @Autowired
    EntryService entryService;

    @Autowired
    OutcomeService outcomeService;

    @GetMapping("/hello")
    public String hello() {
        return "hi";
    }

    @PostMapping("/process")
    public List<Outcome> processFile(@RequestParam("file") MultipartFile entryFile) throws IOException {
        List<Entry> entries = entryService.handleFile(entryFile);
        return outcomeService.fromEntries(entries);
    }
}
