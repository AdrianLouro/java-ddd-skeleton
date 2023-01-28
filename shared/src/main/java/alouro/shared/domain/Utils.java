package alouro.shared.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Utils {

    private Utils() {
    }

    public static String dateToString(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
