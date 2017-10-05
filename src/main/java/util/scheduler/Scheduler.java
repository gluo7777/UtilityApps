package util.scheduler;

import java.io.*;
import java.nio.file.NotDirectoryException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by william.luo on 9/19/2017.
 */
public class Scheduler {
    /* Windows executing batch file commands*/
    public static final String EXEC = "cmd.exe";
    public static final String EXEC_ARG = "/C";

    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_DATE = "date";

    private List<File> jobFiles;

    public Scheduler() {

    }

    /**
     *
     * @param directory containing jobs to run
     * @throws NotDirectoryException if File is not a directory
     */
    public void setJobs(File directory) throws NotDirectoryException {
        if (directory != null && directory.isDirectory()) {
            this.jobFiles = Arrays.asList(directory.listFiles());
        }else{
            throw new NotDirectoryException(directory.getAbsolutePath());
        }
    }

    /**
     *
     * @param jobFiles list of files to execute
     */
    public void setJobs(List<File> jobFiles) {
        this.jobFiles = jobFiles;
    }

    /**
     * Loop through each bat file and execute them
     */
    public void run(){
        if (jobFiles.size() > 0) {
            this.sortFiles(this.jobFiles);
            for (File file : this.jobFiles) {
                this.runJob(file.getName(), file.getParentFile());
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

    private void sortFiles(List<File> files) {
        if (SchedulerConfiguration.instance().getSortType().equals(SORT_BY_NAME)) {
            Collections.sort(files, Comparator.comparing(File::getName));
        } else if (SchedulerConfiguration.instance().getSortType().equals(SORT_BY_DATE)) {
            Collections.sort(files, (o1, o2) -> {
                long result = o1.lastModified() - o2.lastModified();
                if (result > 0) {
                    return 1;
                } else if (result < 0) {
                    return -1;
                } else {
                    return 0;
                }
            });
        }
    }
}
