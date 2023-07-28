//package com.example.webflux.service;
//
//import com.example.webflux.domain.MovieJpa;
//import com.example.webflux.repository.MovieJpaRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//@Service
//@RequiredArgsConstructor
//public class MovieJpaService {
//
//    private final MovieJpaRepository movieJpaRepository;
//
//    @PostConstruct
//    public void init(){
//        movieJpaRepository.save(MovieJpa.builder().name("name").score(11).build());
//    }
//
//
//
//}
//
