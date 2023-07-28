package com.example.webflux.repository;

import com.example.webflux.domain.Movie;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReactiveMovieRepository {

    private static List<Movie> movies = new ArrayList<>();

    @PostConstruct
    public void init(){
        movies.add(new Movie(1L, "Polar (2019)", 64));
        movies.add(new Movie(2L, "Iron Man (2008)", 79));
        movies.add(new Movie(3L, "The Shawshank Redemption (1994)", 93));
        movies.add(new Movie(4L, "Forrest Gump (1994)", 83));
        movies.add(new Movie(5L, "Glass (2019)", 70));
        System.out.println("movies = " + movies);
    }

    public Flux<Movie> findAll(){
        return Flux.fromIterable(movies)
                .delayElements(Duration.ofMillis(100));
    }
}
