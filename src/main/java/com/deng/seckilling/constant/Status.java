package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.BaseEnum;
import lombok.Getter;

/**
 * 用户账户状态枚举类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 17:27
 */
public enum Status implements BaseEnum {

    //用户账户状态值
    NORMAL(1, "normal", "正常状态"),
    FROZEN(2, "frozen", "冻结状态"),
    INVALID(3, "invalid", "作废状态");

    @Getter
    private Integer code;

    @Getter
    private String value;

    @Getter
    private String desc;

    private Status(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }
}
