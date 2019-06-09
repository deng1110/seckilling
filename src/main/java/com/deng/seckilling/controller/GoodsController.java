package com.deng.seckilling.controller;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.DefaultValue;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.domain.*;
import com.deng.seckilling.dto.SpuDTO;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.rpc.util.DataUtils;
import com.deng.seckilling.rpc.util.FenyeUtils;
import com.deng.seckilling.service.GoodsServcie;
import com.deng.seckilling.vo.SpuVO;
import com.github.pagehelper.PageInfo;
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
        PageInfo<SpuVO> spuDTOPageInfo = goodsServcie.listSpuVO(page, size, new Spu());
        model.addAttribute("data", spuDTOPageInfo.getList());
        model.addAttribute("url", "/goods/to_list_spu");
        FenyeUtils.setFenyeValue(model, spuDTOPageInfo);
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

    @RequestMapping("to_goods_list")
    public String to_goods_list() {
        return "common/goods_list";
    }

    @RequestMapping("to_goods_detail")
    public String to_goods_detail() {
        return "common/goods_detail";
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
    public RpcResponse saveShopInfo(String shopName) {
        if (CheckDataUtils.isEmpty(shopName)) {
            log.warn("===>saveShopInfo controller params error");
            return RpcResponse.error(ErrorCode.SECKILLING_PARAMS_ERROR);
        }

        ShopInfo isExistShopInfo = goodsServcie.isExistShopService(shopName);
        if (!CheckDataUtils.isEmpty(isExistShopInfo)) {
            return RpcResponse.error(ErrorCode.SHOPNAME_EXIST_ERROR);
        }

        Long shopId = goodsServcie.saveShopService(new ShopInfo(shopName));
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

        log.warn("===>saveSpec success, save message:specNo:{},specName:{}", specNo, specName);
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

}
