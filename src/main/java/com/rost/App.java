package com.rost;

import com.rost.grpc.GreetingServiceGrpc;
import com.rost.grpc.GreetingServiceOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        GreetingServiceOuterClass.HelloRequest req = GreetingServiceOuterClass.HelloRequest.newBuilder()
                .setName("Neil")
                .build();

        GreetingServiceOuterClass.HelloResponse resp = stub.greeting(req);
        System.out.println(resp);
        channel.shutdownNow();
    }
}
