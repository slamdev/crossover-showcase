package com.dev.frontend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

    public static Double parseDouble(String value) {
        if (value == null || value.isEmpty()) {
            return 0D;
        }
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0D;
        }
    }
}
