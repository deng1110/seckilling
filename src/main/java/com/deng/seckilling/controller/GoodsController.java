package com.deng.seckilling.controller;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.domain.ShopInfo;
import com.deng.seckilling.domain.User;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.service.GoodsServcie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商铺子系统相关接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/9 22:34
 */
@RequestMapping("/goods")
@Slf4j
@Controller
public class GoodsController {

    @Resource
    private GoodsServcie goodsServcie;

    @RequestMapping("to_save_shop")
    @IsLogin(requiredRoot = true)
    public String to_saveShopInfo(Model model, User user) {
        model.addAttribute("user", user);
        return "save_shop";
    }

    @RequestMapping("to_save_category")
    public String to_saveCategory() {
        return "save_category";
    }

    @RequestMapping("to_goods_list")
    public String to_goods_list() {
        return "goods_list";
    }

    @RequestMapping("to_goods_detail")
    public String to_goods_detail() {
        return "goods_detail";
    }

    @RequestMapping("to_goods_list2")
    public String to_goods_list_2() {
        return "goods_list_2";
    }

    @RequestMapping("to_goods_detail2")
    public String to_goods_detail_2() {
        return "goods_detail_2";
    }

    /**
     * 增加店铺
     *
     * @param shopName 店铺名称
     * @return 店铺ID
     */
    @PostMapping("/saveshop")
    @ResponseBody
    public RpcResponse saveShopInfo(String shopName) {
        if (CheckDataUtils.isEmpty(shopName)) {
            log.warn("===>saveShopInfo user controller Permission denied error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        ShopInfo isExistShopInfo = goodsServcie.isExistShopService(shopName);
        if (!CheckDataUtils.isEmpty(isExistShopInfo)) {
            return RpcResponse.error(ErrorCode.SHOPNAME_EXIST_ERROR);
        }
        Long shopId;
        try {
            shopId = goodsServcie.saveShopService(new ShopInfo(shopName));
        } catch (Exception e) {
            log.error("===>saveShopInfo controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (CheckDataUtils.isEmpty(shopId)) {
            log.warn("===>saveShopInfo fail, save message:shopId:{},shopName:{}", shopId, shopName);
            return RpcResponse.error(ErrorCode.SAVE_SHOPINFO_ERROR);
        }

        log.info("===>saveShopInfo success, save message:shopId:{},shopName:{}", shopId, shopName);
        return RpcResponse.success(shopId);
    }

    /**
     * 修改店铺信息
     *
     * @param shopInfo 新店铺信息
     * @return 店铺ID
     */
    @PostMapping("modifyshopinfo")
    public RpcResponse modifyShopInfo(ShopInfo shopInfo) {
        if (CheckDataUtils.isEmpty(shopInfo.getShopName()) || CheckDataUtils.isEmpty(shopInfo.getId())) {
            log.warn("===>modifyShopInfo user controller Permission denied error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        ShopInfo isExistShopInfo = goodsServcie.isExistShopService(shopInfo.getId());
        if (CheckDataUtils.isEmpty(isExistShopInfo)) {
            return RpcResponse.error(ErrorCode.SHOPINFO_NOTEXIST_ERROR);
        }
        if (shopInfo.getShopName().equals(isExistShopInfo.getShopName())) {
            return RpcResponse.error(ErrorCode.MODIFYSHOPNAME_SAME_ERROR);
        }
        Long shopId;
        try {
            shopId = goodsServcie.modifyShopService(shopInfo);
        } catch (Exception e) {
            log.error("===>modifyShopInfo controller error:{}", e.getMessage());
            return RpcResponse.error(ErrorCode.SYSTEM_ERROR);
        }
        if (CheckDataUtils.isEmpty(shopId)) {
            log.warn("===>modifyShopInfo fail,save message:{}", shopInfo.toString());
            return RpcResponse.error(ErrorCode.SAVE_SHOPINFO_ERROR);
        }
        return RpcResponse.success(shopId);
    }

}
