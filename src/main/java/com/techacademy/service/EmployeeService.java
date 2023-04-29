package com.techacademy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;

    }

    /** 全件検索して返す **/
    public List<Employee> getEmployeesList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

}
