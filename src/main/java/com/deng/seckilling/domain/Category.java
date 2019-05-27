package com.deng.seckilling.domain;

import lombok.*;

import java.sql.Timestamp;

/**
 * 商品子系统——分类实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/10 12:45
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Category {

    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    public Category(Long id) {
        this.id = id;
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
