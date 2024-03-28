package com.os.service;

import com.os.dto.UserResponse;
import com.os.entity.User;
import com.os.repository.UserRepository;
import com.os.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId));
    }


    public void updatePassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.ifPresent(user -> {
            User updatedUser = User.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .password(passwordEncoder.encode(newPassword))
                    .build();
            userRepository.save(updatedUser);
        });
    }


    public UserResponse getUserResponseById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponse userResponse = new UserResponse();
            userResponse.setId(user.getId());
            userResponse.setUsername(user.getUsername());
            userResponse.setEmail(user.getEmail());
            userResponse.setPassword(user.getPassword());
            userResponse.setRole(user.getRole());
            return userResponse;
        } else {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
    }

    /*
        @method : findId
        @desc : 시큐리티에서 user 정보를 가져오는 메서드
        @author : 김성민
    */
    public User findId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.orElse(null);
    }
}