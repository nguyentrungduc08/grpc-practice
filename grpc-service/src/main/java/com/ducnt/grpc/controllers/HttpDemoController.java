package com.ducnt.grpc.controllers;

import com.ducnt.grpc.jobs.DemoJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpDemoController {

    public static final Logger _Logger = LoggerFactory.getLogger(HttpDemoController.class);

    @PostMapping("/rest_calc")
    public Integer getResult(@RequestParam(required = true, defaultValue = "") Integer val) {
        DemoJob.doSomething();
        _Logger.info("Recevie http request val=" + val);
        return (val * 2);
    }
}
