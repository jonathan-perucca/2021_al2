package com.example.demo.users.infra.repository.jpa;

import com.example.demo.event.PublishDomainEvents;
import com.example.demo.users.domain.User;
import com.example.demo.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Repository
@RequiredArgsConstructor
public class JpaUserRepository implements UserRepository {

    private final UserRepo repo;
    private final UserMapper mapper;
    private final ApplicationEventPublisher publisher;

    @Override
    public String store(String username) {
        var entity = repo.save(new UserEntity()
                .setId(UUID.randomUUID().toString())
                .setUsername(username));

        return entity.getId();
    }

    @Override
    public int count() {
        return (int) repo.count();
    }

    @Override
    public List<User> findAll() {
        return repo.findAll().stream()
                .map(mapper::map)
                .collect(toList());
    }

    @Override
    public Optional<User> findOne(String userId) {
        return repo.findById(userId).map(mapper::map);
    }

    @PublishDomainEvents
    @Override
    public void save(User user) {
        repo.save(mapper.map(user));
    }
}