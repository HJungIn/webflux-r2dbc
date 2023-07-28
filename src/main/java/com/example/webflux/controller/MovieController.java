package com.example.webflux.controller;

//import com.example.webflux.repository.MovieJpaRepository;
//import com.example.webflux.repository.MovieJpaRepository;
import com.example.webflux.domain.Movie;
import com.example.webflux.repository.MovieR2dbcRepository;
import com.example.webflux.repository.ReactiveMovieRepository;
//import com.example.webflux.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
@RequiredArgsConstructor
public class MovieController {

//    private final MovieJpaRepository movieJpaRepository;
    private final MovieR2dbcRepository movieR2dbcRepository;
    private final ReactiveMovieRepository reactiveMovieRepository;

    @RequestMapping("/")
    public String home(Model model){
//        IReactiveDataDriverContextVariable reactiveDataDriver = new ReactiveDataDriverContextVariable(movieJpaRepository.findAll(), 1); // x : jpa는 blocking 되는 애라서 reactive일 때 안됨
//        IReactiveDataDriverContextVariable reactiveDataDriver = new ReactiveDataDriverContextVariable(reactiveMovieRepository.findAll(), 1); // o
        IReactiveDataDriverContextVariable reactiveDataDriver = new ReactiveDataDriverContextVariable(movieR2dbcRepository.findAll().delayElements(Duration.ofMillis(500)), 1);
        System.out.println("reactiveDataDriver = " + reactiveDataDriver);
        model.addAttribute("movies", reactiveDataDriver);

//        model.addAttribute("moviesJpa", movieJpaRepository.findAll());
        return "home";
    }


    // TEXT_EVENT_STREAM : SSE(Server Sent Events)의 공식 미디어 유형
    // APPLICATION_OCTET_STREAM_VALUE : 서버 대 서버 / HTTP 클라이언트 (브라우저가 아닌 모든 것) 통신을위한 것
    // https://stackoverflow.com/questions/52098863/whats-the-difference-between-text-event-stream-and-application-streamjson
//    @GetMapping(value = "/list") // x : 한꺼번에 옴
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_STREAM_JSON_VALUE) // o : 하나하나 옴 ===> 얘가 그래도 제일 맞는 형태
//    @GetMapping(value = "/list", produces = MediaType.APPLICATION_NDJSON_VALUE) // x : 다운로드 됨
//    @GetMapping(value = "/list", produces = MediaType.TEXT_EVENT_STREAM) // 앞에 data 가 붙어서 옴
    @ResponseBody
    public Flux<Movie> getList(){
//        return reactiveMovieRepository.findAll().delayElements(Duration.ofMillis(200)); // o
//        return reactiveMovieRepository.findAll().log(); // 로그 남기기
        return movieR2dbcRepository.findAll().delayElements(Duration.ofMillis(200)); // o
    }
}
