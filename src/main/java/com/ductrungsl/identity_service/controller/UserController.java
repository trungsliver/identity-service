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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
//    @Autowired
//    private UserService userService;
    UserService userService;

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
        return userService.getUsers();
    }

    // Lấy thông tin user bằng ID
    @GetMapping("/{userId}")
//    User getUser(@PathVariable String userId){
//        return userService.getUser(userId);
//    }
    UserResponse getUser(@PathVariable String userId){
        return userService.getUser(userId);
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
