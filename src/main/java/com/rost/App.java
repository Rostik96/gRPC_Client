package com.rost;

import java.util.Iterator;

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
                .setName("Shamil'")
                .build();

        Iterator<GreetingServiceOuterClass.HelloResponse> respIter = stub.greeting(req);
        while (respIter.hasNext())
            System.out.println(respIter.next());
        channel.shutdownNow();
    }
}
