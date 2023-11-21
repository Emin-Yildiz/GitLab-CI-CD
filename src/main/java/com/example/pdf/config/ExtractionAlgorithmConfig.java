package com.example.pdf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

@Configuration
public class ExtractionAlgorithmConfig {

    @Bean
    public SpreadsheetExtractionAlgorithm spreadsheetExtractionAlgorithm(){
        return new SpreadsheetExtractionAlgorithm();
    }
}
