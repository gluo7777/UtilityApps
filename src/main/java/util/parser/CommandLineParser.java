package util.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * java -jar logger.jar [flags] {directory-containing-files} [optional files]
 * flags:
 * -B bash scripts
 * -W window bat files
 * -interval specify interval
 * -log log stats to scheduler.log
 * options:
 * {file1} [file2] ... parse multiple files
 */
public class CommandLineParser implements Parser{

    private Map<String, String> flagOptions;

    public CommandLineParser(){
        this.flagOptions = new HashMap<>();
    }

    @Override
    public void parse(String[] args) throws MissingOptionForFlagException, UnsupportedFlagException {
        for(int i = 0 ; i < args.length ; i ++){
            if(args[i].equals(Flag.BASH.getValue())){
                flagOptions.put(Flag.BASH.getValue(), null);
            } else if(args[i].equals(Flag.WINDOW.getValue())){
                flagOptions.put(Flag.WINDOW.getValue(), null);
            } else if(args[i].equals(Flag.LOG.getValue())){
                i ++;
                if(i >= args.length) throw new MissingOptionForFlagException("Missing Log file.");
                flagOptions.put(Flag.LOG.getValue(),args[i]);
            } else if(args[i].equals(Flag.INTERVAL.getValue())){
                i ++;
                if(i >= args.length) throw new MissingOptionForFlagException("Interval not specified.");
                flagOptions.put(Flag.INTERVAL.getValue(),args[i]);
            } else{
                throw new UnsupportedFlagException(args[i] + " not a valid flag.");
            }
        }
    }

    @Override
    public boolean hasFlag(String arg) {
        for(String flag : flagOptions.keySet()){
            if(arg.equals(flag)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getOptionForFlag(String flag) {
        return flagOptions.get(flag);
    }


}
