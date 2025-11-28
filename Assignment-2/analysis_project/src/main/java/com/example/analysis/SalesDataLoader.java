package com.example.analysis;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class SalesDataLoader {
    public static List<SalesRecord> loadFromCsv(String filePath) throws IOException {
        Path path = Path.of(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.skip(1)//skipping the headers
                    .filter(l -> !l.isBlank()) // this removes empty lines
                    .map(SalesDataLoader::parseLine)//converting each line to a sales record object
                    .collect(Collectors.toList());//adding all the sales record to the list
        }
    }

    private static SalesRecord parseLine(String line) {
        String[] p = line.split(",");
        return new SalesRecord(
                LocalDate.parse(p[1].trim()),
                p[2].trim(),
                p[3].trim(),
                Integer.parseInt(p[5].trim()),
                Double.parseDouble(p[6].trim())
        );
    }
}
