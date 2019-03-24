package com.deng.seckilling.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String userName;

    private String passWord;

    private String sex;

    private Long phoneNumber;

    private String identityCardId;

    private Date birthday;

    private String rank;

    private String status;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(Long id) {
        this.id = id;
    }
}
