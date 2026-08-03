package com.example.Employee.controller;

import com.example.Employee.async.EmailAsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor

public class AsyncTestController {


    private final EmailAsyncService emailAsyncService;


    @GetMapping("/async")
    public String testAsync() {

        emailAsyncService.sendEmail(
                "employee@gmail.com"
        );

        return "Request completed";

    }

}
