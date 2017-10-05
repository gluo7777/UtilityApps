package util.parser;

import java.util.EnumSet;
import java.util.Set;

public enum Flag {
    BASH("b"),
    WINDOW("w"),
    INTERVAL("i"),
    LOG("l"),
    FILE("f"),
    DIRECTORY("d")
    ;

    private String value;

    private static Set<Flag> flags = EnumSet.range(BASH,DIRECTORY);
    private static final String parseString = Flag.BASH.value
            + Flag.WINDOW.value
            + Flag.INTERVAL.value + ":"
            + Flag.LOG.value + ":"
            + Flag.DIRECTORY.value + ":"
            + Flag.FILE.value + ":"
            ;

    Flag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     *
     * @return set of flags
     */
    public static Set<Flag> getFlags() {
        return flags;
    }

    /**
     *
     * @return set of flags as Jopt parse string
     */
    public static String getParseString(){
        return parseString;
    }
}
