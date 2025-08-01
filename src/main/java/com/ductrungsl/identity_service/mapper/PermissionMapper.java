package com.ductrungsl.identity_service.mapper;

import com.ductrungsl.identity_service.dto.request.PermissionRequest;
import com.ductrungsl.identity_service.dto.request.UserCreationRequest;
import com.ductrungsl.identity_service.dto.request.UserUpdateRequest;
import com.ductrungsl.identity_service.dto.response.PermissionResponse;
import com.ductrungsl.identity_service.dto.response.UserResponse;
import com.ductrungsl.identity_service.entity.Permission;
import com.ductrungsl.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
//    void updateUser(@MappingTarget User user , UserUpdateRequest request);
}
