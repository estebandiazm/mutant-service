package com.marvel.mutanservice.configuration;

import com.google.cloud.firestore.Firestore;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Profile("test")
@TestConfiguration
public class FirestoreConfigurationTest {

    @Bean
    @Primary
    Firestore firestore() {
        return mock(Firestore.class);
    }

}