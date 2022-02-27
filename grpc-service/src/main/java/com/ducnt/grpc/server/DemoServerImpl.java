package com.ducnt.grpc.server;

import com.ducnt.grpc.CalcResponse;
import com.ducnt.grpc.DemoResponse;
import com.ducnt.grpc.DemoServiceGrpc;
import com.ducnt.grpc.jobs.DemoJob;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class DemoServerImpl extends DemoServiceGrpc.DemoServiceImplBase {
    private static final Logger _Logger = LoggerFactory.getLogger(DemoServerImpl.class);

    @Override
    public void hello(com.ducnt.grpc.DemoRequest request,
                      io.grpc.stub.StreamObserver<com.ducnt.grpc.DemoResponse> responseObserver) {
        String msg = request.getMessage();
        System.out.println("receive msg: " + msg);

        DemoResponse build = DemoResponse.newBuilder().setMessage("Receive " + msg + ". server hello!").build();

        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    @Override
    public void calcFunc(com.ducnt.grpc.CalcRequest request,
                         io.grpc.stub.StreamObserver<com.ducnt.grpc.CalcResponse> responseObserver) {
        DemoJob.doSomething();
        _Logger.info("Receive Grcp request val = " + request.getVal());
        CalcResponse res = CalcResponse.newBuilder().setVal(request.getVal() * 2).build();
        responseObserver.onNext(res);
        responseObserver.onCompleted();
    }
}
