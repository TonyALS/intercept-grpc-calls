package br.com.tony.resource;

import br.com.tony.InterceptGrpcCallsReply;
import br.com.tony.InterceptGrpcCallsRequest;
import br.com.tony.InterceptGrpcCallsServiceGrpc;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class ExampleResource extends InterceptGrpcCallsServiceGrpc.InterceptGrpcCallsServiceImplBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleResource.class);

    @Override
    public void send(InterceptGrpcCallsRequest request, StreamObserver<InterceptGrpcCallsReply> responseObserver) {

        LOGGER.info("Executando método send");

        responseObserver.onNext(InterceptGrpcCallsReply.newBuilder()
                .setMessage("Mensagem processada")
                .build());
        responseObserver.onCompleted();
    }
}
