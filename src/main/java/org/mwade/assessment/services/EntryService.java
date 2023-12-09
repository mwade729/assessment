package org.mwade.assessment.services;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
public class EntryService {

    public void handleFile(MultipartFile file) {
        try {
            InputStreamReader reader = new InputStreamReader(file.getInputStream());
            LineNumberReader lineNumberReader = new LineNumberReader(reader);
            String next;
            while ((next = lineNumberReader.readLine()) != null) {
                System.out.println("line number " + lineNumberReader.getLineNumber() + " = " + next);
            }
        } catch (IOException e) {

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
}
