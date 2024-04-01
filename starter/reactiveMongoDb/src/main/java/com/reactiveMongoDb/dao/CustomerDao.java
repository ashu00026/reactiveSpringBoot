package com.reactiveMongoDb.dao;

import com.reactiveMongoDb.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {

    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getCustomers(){
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::sleepExecution)
                .peek(i-> System.out.println("proccessing count "+i))
                .mapToObj(i->new Customer(i,"Customer: "+i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream(){
//        Flux<?> sdwe = Flux.just("1", "2", 3);
        return Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println("processing Count in streamFlow: "+ i))
                .map(i->new Customer(i,"customer :"+i));
    }

    public Flux<Customer> getCustomersList(){
        return Flux.range(1,50)
                .doOnNext(i-> System.out.println("processing Count in streamFlow: "+ i))
                .map(i->new Customer(i,"customer :"+i));
    }
}
