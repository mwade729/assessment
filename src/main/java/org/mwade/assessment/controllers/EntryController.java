package org.mwade.assessment.controllers;

import org.mwade.assessment.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class EntryController {

    @Autowired
    EntryService entryService;

    @GetMapping("/hello")
    public String hello() {
        return "hi";
    }

    @PostMapping("/process")
    public String processFile(@RequestParam("file") MultipartFile entryFile) throws IOException {
        entryService.handleFile(entryFile);
        return "okay";
    }
}
