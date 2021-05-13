package com.example.demo.cache;

import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@CacheConfig(cacheNames = "regions")
public class VeryLowRegionRepository {

    private final int SIMULATED_LATENCY_IN_MS = 2000;
    private final Map<String, Region> db = new HashMap<>();

    @CacheEvict(key = "#region.name")
    public Region store(Region region) {
        return db.put(region.getName(), region);
    }

    @Cacheable(unless = "!#result.active")
    @SneakyThrows
    public Optional<Region> findByName(String name) {
        Thread.sleep(SIMULATED_LATENCY_IN_MS);
        return Optional.ofNullable(db.get(name));
    }

    @Cacheable
    @SneakyThrows
    public List<Region> findAll() {
        Thread.sleep(SIMULATED_LATENCY_IN_MS);
        return new ArrayList<>(db.values());
    }
}
