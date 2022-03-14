package com.ducnt.grpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpClientImpl{

    private static final Logger _Logger = LoggerFactory.getLogger(HttpClientImpl.class);

    @Autowired
    private IHttpFeignClient feignClient;

    public Integer calc_function(int val) {
        _Logger.info("Http client start request " + val);
        int res = feignClient.rest_calc(val);
        _Logger.info(Thread.currentThread().getName() + " http caclFunc val " + val + " res= " + res);
        return res;
    }
}
