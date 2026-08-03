package com.example.Employee.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeScheduler {


    @Scheduled(fixedRate = 60000)
    public void runEmployeeTask() {

        log.info("Employee scheduler executed");

    }

    //    private final EmployeeDocumentRepository documentRepository;
    @Scheduled(cron = "0 0 2 * * *")
    public void cleanupFiles() {

        log.info("Starting file cleanup job");

        // find old files
        // delete unused files

//        runs every day at 2
        log.info("File cleanup completed");
    }

}