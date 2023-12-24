package org.practice.module4practice;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UniversityRepository extends ReactiveCrudRepository<University, Integer> {

    Flux<University> findAllByCountry(String country);
}
