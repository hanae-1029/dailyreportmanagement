package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Authentication;
import com.techacademy.repository.AuthenticationRepository;

@Service
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    public AuthenticationService(AuthenticationRepository repository) {
        this.authenticationRepository = repository;

    }

    /** 全件検索して返す */
    public List<Authentication> getAuthenticationsList() {
        // リポジトリのfindAllメソッドを呼び出す
        return authenticationRepository.findAll();
    }

}
