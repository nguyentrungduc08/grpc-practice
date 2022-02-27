package com.ducnt.grpc.jobs;

public class DemoJob {
    public static int JOBS = 100000;

    public static void doSomething() {
        for (int i = 0; i < JOBS; ++i) {
            //hard work
        }
    }
}
