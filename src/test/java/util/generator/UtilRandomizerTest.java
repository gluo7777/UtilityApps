package util.generator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class UtilRandomizerTest {

    private UtilRandomizer randomizer;

    @Before
    public void init(){
        randomizer = new UtilRandomizer();
    }

    @Test
    public void testGenerateRandomInteger_inRange(){
        for(int i=0 ; i<10000 ; i++) {
            int random = randomizer.generateRandomInteger(50, 100);
            assertTrue(random>=50);
            assertTrue(random<=100);
        }
    }

    @Test
    public void testGenerateRandomInteger_inclusive(){
        assertEquals(1,randomizer.generateRandomInteger(1,1));
    }

    @Test
    public void testGenerateRandomInteger_negative(){
        for(int i=0 ; i<10000 ; i++) {
            int random = randomizer.generateRandomInteger(-5, 5);
            assertTrue(random>=-5);
            assertTrue(random<=5);
        }
    }

    @Test
    public void testGenerateRandomInteger_overFlow(){
    }
}
