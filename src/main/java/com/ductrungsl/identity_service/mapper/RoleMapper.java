package com.ductrungsl.identity_service.mapper;

import com.ductrungsl.identity_service.dto.request.PermissionRequest;
import com.ductrungsl.identity_service.dto.request.RoleRequest;
import com.ductrungsl.identity_service.dto.response.PermissionResponse;
import com.ductrungsl.identity_service.dto.response.RoleResponse;
import com.ductrungsl.identity_service.entity.Permission;
import com.ductrungsl.identity_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    // map từ Permission -> String (tên quyền)
    default Set<String> mapPermissions(Set<Permission> permissions) {
        if (permissions == null) return null;
        return permissions.stream()
                .map(Permission::getName) // hoặc getCode() tuỳ bạn
                .collect(Collectors.toSet());
    }

    RoleResponse toRoleResponse(Role role);
}
