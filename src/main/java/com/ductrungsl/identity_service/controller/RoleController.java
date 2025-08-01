package com.ductrungsl.identity_service.controller;

import com.ductrungsl.identity_service.dto.request.ApiResponse;
import com.ductrungsl.identity_service.dto.request.PermissionRequest;
import com.ductrungsl.identity_service.dto.request.RoleRequest;
import com.ductrungsl.identity_service.dto.response.PermissionResponse;
import com.ductrungsl.identity_service.dto.response.RoleResponse;
import com.ductrungsl.identity_service.service.PermissionService;
import com.ductrungsl.identity_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create((request)))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll(){
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role){
        roleService.delete(role);
        return ApiResponse.<Void>builder().build();
    }

}
