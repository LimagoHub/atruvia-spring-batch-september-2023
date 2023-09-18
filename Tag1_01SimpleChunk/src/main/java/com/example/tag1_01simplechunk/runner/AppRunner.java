package com.example.tag1_01simplechunk.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final JobLauncher jobLauncher;
    private final Job job;
    @Override
    public void run(final String... args) throws Exception {
        JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
        System.out.println(jobExecution.getStatus());
    }
}
