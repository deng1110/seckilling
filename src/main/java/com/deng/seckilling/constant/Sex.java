package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.BaseEnum;
import lombok.Getter;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 21:28
 */
public enum Sex implements BaseEnum {

    //性别枚举值
    MALE(1, DefaultValue.SEX_VALUE_MALE, "男"),
    FEMALE(2, DefaultValue.SEX_VALUE_FEMALE, "女");

    @Getter
    private Integer code;

    @Getter
    private String value;

    @Getter
    private String desc;

    private Sex(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }
}
