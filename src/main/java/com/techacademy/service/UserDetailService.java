package com.techacademy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service

public class UserDetailService implements UserDetailsService {
    private final AuthenticationRepository authenticationRepository;

    // パスワードを暗号化
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;

    }

    @Override
    public UserDetails loadUserByUsername(String code) throws UsernameNotFoundException {
        Optional<Authentication> authentication = authenticationRepository.findById(code);

        if (!authentication.isPresent()) {
            throw new UsernameNotFoundException("Exception:Code Not Found");
        }
        return new UserDetail(authentication.get().getEmployee());
    }

}
