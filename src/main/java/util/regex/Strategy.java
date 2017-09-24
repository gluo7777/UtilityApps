package util.regex;

public enum Strategy {
    /* Date Strategies */
    DATE(100),
    PHONE(101),
    EMAIL(102),
    ADDRESS(103)
    ;

    public final int strategy;

    Strategy(int strategy) {
        this.strategy = strategy;
    }
}
