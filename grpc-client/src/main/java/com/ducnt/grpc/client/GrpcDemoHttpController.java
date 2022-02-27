package com.ducnt.grpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcDemoHttpController {

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
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();
        int res = grpcClient.calcFunc(123);
        long end = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        sb.append(String.format("TotalTime : %d", endTime - startTime));
        return "Res: " + res + "<br>"  + sb.toString();
    }

    @GetMapping("/http")
    public String doHttp() {
        StringBuilder sb = new StringBuilder();
        long startTime = System.currentTimeMillis();
        int res = httpClient.calc_function(345);
        long end = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        sb.append(String.format("TotalTime : %d", endTime - startTime));
        return "Res: " + res + "<br>"  + sb.toString();
    }
}
