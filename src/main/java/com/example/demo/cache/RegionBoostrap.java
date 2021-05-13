package com.example.demo.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
class RegionBoostrap {

    private final VeryLowRegionRepository repository;
    private final ApplicationEventPublisher publisher;

    @EventListener
    void on(ApplicationReadyEvent event) {
        Stream.of(
                new Region().setName("Paris").setCode("PR").setActive(true),
                new Region().setName("Toulouse").setCode("TL").setActive(true),
                new Region().setName("Lyon").setCode("LY").setActive(true),
                new Region().setName("Madrid").setCode("MD").setActive(false)
        ).forEach(repository::store);

        publisher.publishEvent(new RegionReady());
    }
}
