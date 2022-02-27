package com.ducnt.grpc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "HttpClientImpl", url = "localhost:8080", path = "/")
public interface IHttpFeignClient {
    @PostMapping(path = "/rest_calc")
    Integer rest_calc(@RequestParam(required = false, defaultValue = "") Integer val);
}
