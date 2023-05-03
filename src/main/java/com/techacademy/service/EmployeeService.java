package com.techacademy.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private static final Date LocalDateTime = null;
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;

    }

    /** 全件検索して返す **/
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** 詳細 */
    public Employee getEmployee(Integer id) {
        // findById(id) 1件検索
        return employeeRepository.findById(id).get();
    }

    /** 新規登録 */
    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

}