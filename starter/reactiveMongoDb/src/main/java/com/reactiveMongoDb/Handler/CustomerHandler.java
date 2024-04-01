package com.reactiveMongoDb.Handler;

import com.reactiveMongoDb.dao.CustomerDao;
import com.reactiveMongoDb.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> loadCustomers(ServerRequest request){
        Flux<Customer> customerList=customerDao.getCustomersList();
        return ServerResponse.ok().body(customerList,Customer.class);
    }

}
