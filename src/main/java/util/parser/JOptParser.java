package util.parser;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;

import java.io.File;
import java.util.List;

public class JOptParser {

    private static final char DELIMITER = ',';
    private OptionParser parser;
    private List<File> jobFiles;
    private OptionSpec<File> fileSpec;
    private OptionSpec<File> directorySpec;
    private File logFile;
    private OptionSpec<File> logSpec;
    private String scriptType;
    private long interval;
    private OptionSpec<Long> intervalSpec;

    private JOptParser() {
    }

    public static JOptParser buildParser() {
        JOptParser jParser = new JOptParser();
        OptionParser parser = new OptionParser();
        parser.accepts(Flag.BASH.getValue());
        parser.accepts(Flag.WINDOW.getValue());
        jParser.intervalSpec = parser.accepts(Flag.INTERVAL.getValue()).withRequiredArg().ofType(Long.TYPE);
        jParser.fileSpec = parser.accepts(Flag.FILE.getValue()).withRequiredArg().ofType(File.class)
                .withValuesSeparatedBy(DELIMITER);
        jParser.logSpec = parser.accepts(Flag.LOG.getValue()).withRequiredArg().ofType(File.class)
                .withValuesSeparatedBy(DELIMITER);
        jParser.directorySpec = parser.accepts(Flag.DIRECTORY.getValue()).withRequiredArg().ofType(File.class);
        jParser.parser = parser;
        return jParser;
    }

    public boolean parseArguments(String [] args){
        OptionSet set = this.parser.parse(args);
        if(set.has(Flag.BASH.getValue()) && set.has(Flag.WINDOW.getValue())) {
            return false;
        }
        if(set.has(Flag.DIRECTORY.getValue()) && set.has(Flag.FILE.getValue())) {
            return false;
        }
        if(set.has(Flag.BASH.getValue())){
            this.scriptType = Flag.BASH.getValue();
        }
        if(set.has(Flag.WINDOW.getValue())){
            this.scriptType = Flag.WINDOW.getValue();
        }
        if(set.has(Flag.INTERVAL.getValue())){
            this.interval = set.valueOf(this.intervalSpec);
        }
        if(set.has(Flag.DIRECTORY.getValue())) {
            // add files in directory to list of job files
            if(set.hasArgument(Flag.DIRECTORY.getValue())) {
                this.jobFiles = set.valuesOf(this.directorySpec);
            }else{
                return false;
            }
        } else if(set.has(Flag.FILE.getValue())){
            // add files as a list
            if(set.hasArgument(Flag.FILE.getValue())) {
                this.jobFiles = set.valuesOf(this.fileSpec);
            }else{
                return false;
            }
        }
        if(set.has(Flag.LOG.getValue())){
            if(set.hasArgument(Flag.LOG.getValue())){
                this.logFile = set.valueOf(this.logSpec);
            }else{
                return false;
            }
        }
        return true;
    }

    public List<File> getJobFiles() {
        return jobFiles;
    }

    public File getLogFile() {
        return logFile;
    }

    public String getScriptType() {
        return scriptType;
    }

    public long getInterval() {
        return interval;
    }
}
