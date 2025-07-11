package com.solvd.models.countWords;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

public class CountWords {

    private static final Logger logger = LogManager.getLogger(CountWords.class);

    public static void main(String[] args) {
        File outputFile = new File("output-counts.txt");

        try (InputStream inputStream = CountWords.class
                .getClassLoader()
                .getResourceAsStream("com/solvd/models/countWords/input-article.txt")) {

            if (inputStream == null) {
                throw new FileNotFoundException("Input file not found");
            }

            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            content = content.replaceAll("[^а-яёА-ЯЁ\\s]", " ");
            content = content.toLowerCase(Locale.forLanguageTag("ru"));

            String[] words = StringUtils.split(content);
            Map<String, Integer> wordCountMap = new TreeMap<>();
            for (String word : words) {
                if (StringUtils.isNotBlank(word)) {
                    if (wordCountMap.containsKey(word)) {
                        int count = wordCountMap.get(word);
                        wordCountMap.put(word, count + 1);
                    } else {
                        wordCountMap.put(word, 1);
                    }
                }
            }

            StringBuilder result = new StringBuilder();
            result.append("---- Word Summary (").append(LocalDateTime.now()).append(") ----\n");
            for (Map.Entry<String, Integer> entry : wordCountMap.entrySet()) {
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            result.append("\n");

            if (!outputFile.exists()) {
                outputFile.createNewFile();
                logger.info("Created output file: {}", outputFile.getAbsolutePath());
            }

            FileUtils.writeStringToFile(outputFile, result.toString(), StandardCharsets.UTF_8, true);
            logger.info("Successfully wrote results to output file.");

        } catch (IOException e) {
            logger.error("File error: {}", e.getMessage());
        }
    }
}