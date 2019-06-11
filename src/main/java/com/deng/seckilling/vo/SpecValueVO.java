package com.deng.seckilling.vo;

import lombok.*;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/10 9:54
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpecValueVO {

    /**
     * 规格Id
     */
    private Long specId;

    private String specName;

    private Long specValueId;

    /**
     * 规格值
     */
    private String specValue;
}
