package util.scheduler;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by william.luo on 9/19/2017.
 */
public class Scheduler {
    // navigate to job directory
    // exec bat file
    // check if file terminated
    // exec next bat file

    public static final String EXEC = "cmd.exe";
    public static final String EXEC_ARG = "/C";
    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_DATE = "date";

    private File workingDirectory;

    public Scheduler(File directory) {
        this.workingDirectory = directory;
    }

    public File getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    /**
     * Loop through each bat file and execute them
     */
    public void run() throws NotDirectoryException {
        File dir = this.workingDirectory;
        File files[];
        if (!dir.isDirectory()) {
            throw new NotDirectoryException(dir.getAbsolutePath());
        }
        files = dir.listFiles();
        if (files.length > 0) {
            this.sortFiles(files);
            for (File file : files) {
                this.runJob(file.getName(),dir);
            }
        } else {
            throw new RuntimeException();
        }
    }

    public void runJob(String job, File directory) {
        try {
            ProcessBuilder proc = new ProcessBuilder(EXEC, EXEC_ARG, job);
            proc.directory(directory);
            proc.redirectOutput(ProcessBuilder.Redirect.appendTo(SchedulerConfiguration.instance().getOutputFile()));
            proc.start();
        } catch (IOException e) {
            System.err.println("Unable to start process: " + directory.getAbsolutePath() + ": " + job);
        }
    }

    private void sortFiles(File[] files) {
        if (SchedulerConfiguration.instance().getSortType().equals(SORT_BY_NAME)) {
            Arrays.sort(files, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        } else if (SchedulerConfiguration.instance().getSortType().equals(SORT_BY_DATE)) {
            Arrays.sort(files, (o1, o2) -> {
                long result = o1.lastModified() - o2.lastModified();
                if(result > 0){
                    return 1;
                } else if (result < 0){
                    return -1;
                } else{
                    return 0;
                }
            });
        }
    }
}
