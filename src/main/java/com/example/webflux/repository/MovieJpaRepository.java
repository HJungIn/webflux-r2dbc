package com.example.webflux.repository;

import com.example.webflux.domain.MovieJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface MovieJpaRepository extends JpaRepository<MovieJpa, Long> {
}
