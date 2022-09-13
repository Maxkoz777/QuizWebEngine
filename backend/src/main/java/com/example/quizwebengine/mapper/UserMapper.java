package com.example.quizwebengine.mapper;

import com.example.quizwebengine.dto.UserDTO;
import com.example.quizwebengine.model.user_info.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserByDto(@MappingTarget User user, UserDTO userDTO);

    UserDTO userToUserDto(User user);

}
