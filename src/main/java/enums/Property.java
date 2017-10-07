package enums;

/**
 * Created by william.luo on 9/19/2017.
 */
public enum Property {
    JOB_DIRECTORY("job-directory"),
    OUTPUT_FILE("stdout-file"),
    INTERVAL("interval"),
    LOG("log-file"),
    SORT("sort");

    String value;

    Property(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
