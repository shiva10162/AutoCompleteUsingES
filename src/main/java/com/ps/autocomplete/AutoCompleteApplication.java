package com.ps.autocomplete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class AutoCompleteApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoCompleteApplication.class, args);
    }

}
