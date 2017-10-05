package util.generator;

/**
 * Class for generating random numbers in different types and formats
 */
public interface Randomizer {
    /**
     * handles integer overflow
     * @param lo
     * @param hi
     * @return random integer between lo and hi, inclusive
     */
    public int generateRandomInteger(int lo, int hi);
}
