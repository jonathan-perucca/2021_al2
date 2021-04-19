package com.example.demo.users.infra.repository.jdbc;

import com.example.demo.users.domain.User;
import com.example.demo.users.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Repository
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper mapper;

    @Override
    public String store(String username) {
        var id = UUID.randomUUID().toString();
        jdbcTemplate.update("INSERT INTO users (id, username) VALUES (?, ?)", id, username);
        return id;
    }

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from users", Integer.class);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select id, username from users", mapper);
    }

    @Override
    public Optional<User> findOne(String userId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject("select id, username from users where id = ?", mapper, new Object[]{ userId })
        );
    }

    @Override
    public void save(User user) {

    }
}