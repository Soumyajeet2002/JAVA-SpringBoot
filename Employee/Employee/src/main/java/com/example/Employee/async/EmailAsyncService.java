package com.example.Employee.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class EmailAsyncService {


    @Async
    public void sendEmail(String email) {

        log.info(
                "Email task started for {} Thread: {}",
                email,
                Thread.currentThread().getName()
        );


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        log.info(
                "Email sent successfully to {}",
                email
        );
    }

}