package com.ductrungsl.identity_service.service;

import com.ductrungsl.identity_service.dto.request.UserCreationRequest;
import com.ductrungsl.identity_service.dto.request.UserUpdateRequest;
import com.ductrungsl.identity_service.dto.response.UserResponse;
import com.ductrungsl.identity_service.entity.User;
import com.ductrungsl.identity_service.enums.Role;
import com.ductrungsl.identity_service.exception.AppException;
import com.ductrungsl.identity_service.exception.ErrorCode;
import com.ductrungsl.identity_service.mapper.UserMapper;
import com.ductrungsl.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    // Trước khi có RequiredArgsConstructor và FieldDefaults (lombok)
//    @Autowired
//    private UserRepository userRepository;

    // Sau khi có Mapstruct (Mapper)
//    @Autowired
//    private UserMapper userMapper;

    // Sau khi có RequiredArgsConstructor và FieldDefaults (lombok)
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(UserCreationRequest request){
        // Trước khi có Mapper
//        User user = new User();

        if (userRepository.existsByUsername((request.getUsername())))
            throw new AppException(ErrorCode.USER_EXISTED);

//        if (request.getFirstName() == null || request.getLastName() == null)
//            throw  new AppException(ErrorCode.INFO_INVALID);

//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        // Sau khi có mapper
        User user = userMapper.toUser((request));

        // Mã hoá Bcript
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Khi tạo user mới, mặc định role là USER
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public UserResponse getMyInfo(){
        // Khi login, thông tin sẽ được lưu trong SecurityContextHolder
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")  // check role trước khi thực hiện method
    public List<User> getUsers(){
        log.info("In method getUsers");
        return userRepository.findAll();
    }

//    @PostAuthorize("hasRole('ADMIN')") // check role sau khi thực hiện methed, đúng mới return
    @PostAuthorize("returnObject.username == authentication.name")  // user lấy được thông tin của chính mình
    public UserResponse getUser(String id){
        log.info("In method getUser by ID");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }
}
