package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Employer {
    private String name;
    private ArrayList<Test> tests;

    public static final Logger logger = LogManager.getLogger(Employer.class);

    public Employer(String name, ArrayList<Test> tests) {
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

    public ArrayList<Test> getTests() {
        return tests;
    }

    public void setTests(ArrayList<Test> tests) {
        this.tests = tests;
    }
}
