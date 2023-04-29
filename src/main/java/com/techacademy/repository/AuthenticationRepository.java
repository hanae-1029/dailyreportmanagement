package com.techacademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techacademy.entity.Authentication;

public interface AuthenticationRepository extends JpaRepository<Authentication, Integer> {

}
