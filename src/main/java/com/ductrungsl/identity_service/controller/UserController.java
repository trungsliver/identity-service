package com.ductrungsl.identity_service.controller;

import com.ductrungsl.identity_service.dto.request.ApiResponse;
import com.ductrungsl.identity_service.dto.request.UserCreationRequest;
import com.ductrungsl.identity_service.dto.request.UserUpdateRequest;
import com.ductrungsl.identity_service.dto.response.UserResponse;
import com.ductrungsl.identity_service.entity.User;
import com.ductrungsl.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.DoubleStream;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
//    @Autowired
//    private UserService userService;
    UserService userService;
    private final AuthenticationManagerBuilder builder;

    // Tạo user mới
//    @PostMapping
//    User createUser(@RequestBody @Valid UserCreationRequest request){
//        return userService.createUser(request);
//    }
    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    // Lấy thông tin toàn bộ user
    @GetMapping
    List<User> getUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return userService.getUsers();
    }

//    @GetMapping
//    ApiResponse<List<UserResponse>> getUsers(){
//        return ApiResponse.builder()
//                .result(userService.getUsers())
//                .build();
//    }

    // Lấy thông tin user bằng ID
    @GetMapping("/{userId}")
//    User getUser(@PathVariable String userId){
//        return userService.getUser(userId);
//    }
    UserResponse getUser(@PathVariable String userId){
        return userService.getUser(userId);
    }

//    @GetMapping("/users/{myInfo}")
//    UserResponse getMyInfo(){
//        return userService.getMyInfo();
//    }

    @GetMapping("/myInfo")
    UserResponse getMyInfo(){
        return userService.getMyInfo();
    }

    // Chỉnh sửa thông tin bằng ID
    @PutMapping("/{userId}")
//    User updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
//        return userService.updateUser(userId, request);
//    }
    UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId, request);
    }

    // Xóa 1 user bằng ID
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
