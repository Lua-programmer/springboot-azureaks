package io.github.luaprogrammer.springboot.controller.mapper;

import io.github.luaprogrammer.springboot.controller.request.UserRequest;
import io.github.luaprogrammer.springboot.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class UserRequestMapper {

    private ModelMapper mapper;

    public UserModel toUserModel(UserRequest userRequest) {
        return mapper.map(userRequest, UserModel.class);
    }

}
