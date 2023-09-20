package com.example.systemcommandtaskletdemo.batchconfig;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository repository;
    private final PlatformTransactionManager transactionManager;

    public Step step1() {

        return new StepBuilder("step1", repository).tasklet( task1(), transactionManager ).build();
    }

    @Bean
    public SystemCommandTasklet task1() {
        SystemCommandTasklet tasklet = new SystemCommandTasklet();

        tasklet.setCommand("wsl", "split","-l 2","-d","-a 3","--additional-suffix=.csv","../umsaetze.csv","split");
        tasklet.setWorkingDirectory("C:\\Users\\JoachimWagner\\git\\Atruvia\\atruvia-spring-batch-september-2023\\SystemCommandTaskletDemo\\src\\main\\resources\\input");
        tasklet.setTimeout(5000);

        return tasklet;
    }

    @Bean
    public Job meinTaskletJob() throws Exception
    {


        return new JobBuilder("meinTaskJob", repository).incrementer( new RunIdIncrementer() )
                .start( step1() )

                .build();
    }

}
