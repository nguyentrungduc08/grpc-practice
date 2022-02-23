package com.ducnt.grpc.server;

import com.ducnt.grcp.DemoResponse;
import com.ducnt.grcp.DemoServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class DemoServerImpl extends DemoServiceGrpc.DemoServiceImplBase {

    @Override
    public void hello(com.ducnt.grcp.DemoRequest request,
                      io.grpc.stub.StreamObserver<com.ducnt.grcp.DemoResponse> responseObserver) {
        String msg = request.getMessage();
        System.out.println("receive msg: " + msg);

        DemoResponse build = DemoResponse.newBuilder().setMessage("Receive " + msg + ". server hello!").build();

        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }
}
