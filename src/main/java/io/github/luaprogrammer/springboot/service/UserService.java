package io.github.luaprogrammer.springboot.service;

import org.springframework.stereotype.Service;

import io.github.luaprogrammer.springboot.dto.UserDto;
import io.github.luaprogrammer.springboot.model.UserModel;
import io.github.luaprogrammer.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Iterable<UserModel> getUsers() {
        return userRepository.findAll();
    }

    public UserModel getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> { 
            log.error("User not found id {} ", userId);
            return new RuntimeException("User not found with id " + userId); 
        });
    }

    public UserModel creatUserModel(UserDto userDto) {
        UserModel userModel = UserModel.builder()
                .userName(userDto.getUsername())
                .userEmail(userDto.getUserEmail())
                .build();
        return userRepository.save(userModel);
    }

    public UserModel updatUser(Long userId, UserDto userDto) {
        UserModel userModel = getUser(userId);
        userModel.setUserName(userDto.getUsername());
        userModel.setUserEmail(userDto.getUserEmail());
        return userRepository.save(userModel);
    }

    public void deleteUser(Long userId) {
        getUser(userId);
        userRepository.deleteByUserId(userId);
    }
}
