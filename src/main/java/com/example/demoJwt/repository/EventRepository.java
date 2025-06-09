package com.example.demoJwt.repository;

import com.example.demoJwt.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}