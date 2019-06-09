package com.deng.seckilling.dao;

import com.deng.seckilling.domain.*;
import com.deng.seckilling.sqlprovider.GoodsSqlProvider;
import com.deng.seckilling.vo.SpuVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 商品子系统相关Dao层操作
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/9 19:16
 */
@Mapper
public interface GoodsMapper {

    @Insert("insert into shop_info (shop_name) values(#{shopName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertShopInfo(ShopInfo shopInfo);

    @Update("update shop_info set shop_name = #{shopName} where id = #{id}")
    int updateShopInfo(ShopInfo shopInfo);

    @SelectProvider(type = GoodsSqlProvider.class, method = "listShopInfo")
    List<ShopInfo> listShopInfo(ShopInfo shopInfo);

    @Insert("insert into goods_category (category_name) values(#{categoryName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertGoodsCategory(Category category);

    @Update("update goods_category set category_name = #{categoryName} where id = #{id}")
    int updateGoodsCategory(Category category);

    @SelectProvider(type = GoodsSqlProvider.class,method = "listCategory")
    List<Category> listCategory(Category category);

    @Insert("insert into goods_brand (brand_name) values(#{brandName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBrand(Brand brand);

    @Update("update goods_brand set brand_name = #{brandName} where id = #{id}")
    int updateBrand(Brand brand);

    @SelectProvider(type = GoodsSqlProvider.class, method = "listBrand")
    List<Brand> listBrand(Brand brand);

    @Insert("insert into goods_spec (spec_no, spec_name) values(#{specNo}, #{specName})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSpecification(Specification specification);

    @UpdateProvider(type = GoodsSqlProvider.class, method = "updateSpecification")
    int updateSpecification(Specification specification);

    @SelectProvider(type = GoodsSqlProvider.class,method = "listSpecification")
    List<Specification> listSpecification(Specification specification);

    @SelectProvider(type = GoodsSqlProvider.class,method = "listSpuSpec")
    List<SpuSpec> listSpuSpec(SpuSpec spuSpec);

    @Insert("insert into goods_spu (spu_no, goods_name, low_price, category_id, brand_id) values(#{spuNo}, #{goodsName}, #{lowPrice}, #{categoryId}, #{brandId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSpuInfo(Spu spu);

    @UpdateProvider(type = GoodsSqlProvider.class, method = "updateSpuInfo")
    int updateSpuInfo(Spu spu);

    @SelectProvider(type = GoodsSqlProvider.class,method = "listSpuInfo")
    List<Spu> listSpuInfo(Spu spu);

    @SelectProvider(type = GoodsSqlProvider.class,method = "listSpuVO")
    List<SpuVO> listSpuVO(Spu spu);

    @Insert("insert into goods_spu_spec (spu_id, spec_id) values(#{spuId}, #{specId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSpuSpec(SpuSpec spuSpec);

    @UpdateProvider(type = GoodsSqlProvider.class, method = "updateSpuSpec")
    int updateSpuSpec(SpuSpec spuSpec);

    @Delete("delete from goods_spu_spec where id = #{id}")
    int deleteSpuSpec(Long id);

    @Insert("insert into goods_spec_value (spec_id, spec_value) values(#{specId}, #{specValue})")
    int insertSpecValue(SpecValue specValue);

    @UpdateProvider(type = GoodsSqlProvider.class, method = "updateSpecValue")
    int updateSpecValue(SpecValue specValue);

    @Insert("insert into goods_sku_spec_value (spec_id, spec_value) values(#{specId}, #{specValue})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSkuSpecValue(SkuSpecValue skuSpecValue);

    @UpdateProvider(type = GoodsSqlProvider.class, method = "updateSkuSpecValue")
    int updateSkuSpecValue(SkuSpecValue skuSpecValue);

    @SelectProvider(type = GoodsSqlProvider.class, method = "listSpecValue")
    List<SpecValue> listSpecValue(SpecValue specValue);

    @Delete("delete from goods_sku_spec_value where id = {#id}")
    int deleteSkuSpecValue(Long id);

    @Insert("insert into goods_sku (sku_no, sku_name, price, stock, spu_id, shop_id) values(#{skuNo}, #{skuName}, #{price}, #{stock}, #{spuId}, #{shopId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSku(Sku sku);

    @UpdateProvider(type = GoodsSqlProvider.class,method = "updateSku")
    int updateSku(Sku sku);

    @SelectProvider(type = GoodsSqlProvider.class,method = "listSku")
    List<Sku> listSku(Sku sku);
}
