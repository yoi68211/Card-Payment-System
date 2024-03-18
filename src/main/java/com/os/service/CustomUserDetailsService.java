package com.os.service;

import com.os.entity.User;
import com.os.dto.CustomUserDetails;
import com.os.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        User user = userRepository.findByEmail(email);

        if (user == null){
            throw new UsernameNotFoundException("존재하지 않는 이메일 입니다 다시 확인해주세요.");
        }

        return new CustomUserDetails(user);
    }
}
