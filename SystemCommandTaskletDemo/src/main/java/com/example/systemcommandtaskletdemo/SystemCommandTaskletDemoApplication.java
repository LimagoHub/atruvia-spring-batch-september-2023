package com.example.systemcommandtaskletdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SystemCommandTaskletDemoApplication {

    public static void main(String[] args) {

        System.exit(SpringApplication.exit(SpringApplication.run(SystemCommandTaskletDemoApplication.class, args)));
    }

}
