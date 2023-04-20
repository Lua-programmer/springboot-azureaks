package io.github.luaprogrammer.springboot.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.luaprogrammer.springboot.model.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Long> {

    void deleteByUserId(Long userId);
    
}
