import org.junit.Before;
import org.junit.Test;
import util.parser.CommandLineParser;
import util.parser.MissingOptionForFlagException;
import util.parser.UnsupportedFlagException;

import static org.junit.Assert.*;

public class CommandLineParserTest {

    CommandLineParser commandLineParser;

    @Before
    public void init(){
        commandLineParser = new CommandLineParser();
    }

    @Test
    public void testParseArgumentSuccess(){
        String [] args = new String[]{
                "-b","-w","-log","log-file","-interval","7"
        };
        try {
            commandLineParser.parse(args);
        } catch (MissingOptionForFlagException | UnsupportedFlagException e) {
            e.printStackTrace();
        }
        assertTrue(commandLineParser.hasFlag("-b"));
        assertTrue(commandLineParser.hasFlag("-w"));
        assertTrue(commandLineParser.hasFlag("-log"));
        assertTrue(commandLineParser.hasFlag("-interval"));
        assertEquals("log-file",commandLineParser.getOptionForFlag("-log"));
        assertEquals("7",commandLineParser.getOptionForFlag("-interval"));
    }

    @Test
    public void testParseUnsupportedFlagException(){
        String [] args = new String[]{"-a"};
        try {
            commandLineParser.parse(args);
        } catch (Exception e){
            assertEquals(UnsupportedFlagException.class, e.getClass());
        }
    }

    @Test
    public void testParseMissingOptionForFlagException_NoOption(){
        String [] args = new String[]{"-log"};
        try {
            commandLineParser.parse(args);
        } catch (Exception e){
            assertEquals(MissingOptionForFlagException.class, e.getClass());
        }
    }
}
