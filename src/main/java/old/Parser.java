package old;

public interface Parser {

    /**
     * Converts arguments into flags and options
     * @param args arguments to parse
     */
//    void parse(String [] args) throws MissingOptionForFlagException;
    /**
     * returns true if parser has a flag
     * @param flag flag to check for
     * @return true if flag exists in executed line
     */
    boolean hasFlag(String flag);

    /**
     *
     * @param flag to get options for
     * @return list of parameters for flag
     */
    String getOptionForFlag(String flag);
}
