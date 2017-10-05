package util.scheduler;

import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

/**
 * Created by william.luo on 9/19/2017.
 */
public class Runner {
    public static void main(String [] args){
        // runs job scheduler
        Scheduler s = null;
        try {
            s = new Scheduler();
            s.setJobs(SchedulerConfiguration.instance().getJobDirectory());
            s.run();
        } catch (FileNotFoundException e) {
            System.err.println("Directory does not exist: "+ SchedulerConfiguration.instance().getJobDirectoryPath());
            System.exit(-1);
        } catch (NotDirectoryException e) {
            System.err.println(e.toString());
            System.exit(-1);
        }
    }
}
