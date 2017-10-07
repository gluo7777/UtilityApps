package util.scheduler;

import enums.Property;

import java.io.*;
import java.util.Properties;

/**
 * Created by william.luo on 9/19/2017.
 * reads from property file to get properties for scheduler
 */
public class SchedulerConfiguration {
    private static final String DEFAULT = "defaultScheduler.properties";
    private static final String APP = "scheduler.properties";

    private Properties properties = null;
    private static SchedulerConfiguration config = null;

    private SchedulerConfiguration() {
        this.initProperties();
    }

    public static SchedulerConfiguration instance(){
        if(config == null){
            config = new SchedulerConfiguration();
        }
        return config;
    }

    public File getJobDirectory() throws FileNotFoundException {
        File dir = new File(config.getJobDirectoryPath());
        if(!dir.exists()){
            throw new FileNotFoundException();
        }
        return dir;
    }

    public String getJobDirectoryPath(){
        return config.properties.getProperty(Property.JOB_DIRECTORY.getValue());
    }

    public File getOutputFile() throws IOException {
        File file = new File(config.properties.getProperty(Property.OUTPUT_FILE.getValue()));
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    public String getSortType(){
        return config.properties.getProperty(Property.SORT.getValue());
    }

    public int getInterval() {
        return Integer.parseInt(config.properties.getProperty(Property.INTERVAL.getValue()));
    }

    public File getLogFile() throws IOException {
        File file = new File(config.properties.getProperty(Property.LOG.getValue()));
        if(!file.exists()){
            file.createNewFile();
        }
        return file;
    }

    public void deleteLogFile() throws FileNotDeletedException {
        File file = new File(config.properties.getProperty(Property.LOG.getValue()));
        boolean result = false;
        if(file.exists() && !file.isDirectory()){
            if(!file.delete()){
                throw new FileNotDeletedException("Unable to delete log file: "+file.getAbsolutePath());
            }
        }

    }

    private void initProperties(){
        InputStream fs = null;
        try {
            // load default properties
            Properties defaultProp = new Properties();
//            fs = new FileInputStream(DEFAULT);
            defaultProp.load(this.getClass().getClassLoader().getResourceAsStream(DEFAULT));
//            fs.close();

            // default constructor
            properties = new Properties(defaultProp);

            // load properties from last invocation
//            fs = new FileInputStream(APP);
            try {
                properties.load(this.getClass().getClassLoader().getResourceAsStream(APP));
            } catch (NullPointerException e){
                // custom file not set by user
            }
//            fs.close();

        } catch (FileNotFoundException e) {
            // TODO separate try-catch block to handle both files
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fs != null){
                try {
                    fs.close();
                } catch (IOException e) {
                    // fs already closed
                }
            }
        }
    }
}
