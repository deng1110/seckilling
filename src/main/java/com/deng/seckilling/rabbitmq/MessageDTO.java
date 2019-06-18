package com.deng.seckilling.rabbitmq;

import lombok.*;

/**
 * mq的消息实体
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 19:36
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Long userId;

    private Long skuId;

    private String orderSecret;

    private Integer number;
}
