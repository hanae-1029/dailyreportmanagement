package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {

    /** 社員番号 **/
    @Id
    @Column(name = "code", nullable = false, length = 20)
    private String code;

    /** パスワード **/
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    /** 権限 **/
    @Column(name = "role", nullable = false, length = 10)
    private String role;

    /** 従業員テーブルのID **/
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
