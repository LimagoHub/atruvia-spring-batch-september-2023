package com.example.systemcommandtaskletdemo.batchconfig;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository repository;
    private final PlatformTransactionManager transactionManager;




    @Bean
    @JobScope
    public Step step1(Tasklet task1,@Value("#{jobParameters[filename]}") String filename) {
        System.out.println(filename);
        return new StepBuilder("step1", repository).tasklet( task1, transactionManager ).build();
    }

    @Bean
    @StepScope
    public SystemCommandTasklet task1(@Value("#{jobParameters[filename]}") String filename) {
        SystemCommandTasklet tasklet = new SystemCommandTasklet();

        tasklet.setCommand("wsl", "split","-l 2","-d","-a 3","--additional-suffix=.csv",filename,"split");
        tasklet.setWorkingDirectory("C:\\Users\\JoachimWagner\\git\\Atruvia\\atruvia-spring-batch-september-2023\\SystemCommandTaskletDemo\\src\\main\\resources\\input");
        tasklet.setTimeout(5000);

        return tasklet;
    }

    @Bean

    public Job meinTaskletJob(Step step1) throws Exception
    {


        return new JobBuilder("meinTaskJob", repository).incrementer( new RunIdIncrementer() )
                .start( step1 )

                .build();
    }

}
