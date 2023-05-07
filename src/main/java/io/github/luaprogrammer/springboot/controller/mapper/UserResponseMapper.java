package io.github.luaprogrammer.springboot.controller.mapper;

import io.github.luaprogrammer.springboot.controller.response.UserResponse;
import io.github.luaprogrammer.springboot.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class UserResponseMapper {

    private ModelMapper mapper;

    public UserResponse toUserResponse(UserModel userModel) {
        return mapper.map(userModel, UserResponse.class);
    }

}