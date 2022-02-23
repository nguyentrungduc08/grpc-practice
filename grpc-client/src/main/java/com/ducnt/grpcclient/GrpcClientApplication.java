package com.ducnt.grpcclient;

import com.ducnt.grcp.DemoRequest;
import com.ducnt.grcp.DemoResponse;
import com.ducnt.grcp.DemoServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrpcClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientApplication.class, args);
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();

		DemoServiceGrpc.DemoServiceBlockingStub stub = DemoServiceGrpc.newBlockingStub(channel);

		DemoResponse helloResponse = stub
				.hello(DemoRequest.newBuilder().setMessage("client hello").build());

		System.out.println(helloResponse.getMessage());

		channel.shutdown();
	}

}
