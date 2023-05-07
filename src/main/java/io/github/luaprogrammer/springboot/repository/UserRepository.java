package io.github.luaprogrammer.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.luaprogrammer.springboot.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {}
