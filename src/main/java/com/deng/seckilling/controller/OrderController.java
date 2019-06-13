package com.deng.seckilling.controller;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.domain.Order;
import com.deng.seckilling.domain.UserCookie;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.rpc.util.FenyeUtils;
import com.deng.seckilling.service.OrderService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 订单相关接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/13 10:25
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/to_order_detail")
    @IsLogin
    public String to_order_detail(Model model, UserCookie userCookie, Order order){
        model.addAttribute("user",userCookie);
        model.addAttribute("order",order);
        return "common/order_detail";
    }

    @RequestMapping("/to_list_order")
    @IsLogin
    public String to_list_order(Model model, UserCookie userCookie, Integer page, Integer size){
        model.addAttribute("user",userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<Order> orderPageInfo =orderService.listOrder(page,size,userCookie.getId());
        model.addAttribute("data", orderPageInfo.getList());
        model.addAttribute("url", "/order/to_list_order");
        FenyeUtils.setFenyeValue(model, orderPageInfo);
        return "common/list_order";
    }


    @GetMapping("/getorderdetail")
    @ResponseBody
    @IsLogin
    public RpcResponse getOrderDetail(String orderSecret){
        if(CheckDataUtils.isEmpty(orderSecret)){
            log.warn("===>getOrderDetail controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        Order order = orderService.getOrder(orderSecret);
        return RpcResponse.success(order);
    }


}
