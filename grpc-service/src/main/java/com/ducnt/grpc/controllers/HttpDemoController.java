package com.ducnt.grpc.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpDemoController {

    @PostMapping("/")
    public String getResult() {
        String res = " server";
        res = grpcClient.getMsg("client hello");
        return "hello" + res;
    }
}
