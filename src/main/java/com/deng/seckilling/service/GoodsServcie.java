package com.deng.seckilling.service;

import com.deng.seckilling.dao.GoodsMapper;
import com.deng.seckilling.domain.*;
import com.deng.seckilling.rpc.redis.RedisClient;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import com.deng.seckilling.vo.SpuVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品子系统Service
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/9 22:28
 */
@Service
public class GoodsServcie {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private RedisClient redisClient;

    /**
     * 注册店铺信息
     *
     * @param shopInfo 店铺信息
     * @return 店铺ID
     */
    public Long saveShopService(ShopInfo shopInfo) {
        goodsMapper.insertShopInfo(shopInfo);
        return shopInfo.getId();
    }

    /**
     * 更新店铺信息
     *
     * @param shopInfo 店铺信息
     * @return 店铺ID
     */
    public Long modifyShopService(ShopInfo shopInfo) {
        goodsMapper.updateShopInfo(shopInfo);
        return shopInfo.getId();
    }

    /**
     * 分页展示店铺
     */
    public PageInfo<ShopInfo> listShopInfo(Integer page, Integer size, ShopInfo shopInfo) {
        PageHelper.startPage(page, size);
        return new PageInfo<ShopInfo>(goodsMapper.listShopInfo(shopInfo));
    }

    /**
     * 验证店铺名是否已存在
     *
     * @param shopName 店铺名
     * @return 如果存在返回店铺信息
     */
    public ShopInfo isExistShopService(String shopName) {
        List<ShopInfo> shopInfoList = goodsMapper.listShopInfo(new ShopInfo(shopName));
        return CheckDataUtils.isEmpty(shopInfoList) ? null : shopInfoList.get(0);
    }

    /**
     * 验证店铺ID是否存在
     *
     * @param shopId 店铺ID
     * @return 如果存在返回店铺信息
     */
    public ShopInfo isExistShopService(Long shopId) {
        List<ShopInfo> shopInfoList = goodsMapper.listShopInfo(new ShopInfo(shopId));
        return CheckDataUtils.isEmpty(shopInfoList) ? null : shopInfoList.get(0);
    }

    /**
     * 增加分类信息
     *
     * @param category 分类信息
     * @return 分类信息ID
     */
    public Long saveCategoryInfoService(Category category) {
        category.setId(null);
        goodsMapper.insertGoodsCategory(category);
        return category.getId();
    }

    /**
     * 分页展示分类信息
     */
    public PageInfo<Category> listCategoryService(Integer page, Integer size, Category category) {
        PageHelper.startPage(page, size);
        return new PageInfo<Category>(goodsMapper.listCategory(category));
    }

    /**
     * 分页展示品牌信息
     */
    public PageInfo<Brand> listBrandService(Integer page, Integer size, Brand brand) {
        PageHelper.startPage(page, size);
        return new PageInfo<Brand>(goodsMapper.listBrand(brand));
    }

    /**
     * 验证分类名称是否存在
     *
     * @param categoryName 分类名称
     * @return 如果存在返回分类信息
     */
    public Category isExistCategoryServcie(String categoryName) {
        List<Category> categoryList = goodsMapper.listCategory(new Category(categoryName));
        return CheckDataUtils.isEmpty(categoryList) ? null : categoryList.get(0);
    }

    /**
     * 注册品牌
     *
     * @param brand 品牌信息
     * @return 品牌信息ID
     */
    public Long saveBrandServcie(Brand brand) {
        goodsMapper.insertBrand(brand);
        return brand.getId();
    }

    /**
     * 验证品牌名称是否存在
     *
     * @param brandName 品牌名称
     * @return 如果存在返回品牌信息
     */
    public Brand isExistBrandService(String brandName) {
        List<Brand> brandList = goodsMapper.listBrand(new Brand(brandName));
        return CheckDataUtils.isEmpty(brandList) ? null : brandList.get(0);
    }

    /**
     * 增加规格信息
     *
     * @param specification 规格信息
     * @return 规格信息ID
     */
    public Long saveSpecService(Specification specification) {
        goodsMapper.insertSpecification(specification);
        return specification.getId();
    }

    /**
     * 验证规格名是否存在
     *
     * @param specName 规格名
     * @return 如果存在返回规格信息
     */
    public Specification isExistSpecService(String specName) {
        List<Specification> specificationList = goodsMapper.listSpecification(new Specification(specName));
        return CheckDataUtils.isEmpty(specificationList) ? null : specificationList.get(0);
    }

    /**
     * 验证规格ID是否存在
     *
     * @param specId 规格ID
     * @return 如果存在返回规格信息
     */
    public Specification isExistSpecService(Long specId) {
        List<Specification> specificationList = goodsMapper.listSpecification(new Specification(specId));
        return CheckDataUtils.isEmpty(specificationList) ? null : specificationList.get(0);
    }

    /**
     * 验证规格编号是否存在
     *
     * @param specNo 规格编号
     * @return 如果存在返回规格信息
     */
    public Specification isExistSpecService2(String specNo) {
        Specification specification = new Specification();
        specification.setSpecNo(specNo);
        List<Specification> specificationList = goodsMapper.listSpecification(specification);
        return CheckDataUtils.isEmpty(specificationList) ? null : specificationList.get(0);
    }

    /**
     * 分页展示规格信息
     */
    public PageInfo<Specification> listSpecService(Integer page, Integer size, Specification specification) {
        PageHelper.startPage(page, size);
        return new PageInfo<Specification>(goodsMapper.listSpecification(specification));
    }

    /**
     * 分页展示SPU和规格关联信息
     */
    public PageInfo<SpuSpec> listSpuSpecService(Integer page, Integer size, SpuSpec spuSpec) {
        PageHelper.startPage(page, size);
        return new PageInfo<SpuSpec>(goodsMapper.listSpuSpec(spuSpec));
    }

    /**
     * 增加Spu信息
     *
     * @param spu Spu信息
     * @return Spu信息ID
     */
    public Long saveSpuServcie(Spu spu) {
        goodsMapper.insertSpuInfo(spu);
        return spu.getId();
    }

    /**
     * 更新Spu信息（仅可更改最低价格）
     *
     * @param spu Spu信息
     * @return Spu信息ID
     */
    public Long updateSpuService(Spu spu) {
        goodsMapper.updateSpuInfo(new Spu(spu.getId(), spu.getLowPrice()));
        return spu.getId();
    }

    /**
     * 分页展示SPU
     */
    public PageInfo<SpuVO> listSpuVO(Integer page, Integer size, Spu spu) {
        PageHelper.startPage(page, size);
        return new PageInfo<SpuVO>(goodsMapper.listSpuVO(spu));
    }

    /**
     * 验证SpuNo是否存在
     */
    public Spu isExistSpuService(String spuNo) {
        List<Spu> spuList = goodsMapper.listSpuInfo(new Spu(spuNo));
        return CheckDataUtils.isEmpty(spuList) ? null : spuList.get(0);
    }

    /**
     * 验证SpuNo是否存在
     */
    public Spu isExistSpuService(Long spuId) {
        List<Spu> spuList = goodsMapper.listSpuInfo(new Spu(spuId));
        return CheckDataUtils.isEmpty(spuList) ? null : spuList.get(0);
    }

    /**
     * 保存规格值
     *
     * @param specValue 规格值信息
     * @return 规格值ID
     */
    public Long saveSpecValue(SpecValue specValue) {
        goodsMapper.insertSpecValue(specValue);
        return specValue.getId();
    }

    /**
     * 修改规格值
     *
     * @param specValue 规格值信息
     * @return 规格值ID
     */
    public Long updateSpecValue(SpecValue specValue) {
        specValue.setSpecId(null);
        goodsMapper.updateSpecValue(specValue);
        return specValue.getId();
    }

    /**
     * 验证规格值ID是否存在
     *
     * @param id 规格值ID
     * @return 如果存在返回规格值信息
     */
    public SpecValue isExistSpecValue(Long id) {
        List<SpecValue> specValueList = goodsMapper.listSpecValue(new SpecValue(id));
        return CheckDataUtils.isEmpty(specValueList) ? null : specValueList.get(0);
    }

    /**
     * 增加Sku
     *
     * @param sku Sku信息
     * @return Sku主键ID
     */
    public Long saveSku(Sku sku) {
        goodsMapper.insertSku(sku);
        return sku.getId();
    }

    /**
     * 修改Sku的信息（仅可修改单价，库存，是否在架）
     *
     * @param sku Sku信息
     * @return
     */
    public Long updateSku(Sku sku) {
        sku.setSkuNo(null);
        sku.setSkuName(null);
        sku.setSpuId(null);
        sku.setShopId(null);
        goodsMapper.updateSku(sku);
        return sku.getId();
    }

    /**
     * 验证SkuId是否存在
     *
     * @param id SkuId
     * @return 如果存在返回Sku信息
     */
    public Sku isExistSku(Long id) {
        List<Sku> skuList = goodsMapper.listSku(new Sku(id));
        return CheckDataUtils.isEmpty(skuList) ? null : skuList.get(0);
    }

    /**
     * 增加Spu和规格的关联关系
     *
     * @param spuSpec Spu和规格的关联关系信息
     * @return Spu和规格的关联关系ID
     */
    public Long saveSpuSpec(SpuSpec spuSpec) {
        goodsMapper.insertSpuSpec(spuSpec);
        return spuSpec.getId();
    }

    /**
     * 增加Sku和规格值关联关系
     *
     * @param skuSpecValue Sku和规格值的关联关系信息
     * @return Sku和规格值的关联关系信息ID
     */
    public Long saveSkuSpecValue(SkuSpecValue skuSpecValue) {
        goodsMapper.insertSkuSpecValue(skuSpecValue);
        return skuSpecValue.getId();
    }

}
