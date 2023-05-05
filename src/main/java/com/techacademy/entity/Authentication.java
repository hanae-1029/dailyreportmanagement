package com.techacademy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
@Table(name = "authentication")
public class Authentication {
    /** 権限用の列挙型 */
    public static enum Role {
        一般, 管理者

    }

    /** 社員番号 **/
    @Id
    @Column(name = "code", nullable = false, length = 20)
    @NotEmpty // 値が空ではないことをチェックする
    @Length(max = 20) // 値の長さをチェックする。max属性は許可する最大長を指定する
    private String code;

    /** パスワード **/
    @Column(name = "password", nullable = false, length = 255)
    @NotEmpty
    @Length(max = 255)
    private String password;

    /** 権限 **/
    @Column(name = "role", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;

    /** 従業員テーブルのID **/
    @OneToOne
    @Valid
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

}
