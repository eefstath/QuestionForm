package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

//Class Employer contains the name and list of tests created by a specific employer.
public class Employer {
    private String name;
    private List<Test> tests;

    public static final Logger logger = LogManager.getLogger(Employer.class);

    public Employer(String name, List<Test> tests) {
        this.name = name;
        this.tests = tests;
        logger.info("An employer has been created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
