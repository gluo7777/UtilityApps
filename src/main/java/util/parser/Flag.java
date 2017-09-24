package util.parser;

public enum Flag {
    BASH("-b"),
    WINDOW("-w"),
    INTERVAL("-interval"),
    LOG("-log"),
    ;

    private String value;
    Flag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
