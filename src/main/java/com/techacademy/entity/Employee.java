package com.techacademy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    /** 主キー。自動生成 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 名前。20桁。null不許可 */
    @Column(length = 20, nullable = false)
    private String name;

    /** 削除フラグ **/
    @Column(name = "delete_flag")
    public Integer deleteflag;

    /** 登録日時 **/
    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdat;

    /** 更新日時 **/
    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedat;

    @OneToOne(mappedBy = "employee")
    private Authentication authentication;

    /** レコードが削除される前に行なう処理 */
    @PreRemove
    @Transactional
    private void preRemove() {
        // 認証エンティティからを切り離す
        if (authentication != null) {
            authentication.setEmployee(null);
        }
    }

}
