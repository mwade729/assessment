package org.mwade.assessment.services;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mwade.assessment.domain.Entry;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EntryService {

    public List<Entry> handleFile(MultipartFile file) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(reader);
            List<Entry> entries = new ArrayList<>();
            String next;
            while ((next = lineNumberReader.readLine()) != null) {
                System.out.println("line number " + lineNumberReader.getLineNumber() + " = " + next);
                entries.add(createEntry(next.split("\\|")));
            }
            return entries;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyLine(String[] line, int lineNumber) throws ParseException {
        if (line.length != 7) {
            throw new ParseException("Incorrect number of values.", lineNumber);
        }
        try {
            UUID.fromString(line[0]);
        } catch (IllegalArgumentException e) {
            throw new ParseException("UUID parse error.", lineNumber);
        }
        try {
            Double.parseDouble(line[5]);
            Double.parseDouble(line[6]);
        } catch (NumberFormatException e) {
            throw new ParseException("Number parse error.", lineNumber);
        }
    }
    
    public Entry createEntry(String[] line) {
        return Entry.builder()
                .uuid(UUID.fromString(line[0]))
                .id(line[1])
                .name(line[2])
                .likes(line[3])
                .transport(line[4])
                .avgSpeed(Double.parseDouble(line[5]))
                .topSpeed(Double.parseDouble(line[6]))
                .build();
    }
}
