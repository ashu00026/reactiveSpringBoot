package com.reactiveMongoDb;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class MonoFluxTest {

    @Test
    public void testMono(){
        Mono<?> monoString = Mono.just("Ashutosh")
                .then(Mono.error(new RuntimeException("excetion occured!!")))
                .log();

        //error handling in reactive programming
        monoString.subscribe(System.out::print,(e)-> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> applicationsFlux = Flux.just("spring", "microservice", "hibernate", "mognodb")
                .concatWithValues("aws")
                .concatWith(Flux.error(new RuntimeException("exception occured in Flux!!!")))
                .doOnComplete(()-> System.out.println("completed Execution!"))
                .concatWithValues("Cloud")
                .log();
        applicationsFlux.subscribe(System.out::println,(e)-> System.out.println(e.getMessage()));

    }

}
