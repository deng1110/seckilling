package com.deng.seckilling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/4/8 22:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseUserInfoDTO {

    private String userName;

    private String passWord;

    private Integer sex;

    private Long phoneNumber;

    private Integer rank;

}
