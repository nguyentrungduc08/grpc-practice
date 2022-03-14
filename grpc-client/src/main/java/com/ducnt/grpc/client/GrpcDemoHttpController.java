package com.ducnt.grpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GrpcDemoHttpController {
    private static final Logger _Logger = LoggerFactory.getLogger(GrpcDemoHttpController.class);
    private static AtomicInteger aValGrpc = new AtomicInteger(0);
    private static AtomicInteger aValHttp = new AtomicInteger(0);

    @Autowired
    private GrpcDemoClientImpl grpcClient;

    @Autowired
    private HttpClientImpl httpClient;

    @GetMapping("/get-result")
    public String getResult() {
        String res = " server";
        res = grpcClient.getMsg("client hello");
        return "hello" + res;
    }

    @GetMapping("/bench-test")
    public String benchTest() {
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();
        for(int i = 0; i < 100; ++i) {
            long start = System.currentTimeMillis();
            int res = grpcClient.calcFunc(i);
            long end = System.currentTimeMillis();
            sb.append(String.format("Request %d res= %d, processTime = %d", i, res, end-start)).append("<br>");
        }
        long endTime = System.currentTimeMillis();
        sb.append(String.format("TotalTime : %d", endTime - startTime));
        return "Res: <br>" + sb.toString();
    }

    @GetMapping("/grpc")
    public String doGrcp() {
        _Logger.info("GRPC_________________");
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();
        int val = aValGrpc.incrementAndGet();
        _Logger.info("Grpc controler receive request val=" + val);
        int res = grpcClient.calcFunc(val);
        long end = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        sb.append(String.format("TotalTime : %d", endTime - startTime));
        return "Res: " + res + "<br>"  + sb.toString();
    }

    @GetMapping("/http")
    public String doHttp() {
        _Logger.info("HTTP_________________");
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();
        int val = aValHttp.incrementAndGet();
        _Logger.info("Http controler receive request val=" + val);
        int res = httpClient.calc_function(val);
        long end = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        sb.append(String.format("TotalTime : %d", endTime - startTime));
        return "Res: " + res + "<br>"  + sb.toString();
    }
}
