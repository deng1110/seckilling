package com.deng.seckilling.service;

import com.deng.seckilling.dao.OrderMapper;
import com.deng.seckilling.domain.Order;
import com.deng.seckilling.domain.Sku;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.vo.SkuVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 订单Service
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 21:04
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private GoodsServcie goodsServcie;

    /**
     * 秒杀后，mysql库存减少，生成订单
     *
     * @param userId      购买者ID
     * @param skuId       商品skuId
     * @param orderSecret 订单的唯一标识
     */
    @Transactional
    public void afterMiaosha(Long userId, Long skuId, String orderSecret, Integer number) {
        //mysql库存减少
        SkuVO skuVO = goodsServcie.getSkuVOService(skuId);
        Integer newStock = skuVO.getStock() - number;
        Sku sku = new Sku(skuId);
        sku.setStock(newStock);
        Long updateSkuId = goodsServcie.updateSku(sku);
        if (CheckDataUtils.isEmpty(updateSkuId)) {
            return;
        }
        //生成订单
        Order order = new Order();
        order.setGoodsName(skuVO.getSkuName());
        order.setMiaoshaoPrice(skuVO.getMiaoshaPrice());
        order.setNumber(number);
        order.setUserId(userId);
        order.setOrderSecret(orderSecret);
        order.setShippingAddress(" ");
        order.setStatus(0);
        saveOrder(order);
    }

    /**
     * 生成订单
     *
     * @param order 订单实体
     * @return 订单ID
     */
    public Long saveOrder(Order order) {
        orderMapper.insertOrder(order);
        return order.getId();
    }
}
