package com.example.demo.users.infra.repository.jpa;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
@Accessors(chain = true)
class UserEntity {

    @Id
    private String id;

    @Column
    private String username;

    @Column
    private boolean activated;
}