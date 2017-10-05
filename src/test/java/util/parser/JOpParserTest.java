package util.parser;

import org.junit.Test;
import util.parser.JOptParser;
import static org.junit.Assert.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JOpParserTest {
    @Test
    public void testParserSuccess(){
        JOptParser jOptParser = JOptParser.buildParser();
        String arguments [] = new String[]{"-w","-f","a.txt,b.txt","-l","file.log"};
        boolean success = jOptParser.parseArguments(arguments);
        assertTrue(success);
        List<File> jobFiles = Arrays.asList(new File("a.txt"),new File("b.txt"));
        File logFile = new File("file.log");
        assertEquals(jobFiles,jOptParser.getJobFiles());
        assertEquals(logFile,jOptParser.getLogFile());
    }
}
