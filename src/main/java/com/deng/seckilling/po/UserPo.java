package com.deng.seckilling.po;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 14:14
 */
@Getter
@Setter
public class UserPo {

    private Long id;

    private String userName;

    private String passWord;

    private String sex;

    private String identityCardId;

    private String address;
}
