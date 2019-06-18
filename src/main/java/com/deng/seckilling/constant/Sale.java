package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.constant.BaseEnum;
import lombok.Getter;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/10 14:48
 */
public enum Sale implements BaseEnum {

    ON_SALE(1, "on_sale", "在架"),
    NOT_SALE(2, "not_sale", "下架");

    @Getter
    private Integer code;

    @Getter
    private String value;

    @Getter
    private String desc;

    private Sale(Integer code, String value, String desc) {
        this.code = code;
        this.value = value;
        this.desc = desc;
    }

}
