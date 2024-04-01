package com.reactiveMongoDb.Handler;

import com.reactiveMongoDb.dao.CustomerDao;
import com.reactiveMongoDb.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> findCustomerStream(ServerRequest request){
//        System.out.println("requested: "+request.pathVariable("asd"));
        Flux<Customer> daoCustomersStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(daoCustomersStream,Customer.class);
    }



}
