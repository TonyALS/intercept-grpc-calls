package br.com.tony.interceptor;

import br.com.tony.resource.ExampleResource;
import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class InterceptGrpcCalls implements ServerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleResource.class);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {

        LOGGER.info("Headers: {}", headers.get(Metadata.Key.of("header-test", Metadata.ASCII_STRING_MARSHALLER)));

        return new ForwardingServerCallListener
                .SimpleForwardingServerCallListener<>(next.startCall(call, headers)) {
            @Override
            public void onMessage(ReqT message) {
                super.onMessage(message);
            }
        };
    }
}
