package com.deng.seckilling.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@Getter
@Setter
@ToString
public class UserPo {

    private Long id;

    private String userName;

    private String passWord;

    private String sex;

    private Long phoneNumber;

    private String identityCardId;

    private Date birthday;

    private String address;

    private String rank;

    private String status;
}
