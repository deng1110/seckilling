package com.deng.seckilling.dao;

import com.deng.seckilling.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单子系统DAO层操作
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 20:18
 */
@Mapper
public interface OrderMapper {

    @Insert("insert into order_info (order_secret,goods_name,miaosha_price,number,user_id,order_time,shipping_address,status) values(#{orderSecret},#{goodsName},#{miaoshaoPrice},#{number},#{userId},#{orderTime},#{shippingAddress},#{status}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertOrder(Order order);

    @Select("SELECT * FROM order_info where id = #{orderId}")
    Order getOrder(Long orderId);

    @Select("SELECT * FROM order_info where user_id = #{userId}")
    List<Order> listOrder(Long userId);
}
