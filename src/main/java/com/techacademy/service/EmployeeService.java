package com.techacademy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techacademy.entity.Employee;
import com.techacademy.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;

    }

    /** 全件検索して返す **/
    public List<Employee> getEmployeeList() {
        // リポジトリのfindAllメソッドを呼び出す
        return employeeRepository.findAll();
    }

    /** 詳細 更新 */
    public Employee getEmployee(Integer id) {

        // findById(id) 1件検索
        return employeeRepository.findById(id).get();
    }

    /** 新規登録 */

    @Transactional
    public Employee saveEmployee(Employee employee) {
        // パスワード暗号化
        String password = passwordEncoder.encode("password");
        employee.getAuthentication().setPassword(passwordEncoder.encode(password));

        return employeeRepository.save(employee);

    }

    /** 削除 */
    public void delete(Integer id) {
        // idを指定してデータベースからデータを削除する
        employeeRepository.deleteById(id);
    }
}
