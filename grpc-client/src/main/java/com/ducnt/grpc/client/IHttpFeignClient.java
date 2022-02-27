package com.ducnt.grpc.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("client-demo")
public interface IHttpFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "/rest_calc")
    Integer rest_calc(@RequestParam(required = false, defaultValue = "") Integer val);
}
