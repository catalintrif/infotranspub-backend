package ro.gov.ithub.infotranspub.extraction.scheduler;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import ro.gov.ithub.infotranspub.extraction.config.Constants;
import ro.gov.ithub.infotranspub.extraction.scheduler.jobs.RATBJob;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by vlad4800@gmail.com on 13-Dec-16.
 * <p>
 * Generic scheduler for repetitive crawler jobs
 * TODO Move jobs to a databse and pull them from there
 */
public class GenericScheduler {

    private static Scheduler scheduler;
    private static GenericScheduler instance = null;

    public static GenericScheduler getInstance() {
        if (instance == null) {
            instance = new GenericScheduler();
        }
        return instance;
    }

    public void start() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        setupRATBJob();

    }

    private void setupRATBJob() throws SchedulerException {

        JobDetail ratbJob = newJob(RATBJob.class)
                .withIdentity(Constants.JobNames.RATB)
                .build();

        Trigger ratbTrigger = newTrigger()
                .withIdentity(Constants.JobNames.RATB + "Trigger")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInHours(24)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(ratbJob, ratbTrigger);
    }

}
