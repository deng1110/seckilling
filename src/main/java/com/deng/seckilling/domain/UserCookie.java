package com.deng.seckilling.domain;

import lombok.*;

import java.util.Date;

/**
 * 用户信息用于存储cookie
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/31 11:46
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserCookie {
    private Long id;

    private String userName;

    private String passWord;

    private String sex;

    private Long phoneNumber;

    private String identityCardId;

    private java.sql.Date birthday;

    private String rank;

    private String status;

    private Date loginTimen;

    public UserCookie(Date date) {
        this.loginTimen = date;
    }
}
