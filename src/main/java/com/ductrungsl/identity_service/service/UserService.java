package com.ductrungsl.identity_service.service;

import com.ductrungsl.identity_service.dto.request.UserCreationRequest;
import com.ductrungsl.identity_service.dto.request.UserUpdateRequest;
import com.ductrungsl.identity_service.dto.response.UserResponse;
import com.ductrungsl.identity_service.entity.User;
import com.ductrungsl.identity_service.exception.AppException;
import com.ductrungsl.identity_service.exception.ErrorCode;
import com.ductrungsl.identity_service.mapper.UserMapper;
import com.ductrungsl.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
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

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found")));
    }
}
