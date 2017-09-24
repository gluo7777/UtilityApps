package util.regex;

import java.util.EnumSet;
import java.util.Set;

public enum CommonPatterns {
    /**
     * Dates
     * Possible date formats:
     * mm/dd/yyyy
     * yyyy/mm/dd
     * mm-dd-yyyy
     * MMM dd, yyyy
     * MMM d ,yyyy
     * MMM. d ,yyyy
     * M_full_name dd, yyyy
     * yyyy-mm-dd
     * yyyy, M_full_name dd
     */
    NUMERIC_DATE("^(\\d{2})-(\\d{2})-(\\d{4})|(\\d{2})\\/(\\d{2})\\/(\\d{4})$"),
    NUMERIC_DATE_REVERSED("^(\\d{4})-(\\d{2})-(\\d{2})|(\\d{4})\\/(\\d{2})\\/(\\d{2})$"),
    NUMERIC_DATE_TEXT("^([A-Z][a-z]{2}\\.?|[A-Z][a-z]+) (\\d{1,2}),[ ]?(\\d{4})$"),
    NUMERIC_DATE_TEXT_REVERSED("^(\\d{4}),[ ]?([A-Z][a-z]{2}\\.?|[A-Z][a-z]+) (\\d{1,2})$"),

    /**
     * Phone numbers
     * (nnn) - nnn - nnnn
     * nnn - nnn - nnnn
     * above without spaces
     */
    PHONE_NUMBER("^(\\(\\d{3}\\)|\\d{3})[ ]?-?[ ]?(\\d{3})[ ]?-[ ]?(\\d{4})$"),

    /**
     * gmail
     * [letter,numbers,periods]@gmail.com
     *
     */
    GMAIL("^([A-Za-z0-9\\.]+@[a-z]+\\.com)$"),

    /**
     * addresses
     * number street-name street-type suite-number [,] city-name [,] state-name [,] zip-code
     * 2301 Bahamas island dr. suite, Garland, Texas, 75044
     */
    STREET("^(\\d+ [A-Za-z0-9]*(?:[A-Za-z0-9- ]+(?:[A-Za-z]+\\.?)))$"),
    SUITE("^((?:[A-Za-z]+|#)?[ ]?\\d+)?$"),
    CITY("^([A-Za-z0-9][A-Za-z0-9- ]*)$"),
    STATE("^([A-Za-z]+)$"),
    ZIP("^(\\d+-?\\d*)$")
    ;

    /**
     * Sets
     */
    public static Set<CommonPatterns> dates = EnumSet.range(NUMERIC_DATE,NUMERIC_DATE_TEXT_REVERSED);

    private final String pattern;

    CommonPatterns(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
