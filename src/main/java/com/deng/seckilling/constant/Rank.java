package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.constant.BaseEnum;
import lombok.Getter;

/**
 * 权限枚举类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 17:34
 */
public enum Rank implements BaseEnum {

    //权限枚举值
    ADMIN(1, "admin", "管理员"),
    BUYER(2, "buyer", "买家"),
    SELLER(3, "seller", "卖家");
    @Getter
    private Integer code;

    @Getter
    private String value;

    @Getter
    private String desc;

    private Rank(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }
}
