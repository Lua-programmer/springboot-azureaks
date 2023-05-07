package io.github.luaprogrammer.springboot.service;

import io.github.luaprogrammer.springboot.controller.mapper.UserRequestMapper;
import io.github.luaprogrammer.springboot.controller.mapper.UserResponseMapper;
import io.github.luaprogrammer.springboot.controller.request.UserRequest;
import io.github.luaprogrammer.springboot.controller.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import io.github.luaprogrammer.springboot.model.UserModel;
import io.github.luaprogrammer.springboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.mapper = modelMapper;
    }

    public List<UserResponse> getUsers() {
        final var users = userRepository.findAll();
        return users.stream().map(u -> mapper.map(u, UserResponse.class)).collect(Collectors.toList());
    }

    public UserResponse getUser(Long userId) {
        final var userModel = userRepository.findById(userId).orElseThrow(() -> {
            log.error("User not found id {} ", userId);
            return new RuntimeException("User not found with id " + userId);
        });

        return mapper.map(userModel, UserResponse.class);
    }

    public UserResponse creatUserModel(UserRequest userRequest) {
        UserModel userModel = UserModel.builder()
                .userName(userRequest.getUserName())
                .userEmail(userRequest.getUserEmail())
                .build();
        final var save = userRepository.save(userModel);
        return mapper.map(save, UserResponse.class);
    }

    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        final var user = getUser(userId);
        user.setUserName(userRequest.getUserName());
        user.setUserEmail(userRequest.getUserEmail());

        final var save = userRepository.save(mapper.map(user, UserModel.class));
        return mapper.map(save, UserResponse.class);
    }

    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteById(userId);
    }
}
