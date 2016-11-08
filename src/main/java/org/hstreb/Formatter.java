package org.hstreb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Formatter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String format(String name) {
        logger.info("Format {}", name);
        return " " + name + " ";
    }
}
