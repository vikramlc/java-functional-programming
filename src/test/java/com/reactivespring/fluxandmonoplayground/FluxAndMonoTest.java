package com.reactivespring.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    public void fluxTest() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Error occurred!")))
                .concatWith(Flux.just("After error!"))
                .log();

        stringFlux.subscribe(System.out::println,
                (e) -> System.err.println("Exception is " + e),
                () -> System.out.println("Completed!"));

    }

    @Test
    public void fluxTestElements_WithoutError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive Spring")
                .verifyComplete();

    }

    @Test
    public void fluxTestElements_WithError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Error occurred!!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive Spring")
//                .expectError(RuntimeException.class)
                .expectErrorMessage("Error occurred!!")
                .verify();

    }

    @Test
    public void fluxTestElementsImproved_WithError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Error occurred!!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring boot", "Reactive Spring")
                .expectErrorMessage("Error occurred!!")
                .verify();

    }

    @Test
    public void fluxTestElementsCount_WithError() {

        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Error occurred!!")))
                .log();

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectErrorMessage("Error occurred!!")
                .verify();

    }

    @Test
    public void monoTest() {

        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono)
                .expectNext("Spring")
                .verifyComplete();

    }

    @Test
    public void monoTest_Error() {

        StepVerifier.create(Mono.error(new RuntimeException("Error ocurred!!")))
                .expectError(RuntimeException.class)
                .verify();

    }

}
