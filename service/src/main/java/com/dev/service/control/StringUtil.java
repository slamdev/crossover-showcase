package com.dev.service.control;

import static java.util.Arrays.stream;

public final class StringUtil {

    public static String firstNotEmpty(String... strings) {
        return stream(strings).filter(s -> s != null && !s.isEmpty()).findFirst().orElse("");
    }

    private StringUtil() {
        // Util class should not be instantiated directly
    }
}
