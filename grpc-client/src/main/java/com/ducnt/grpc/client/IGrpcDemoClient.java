package com.ducnt.grpc.client;

public interface IGrpcDemoClient {
    public String getMsg(String msg);

    public int calcFunc(int val);
}
