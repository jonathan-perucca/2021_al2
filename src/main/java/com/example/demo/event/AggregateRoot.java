package com.example.demo.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AggregateRoot {

    private final List<DomainEvent> events = new ArrayList<>();
    
    protected void registerEvent(DomainEvent event) {
        events.add(event);
    }

    public List<DomainEvent> domainEvents() {
        return Collections.unmodifiableList(events);
    }

    public void clearEvents() {
        events.clear();
    }
}