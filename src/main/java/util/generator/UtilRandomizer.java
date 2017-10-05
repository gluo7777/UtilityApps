package util.generator;

import java.time.LocalTime;
import java.util.Random;

public class UtilRandomizer implements Randomizer {
    @Override
    public int generateRandomInteger(int lo, int hi) {
        if (lo > hi)
            throw new IllegalArgumentException();
        return (int) (
                (
                        (long) hi - (long) lo + 1
                ) * new Random(LocalTime.now().getNano()).nextDouble()
        ) + lo;
    }
}
