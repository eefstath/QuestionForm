package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Section {
    private String name;

    private static final Logger logger = LogManager.getLogger(Section.class);

    public Section(String name) {
        this.name = name;
        logger.info("A section has been created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
