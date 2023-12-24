package org.practice.module4practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UniversityHandler {

    @Autowired
    private UniversityService universityService;

    public Mono<ServerResponse> handleGetByName(ServerRequest request) {
        Flux<University> uf = universityService.findUniversitiesByCountry(request.queryParam("country").get());
        return ServerResponse.ok().body(BodyInserters.fromPublisher(uf, University.class));
    }

    public Mono<ServerResponse> handleCreateUniversity(ServerRequest request) {
        Mono<University> mono = request.bodyToMono(University.class)
                .flatMap(u -> universityService.saveUniversity(u));
        return ServerResponse.status(201).body(BodyInserters.fromPublisher(mono, University.class));
    }

    public Mono<ServerResponse> handleBackPressure(ServerRequest request) {
        Flux<University> uf = universityService.findAll().limitRate(Integer.parseInt(request.queryParam("limit").get())).log();
        return ServerResponse.ok().body(BodyInserters.fromPublisher(uf, University.class));
    }
}
