package com.example.demo.users.infra.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepo extends JpaRepository<UserEntity, String> {
}