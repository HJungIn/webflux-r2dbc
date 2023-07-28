package com.example.webflux.repository;

import com.example.webflux.domain.Movie;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieR2dbcRepository extends R2dbcRepository<Movie, Long> {

    // Mono : one or Flux : all
//    Mono<Movie> findByName(String name);
//    Flux<Movie> findByName(String name);
}
