package com.example.demo.cache;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegionReader {

    private final VeryLowRegionRepository repository;

    @EventListener
    void on(RegionReady event) {
        log.info("Asking to find all regions");
        var regions = repository.findAll();
        log.info("Found all regions");

        log.info("Asking to find Paris region");
        var paris = repository.findByName("Paris");
        log.info("Found paris region");

        // here, data should be cached

        log.info("2nd time asking to find all regions");
        regions = repository.findAll();
        log.info("2nd time Found all regions");

        log.info("2nd time asking to find Paris region");
        paris = repository.findByName("Paris");
        log.info("2nd time found paris region");

        log.info("Updating paris region");
        repository.store(paris.get().setCode("PRs"));
        log.info("Reading again paris region should not hit the cache");
        repository.findByName("Paris");
        log.info("3rd time found paris region");

        log.info("4th time asking to find Paris region");
        paris = repository.findByName("Paris");
        log.info("4th time found paris region");

        log.info("1st time asking to find Madrid region");
        var madrid = repository.findByName("Madrid");
        log.info("1st time found Madrid region");

        log.info("2nd time asking to find Madrid region");
        madrid = repository.findByName("Madrid");
        log.info("2nd time found Madrid region");
    }
}
