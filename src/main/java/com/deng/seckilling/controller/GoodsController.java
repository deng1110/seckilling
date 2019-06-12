package com.deng.seckilling.controller;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.constant.Sale;
import com.deng.seckilling.domain.*;
import com.deng.seckilling.dto.SkuDTO;
import com.deng.seckilling.dto.SpuDTO;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.redis.RedisClient;
import com.deng.seckilling.rpc.redis.RedisLocker;
import com.deng.seckilling.rpc.util.*;
import com.deng.seckilling.service.GoodsServcie;
import com.deng.seckilling.service.UserService;
import com.deng.seckilling.vo.SkuVO;
import com.deng.seckilling.vo.SpecValueVO;
import com.deng.seckilling.vo.SpuVO;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private UserService userService;

    @Resource
    private RedisClient redisClient;

    @Resource
    private RedisLocker redisLocker;

    @RequestMapping("to_save_shop")
    @IsLogin(requiredRoot = true)
    public String to_save_shop(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/save_shop";
    }

    @RequestMapping("to_modify_shop")
    @IsLogin(requiredRoot = true)
    public String to_modify_shop(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/modify_shop";
    }

    @RequestMapping("to_list_shop")
    @IsLogin(requiredRoot = true)
    public String to_list_shop(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<ShopInfo> shopInfoPageInfo = goodsServcie.listShopInfo(page, size, new ShopInfo());
        model.addAttribute("data", shopInfoPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_shop");
        FenyeUtils.setFenyeValue(model, shopInfoPageInfo);
        return "root/list_shop";
    }

    @RequestMapping("/to_save_spu")
    @IsLogin(requiredRoot = true)
    public String to_save_spu(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/save_spu";
    }

    @RequestMapping("/to_list_spu")
    @IsLogin(requiredRoot = true)
    public String to_list_spu(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<SpuVO> spuVOPageInfo = goodsServcie.listSpuVO(page, size, new Spu());
        model.addAttribute("data", spuVOPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_spu");
        FenyeUtils.setFenyeValue(model, spuVOPageInfo);
        return "root/list_spu";
    }

    @RequestMapping("to_save_category")
    @IsLogin(requiredRoot = true)
    public String to_save_category(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/save_category";
    }

    @RequestMapping("to_list_category")
    @IsLogin(requiredRoot = true)
    public String to_list_category(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<Category> categoryPageInfo = goodsServcie.listCategoryService(page, size, new Category());
        model.addAttribute("data", categoryPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_category");
        FenyeUtils.setFenyeValue(model, categoryPageInfo);
        return "root/list_category";
    }

    @RequestMapping("/to_save_brand")
    @IsLogin(requiredRoot = true)
    public String to_save_brand(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/save_brand";
    }

    @RequestMapping("to_list_brand")
    @IsLogin(requiredRoot = true)
    public String to_list_brand(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<Brand> categoryPageInfo = goodsServcie.listBrandService(page, size, new Brand());
        model.addAttribute("data", categoryPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_brand");
        FenyeUtils.setFenyeValue(model, categoryPageInfo);
        return "root/list_brand";
    }

    @RequestMapping("to_save_spec")
    @IsLogin(requiredRoot = true)
    public String to_save_spec(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/save_spec";
    }

    @RequestMapping("to_list_spec")
    @IsLogin(requiredRoot = true)
    public String to_list_spec(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<Specification> specificationPageInfo = goodsServcie.listSpecService(page, size, new Specification());
        model.addAttribute("data", specificationPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_spec");
        FenyeUtils.setFenyeValue(model, specificationPageInfo);
        return "root/list_spec";
    }

    @RequestMapping("to_save_spu_spec")
    @IsLogin(requiredRoot = true)
    public String to_save_spu_spec(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "root/save_spu_spec";
    }

    @RequestMapping("to_list_spu_spec")
    @IsLogin(requiredRoot = true)
    public String to_list_spu_spec(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<SpuSpec> spuSpecPageInfo = goodsServcie.listSpuSpecService(page, size, new SpuSpec());
        model.addAttribute("data", spuSpecPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_spec");
        FenyeUtils.setFenyeValue(model, spuSpecPageInfo);
        return "root/list_spu_spec";
    }

    @RequestMapping("to_save_spec_value")
    @IsLogin
    public String to_save_spec_value(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "common/save_spec_value";
    }

    @RequestMapping("to_list_spec_value")
    @IsLogin
    public String to_list_spec_value(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<SpecValueVO> specValueVOPageInfo = goodsServcie.listSpecValueVOService(page, size);
        model.addAttribute("data", specValueVOPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_spec_value");
        FenyeUtils.setFenyeValue(model, specValueVOPageInfo);
        return "common/list_spec_value";
    }

    @RequestMapping("to_save_sku")
    @IsLogin
    public String to_save_sku(Model model, UserCookie userCookie) {
        model.addAttribute("user", userCookie);
        return "common/save_sku";
    }

    @RequestMapping("to_list_sku")
    @IsLogin
    public String to_list_sku(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<SkuVO> skuVOPageInfo = goodsServcie.listSkuVO(page, size, userCookie.getId());
        model.addAttribute("data", skuVOPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_sku");
        FenyeUtils.setFenyeValue(model, skuVOPageInfo);
        return "common/list_sku";
    }

    /**
     * 展示商品列表
     */
    @RequestMapping("to_goods_list")
    @IsLogin
    public String to_goods_list(Model model, UserCookie userCookie, Integer page, Integer size) {
        model.addAttribute("user", userCookie);
        page = CheckDataUtils.isEmpty(page) ? DefaultValue.FENYE_FIRSTPAGE_VALUE : page;
        size = CheckDataUtils.isEmpty(size) ? DefaultValue.FENYE_PAGESIZE_VALUE : size;
        PageInfo<SkuVO> skuVOPageInfo = goodsServcie.listSkuVO(page, size);
        model.addAttribute("data", skuVOPageInfo.getList());
        model.addAttribute("url", "/goods/to_goods_list");
        FenyeUtils.setFenyeValue(model, skuVOPageInfo);
        return "common/goods_list";
    }

    /**
     * 展示某个商品细节
     * 种上缓存，为接下来秒杀削峰，限流做准备
     */
    @RequestMapping("to_goods_detail")
    @IsLogin
    public String to_goods_detail(Model model, UserCookie userCookie, Long skuId) {
        model.addAttribute("user", userCookie);
        skuId = CheckDataUtils.isEmpty(skuId) ? 0L : skuId;

        SkuVO skuVO = goodsServcie.getSkuVOService(skuId);

        //如果redis缓存有值则信任这个值，认为其是最新的值，从缓存中读
        String stockStr = redisClient.get(skuId + DefaultValue.STOCK_SUFFIX_VALUE);
        if (!CheckDataUtils.isEmpty(stockStr)) {
            Integer stock = Integer.parseInt(stockStr);
            skuVO.setStock(stock);
        }

        model.addAttribute("goods", skuVO);

        long startAt = DateUtils.strToDate(skuVO.getStartTime()).getTime();
        long endAt = DateUtils.strToDate(skuVO.getEndTime()).getTime();
        long now = System.currentTimeMillis();

        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if (now < startAt) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int) ((startAt - now) / 1000);
        } else if (now > endAt) {//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        } else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", miaoshaStatus);
        model.addAttribute("remainSeconds", remainSeconds);

        //查看detail的时候商品库存入缓存
        redisClient.set(skuId + DefaultValue.STOCK_SUFFIX_VALUE, skuVO.getStock().toString());

        return "common/goods_detail";
    }

    /**
     * 秒杀点，核心！在detail的时候种上缓存了
     *
     * @param model      内嵌模型
     * @param userCookie 自定义内嵌用户信息
     * @param skuId      商品skuId
     * @param number     购买数量
     * @return 订单ID
     */
    @PostMapping("/miaosha")
    @IsLogin
    public String miaosha(Model model, UserCookie userCookie, Long skuId, Integer number) {
        model.addAttribute("user", userCookie);//供展示界面用户基本信息。

        if (CheckDataUtils.isEmpty(skuId)) {//指定被购买的sku
            return "common/miaoshao_fail";
        }

        number = CheckDataUtils.isEmpty(number) ? 1 : number;//number校验，默认购买一件

        //限流
        //先在非加锁状态验证库存（较小代价做出最快判断），此处是库存的前置判断，类似于单例的double check双重检查锁
        Integer stock = goodsServcie.getSkuStock(skuId);
        if (CheckDataUtils.isEmpty(stock) || stock < number) {
            return "common/miaoshao_fail";
        }

        //分布式锁
        redisLocker.lock(userCookie.getUserName());
        String orderSecret = goodsServcie.miaoshaServcie(userCookie, skuId, number);
        redisLocker.unlock(userCookie.getUserName());

        if (CheckDataUtils.isEmpty(orderSecret)) {
            log.error("===>create order fail,message, userName:{}, skuId:{}, purchase number:{}", userCookie.getUserName(), skuId, number);
            return "common/miaoshao_fail";
        }

        model.addAttribute("orderSecret", orderSecret);
        return "common/order";
    }


    /**
     * 增加店铺
     *
     * @param shopName 店铺名称
     * @return 店铺ID
     */
    @PostMapping("/saveshop")
    @ResponseBody
    @IsLogin(requiredRoot = true, requiredController = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse saveShopInfo(String shopName, Long userId) {
        if (CheckDataUtils.isEmpty(shopName) || CheckDataUtils.isEmpty(userId)) {
            log.warn("===>saveShopInfo controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        ShopInfo isExistShopInfo = goodsServcie.isExistShopService(shopName);
        if (!CheckDataUtils.isEmpty(isExistShopInfo)) {
            return RpcResponse.error(ErrorCode.SHOPNAME_EXIST_ERROR);
        }

        List<User> isExistUser = userService.queryUsersByConditionService(new User(userId));
        if (CheckDataUtils.isEmpty(isExistUser)) {
            return RpcResponse.error(ErrorCode.USER_NOTEXIT_ERROR);
        }

        Long shopId = goodsServcie.saveShopService(new ShopInfo(shopName, userId));
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
    @PostMapping("modifyshop")
    @ResponseBody
    @IsLogin(requiredRoot = true, requiredController = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse modifyShopInfo(ShopInfo shopInfo) {
        if (CheckDataUtils.isEmpty(shopInfo.getShopName()) || CheckDataUtils.isEmpty(shopInfo.getId())) {
            log.warn("===>modifyShopInfo controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        ShopInfo isExistShopInfo = goodsServcie.isExistShopService(shopInfo.getId());
        if (CheckDataUtils.isEmpty(isExistShopInfo)) {
            return RpcResponse.error(ErrorCode.SHOPINFO_NOTEXIST_ERROR);
        }
        if (shopInfo.getShopName().equals(isExistShopInfo.getShopName())) {
            return RpcResponse.error(ErrorCode.MODIFYSHOPNAME_SAME_ERROR);
        }
        if (!CheckDataUtils.isEmpty(goodsServcie.isExistShopService(shopInfo.getShopName()))) {
            return RpcResponse.error(ErrorCode.SHOPNAME_EXIST_ERROR);
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

        log.info("===>modifyShopInfo success,save message:{}", shopInfo.toString());
        return RpcResponse.success(shopId);
    }

    @PostMapping("/savespu")
    @IsLogin(requiredRoot = true, requiredController = true, errorMessage = "权限不足，只有管理员可操作")
    @ResponseBody
    public RpcResponse saveSpu(SpuDTO spuDTO) {
        if (CheckDataUtils.isEmpty(spuDTO) || CheckDataUtils.isEmpty(spuDTO.getSpuNo()) ||
                CheckDataUtils.isEmpty(spuDTO.getGoodsName()) || CheckDataUtils.isEmpty(spuDTO.getLowPrice()) ||
                CheckDataUtils.isEmpty(spuDTO.getCategoryName()) || CheckDataUtils.isEmpty(spuDTO.getBrandName())) {
            log.warn("===>saveSpu controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        Brand brand = goodsServcie.isExistBrandService(spuDTO.getBrandName());
        Category category = goodsServcie.isExistCategoryServcie(spuDTO.getCategoryName());
        if (CheckDataUtils.isEmpty(brand)) {
            return RpcResponse.error(ErrorCode.BRAND_NOTEXIST_ERROR);
        }
        if (CheckDataUtils.isEmpty(category)) {
            return RpcResponse.error(ErrorCode.CATEGORY_NOTEXIST_ERROR);
        }
        Spu spu = goodsServcie.isExistSpuService(spuDTO.getSpuNo());
        if (!CheckDataUtils.isEmpty(spu)) {
            return RpcResponse.error(ErrorCode.SPUNO_EXIST_ERROR);
        }
        spu = new Spu(category.getId(), brand.getId());
        DataUtils.entityTransform(spuDTO, spu);
        Long spuId = goodsServcie.saveSpuServcie(spu);
        if (CheckDataUtils.isEmpty(spuId)) {
            log.warn("===>saveSpu fail, save message:{}", spu.toString());
            return RpcResponse.error(ErrorCode.SAVE_SPU_ERROR);
        }

        log.info("===>saveSpu success, save message:{}", spu.toString());
        return RpcResponse.success(spuId);
    }

    @PostMapping("/savecategory")
    @IsLogin(requiredRoot = true, requiredController = true, errorMessage = "权限不足，只有管理员可操作")
    @ResponseBody
    public RpcResponse saveCategory(Category category) {
        if (CheckDataUtils.isEmpty(category) || CheckDataUtils.isEmpty(category.getCategoryName())) {
            log.warn("===>saveCategory controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }
        Category isExistCategory = goodsServcie.isExistCategoryServcie(category.getCategoryName());
        if (!CheckDataUtils.isEmpty(isExistCategory)) {
            log.info("===>categoryName:{} has already exist", category.getCategoryName());
            return RpcResponse.error(ErrorCode.CATEGORY_EXIST_ERROR);
        }

        Long categoryId = goodsServcie.saveCategoryInfoService(category);
        if (CheckDataUtils.isEmpty(categoryId)) {
            log.warn("===>saveCategory fail, save message:{}", category.toString());
            return RpcResponse.error(ErrorCode.SAVE_CATEGORY_ERROR);
        }

        log.info("===>saveCategory success, save message:{}", category.toString());
        return RpcResponse.success(category.getId());
    }

    @PostMapping("savebrand")
    @ResponseBody
    @IsLogin(requiredController = true, requiredRoot = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse saveBrand(Brand brand) {
        if (CheckDataUtils.isEmpty(brand) || CheckDataUtils.isEmpty(brand.getBrandName())) {
            log.warn("===>saveBrand controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        Brand isExistBrand = goodsServcie.isExistBrandService(brand.getBrandName());
        if (!CheckDataUtils.isEmpty(isExistBrand)) {
            return RpcResponse.error(ErrorCode.BRAND_EXIST_ERROR);
        }

        Long brandId = goodsServcie.saveBrandServcie(brand);
        if (CheckDataUtils.isEmpty(brandId)) {
            log.warn("===>saveBrand fail, save message:{}", brand.toString());
            return RpcResponse.error(ErrorCode.SAVE_BRAND_ERROR);
        }

        log.info("===>saveBrand success, save message:{}", brand.toString());
        return RpcResponse.success(brand.getId());
    }

    /**
     * 增加规格
     *
     * @param specNo   规格编号
     * @param specName 规格名称
     * @return 规格ID
     */
    @PostMapping("savespec")
    @ResponseBody
    @IsLogin(requiredController = true, requiredRoot = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse saveSpec(String specNo, String specName) {
        if (CheckDataUtils.isEmpty(specNo) || CheckDataUtils.isEmpty(specName)) {
            log.warn("===>saveSpec controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        Specification isExistSpec = null;
        isExistSpec = goodsServcie.isExistSpecService2(specNo);
        if (!CheckDataUtils.isEmpty(isExistSpec)) {
            return RpcResponse.error(ErrorCode.SPECNO_EXIST_ERROR);
        }
        isExistSpec = goodsServcie.isExistSpecService(specName);
        if (!CheckDataUtils.isEmpty(isExistSpec)) {
            return RpcResponse.error(ErrorCode.SPEC_EXIST_ERROR);
        }

        Long specId = goodsServcie.saveSpecService(new Specification(specNo, specName));
        if (CheckDataUtils.isEmpty(specId)) {
            log.warn("===>saveSpec fail, save message:specNo:{},specName:{}", specNo, specName);
            return RpcResponse.error(ErrorCode.SAVE_SPEC_ERROR);
        }

        log.info("===>saveSpec success, save message:specNo:{},specName:{}", specNo, specName);
        return RpcResponse.success(specId);
    }

    /**
     * 增加SPU和规格的关联关系
     *
     * @param spuId  SPUID
     * @param specId 规格ID
     * @return 关联ID
     */
    @PostMapping("savespuspec")
    @ResponseBody
    @IsLogin(requiredController = true, requiredRoot = true, errorMessage = "权限不足，只有管理员可操作")
    public RpcResponse saveSpuSpec(Long spuId, Long specId) {
        if (CheckDataUtils.isEmpty(spuId) || CheckDataUtils.isEmpty(specId)) {
            log.warn("===>saveSpuSpec controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        Spu isExistSpu = goodsServcie.isExistSpuService(spuId);
        if (CheckDataUtils.isEmpty(isExistSpu)) {
            return RpcResponse.error(ErrorCode.SPUID_NOTEXIST_ERROR);
        }

        Specification isExistSpeC = goodsServcie.isExistSpecService(specId);
        if (CheckDataUtils.isEmpty(isExistSpeC)) {
            return RpcResponse.error(ErrorCode.SPECID_NOTEXIST_ERROR);
        }

        Long spuSpecId = goodsServcie.saveSpuSpec(new SpuSpec(spuId, specId));
        if (CheckDataUtils.isEmpty(spuSpecId)) {
            log.warn("===>saveSpuSpec fail, save message:spuId:{},specId:{}", spuId, specId);
            return RpcResponse.error(ErrorCode.SAVE_SPUSPEC_ERROR);
        }

        log.info("===>saveSpuSpec success, save message:spuId:{},specId:{}", spuId, specId);
        return RpcResponse.success(spuSpecId);
    }

    @PostMapping("savespecvalue")
    @ResponseBody
    @IsLogin
    public RpcResponse saveSpecValue(String specNo, String specValue) {
        if (CheckDataUtils.isEmpty(specNo) || CheckDataUtils.isEmpty(specValue)) {
            log.warn("===>saveSpec controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        Specification isExistSpec = goodsServcie.isExistSpecService2(specNo);
        if (CheckDataUtils.isEmpty(isExistSpec)) {
            return RpcResponse.error(ErrorCode.SPECNO_NOTEXIST_ERROR);
        }

        SpecValue isExistSpecName = goodsServcie.isExistSpecValue(specValue);
        if (!CheckDataUtils.isEmpty(isExistSpecName)) {
            return RpcResponse.error(ErrorCode.SPECVALUE_EXIST_ERROR);
        }

        Long specValueId = goodsServcie.saveSpecValue(new SpecValue(isExistSpec.getId(), specValue));
        if (CheckDataUtils.isEmpty(specValueId)) {
            log.warn("===>saveSpecValue fail, save message:specNo:{},specValue:{}", specNo, specValue);
            return RpcResponse.error(ErrorCode.SAVE_SPECVALUE_ERROR);
        }
        log.warn("===>saveSpecValue success, save message:specNo:{},specValue:{}", specNo, specValue);
        return RpcResponse.success(specValueId);
    }

    /**
     * 保存SKU
     *
     * @param skuDTO 保存SKU参数接收实体
     * @return Sku主键ID
     */
    @PostMapping("/savesku")
    @IsLogin
    @ResponseBody
    public RpcResponse saveSku(SkuDTO skuDTO) {
        if (CheckDataUtils.isEmpty(skuDTO) || CheckDataUtils.isEmpty(skuDTO.getSkuNo()) ||
                CheckDataUtils.isEmpty(skuDTO.getSpuNo()) || CheckDataUtils.isEmpty(skuDTO.getPrice()) ||
                CheckDataUtils.isEmpty(skuDTO.getStock()) || CheckDataUtils.isEmpty(skuDTO.getIsSale()) ||
                CheckDataUtils.isEmpty(skuDTO.getShopName()) || !EnumUtils.isEnumCode(Sale.class, skuDTO.getIsSale()) ||
                CheckDataUtils.isEmpty(skuDTO.getMiaoshaPrice()) || CheckDataUtils.isEmpty(skuDTO.getStartTime()) ||
                CheckDataUtils.isEmpty(skuDTO.getEndTime())) {
            log.warn("===>saveSku controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        Spu isExistSpu = goodsServcie.isExistSpuService(skuDTO.getSpuNo());
        if (CheckDataUtils.isEmpty(isExistSpu)) {
            return RpcResponse.error(ErrorCode.SPUNO_NOTEXIST_ERROR);
        }

        Sku isExistSku = goodsServcie.isExistSku(skuDTO.getSkuNo());
        if (!CheckDataUtils.isEmpty(isExistSku)) {
            return RpcResponse.error(ErrorCode.SKUNO_EXIST_ERROR);
        }

        if (skuDTO.getPrice() < isExistSpu.getLowPrice()) {
            return RpcResponse.error(ErrorCode.ILLEGAL_PRICE_ERROR);
        }

        UserCookie userCookie = userService.getUserFromRequest();
        if (CheckDataUtils.isEmpty(userCookie)) {
            return RpcResponse.error(ErrorCode.NOT_LOGIN_ERROR);
        }

        ShopInfo isExistShop = goodsServcie.isExistShopService(skuDTO.getShopName());
        if (CheckDataUtils.isEmpty(isExistShop)) {
            return RpcResponse.error(ErrorCode.SHOPNAME_NOTEXIST_ERROR);
        }
        if (!userCookie.getId().equals(isExistShop.getUserId())) {
            return RpcResponse.error(ErrorCode.ILLEGAL_SHOPNAME_ERROR);
        }

        Long skuId = goodsServcie.saveSkuDTO(isExistSpu, isExistShop.getId(), skuDTO);
        if (CheckDataUtils.isEmpty(skuId)) {
            log.warn("===>saveSku fail, save message:{}", skuDTO.toString());
            return RpcResponse.error(ErrorCode.SAVE_SKU_ERROR);
        }

        log.info("===>saveSku success, save message:{}", skuDTO.toString());
        return RpcResponse.success(skuId);
    }

}
