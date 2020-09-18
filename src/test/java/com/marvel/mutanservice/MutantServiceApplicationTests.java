package com.marvel.mutanservice;

import com.marvel.mutanservice.configuration.FirestoreConfigurationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {MutantServiceApplication.class, FirestoreConfigurationTest.class})
class MutantServiceApplicationTests {



}
