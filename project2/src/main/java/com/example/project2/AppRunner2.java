package com.example.project2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner2 implements CommandLineRunner {


    private final JobLauncher jobLauncher;
    private final Job job;

    @Override
    public void run(final String... args) throws Exception {
        System.out.println( "\nJoblauf mit fehlerhaftem Job-Parameter (Text statt Zahl):" );
        JobExecution je = jobLauncher.run( job, new JobParametersBuilder().addString(
                TaskletJobConfiguration.ANZAHLSTEPS_KEY, "xx" ).toJobParameters() );
        // Ueberpruefe Job-Ergebnis:
       for( StepExecution se : je.getStepExecutions() ) {
            System.out.println( "StepExecution " + se.getId() + ": StepName = " + se.getStepName() +
                    ", CommitCount = " + se.getCommitCount() +
                    ", BatchStatus = " + se.getStatus() + ", ExitStatus = " + se.getExitStatus().getExitCode() );
        }
    }
}
