package org.practice.module4practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UniversityService {

    @Autowired
    private UniversityRepository repository;
    private final WebClient webClient = WebClient.builder().build();

    public Flux<University> getAndSaveUniversities() {
        Flux<University> universityFlux = webClient.get()
                .uri("http://universities.hipolabs.com/search?name=technical")
                .exchangeToFlux(r -> r.bodyToFlux(University.class));
        return repository.saveAll(universityFlux);
    }

    public Flux<University> findUniversitiesByCountry(String country) {
        return repository.findAllByCountry(country);
    }

    public Flux<University> findAll() {
        return repository.findAll();
    }

    public Mono<University> saveUniversity(University u) {
        return repository.save(u);
    }
}
