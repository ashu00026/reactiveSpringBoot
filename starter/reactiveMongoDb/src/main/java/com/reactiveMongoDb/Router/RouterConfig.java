package com.reactiveMongoDb.Router;

import com.reactiveMongoDb.Handler.CustomerHandler;
import com.reactiveMongoDb.Handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    private CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route()
                .GET("/router/customers",(serverRequest)->customerHandler.loadCustomers(serverRequest))
                .GET("/router/customers/stream",customerStreamHandler::findCustomerStream)
                .build();
    }

}
