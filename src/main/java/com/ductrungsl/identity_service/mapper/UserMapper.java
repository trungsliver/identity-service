package com.ductrungsl.identity_service.mapper;

import com.ductrungsl.identity_service.dto.request.UserCreationRequest;
import com.ductrungsl.identity_service.dto.request.UserUpdateRequest;
import com.ductrungsl.identity_service.dto.response.UserResponse;
import com.ductrungsl.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    // Tùy biến Map
//    @Mapping(source = "firstName", target = "lastName")
//    @Mapping(target = "", ignore = true)

    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user , UserUpdateRequest request);
}
