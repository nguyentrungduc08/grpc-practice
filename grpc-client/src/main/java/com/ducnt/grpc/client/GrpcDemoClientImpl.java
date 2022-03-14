package com.ducnt.grpc.client;

import com.ducnt.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GrpcDemoClientImpl implements IGrpcDemoClient {

    private static final Logger _Logger = LoggerFactory.getLogger(GrpcDemoClientImpl.class);
    private static final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();;

    public GrpcDemoClientImpl() {
    }

    public String getMsg(String msg) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

		DemoServiceGrpc.DemoServiceBlockingStub stub = DemoServiceGrpc.newBlockingStub(channel);

		DemoResponse helloResponse = stub
				.hello(DemoRequest.newBuilder().setMessage(msg).build());
        channel.shutdown();

		System.out.println(helloResponse.getMessage());
        return helloResponse.getMessage();
    }

    @Override
    public int calcFunc(int val) {
        _Logger.info("Grpc client start request " + val);
        DemoServiceGrpc.DemoServiceBlockingStub stub = DemoServiceGrpc.newBlockingStub(channel);
        CalcResponse res = stub
                .calcFunc(CalcRequest.newBuilder().setVal(val).build());

        _Logger.info(Thread.currentThread().getName() + " grcp calcFunc val= " + val + ", res= " +res.getVal());
        return res.getVal();
    }
}
