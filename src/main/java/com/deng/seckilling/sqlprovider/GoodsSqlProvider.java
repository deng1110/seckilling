package com.deng.seckilling.sqlprovider;

import com.deng.seckilling.domain.*;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/9 21:38
 */
public class GoodsSqlProvider {

    public String updateSpecification(Specification specification) {
        String sql = "update goods_spec set id = #{id} ";
        if (specification.getSpecNo() != null) {
            sql += ", spec_no = #{specNo} ";
        }
        if (specification.getSpecName() != null) {
            sql += ", spec_name = #{specName} ";
        }
        sql += " where id = #{id} ";
        return sql;
    }

    public String updateSpuInfo(Spu spu) {
        String sql = "update spu_info set id = #{id} ";
        if (spu.getGoodsName() != null) {
            sql += ", goods_name = #{goodsName} ";
        }
        if (spu.getLowPrice() != null) {
            sql += ", low_price = #{lowPrice} ";
        }
        sql += " where id = #{id}";
        return sql;
    }

    public String listShopInfo(ShopInfo shopInfo) {
        String sql = "select * from shop_info where 1=1 ";
        if (shopInfo == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (shopInfo.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (shopInfo.getShopName() != null) {
            sql += " and shop_name = #{shopName} ";
        }
        return sql;
    }

    public String updateSpuSpec(SpuSpec spuSpec) {
        String sql = "update goods_spu_spec set id = #{id} ";
        if (null != spuSpec.getSpuId()) {
            sql += " ,spu_id = #{spuId} ";
        }
        if (null != spuSpec.getSpecId()) {
            sql += " ,spec_id = #{specId} ";
        }
        sql += " where id = #{id}";
        return sql;
    }

    public String updateSpecValue(SpecValue specValue) {
        String sql = "update goods_spec_value set id = #{id} ";
        if (null != specValue.getSpecId()) {
            sql += " ,spec_id = {#specId} ";
        }
        if (null != specValue.getSpecValue()) {
            sql += " ,spec_value = {#specValue} ";
        }
        sql += " where id = {#id}";
        return sql;
    }

    public String updateSkuSpecValue(SkuSpecValue skuSpecValue) {
        String sql = "update goods_sku_spec_value set id = #{id} ";
        if (null != skuSpecValue.getSkuId()) {
            sql += " ,sku_id = {#skuId} ";
        }
        if (null != skuSpecValue.getSpecValueId()) {
            sql += " ,spec_value_id = {#specValueId} ";
        }
        sql += " where id = {#id}";
        return sql;
    }

    public String updateSku(Sku sku) {
        String sql = "update sku set id = {#id} ";
        if (null != sku.getSkuNo()) {
            sql += " ,sku_no = {#skuNo} ";
        }
        if (null != sku.getSkuName()) {
            sql += " ,sku_name = {#skuName} ";
        }
        if (null != sku.getPrice()) {
            sql += " ,price = {#price} ";
        }
        if (null != sku.getStock()) {
            sql += " ,stock = {#stock} ";
        }
        if (null != sku.getSpuId()) {
            sql += " ,spu_id = {#spuId} ";
        }
        if (null != sku.getShopId()) {
            sql += " ,shop_id = {#shopId} ";
        }
        sql += " where id = {#id}";
        return sql;
    }

    public String listSpuVO(Spu spu) {
        String sql = "SELECT s.id,s.spu_no,s.goods_name,s.low_price,b.brand_name,c.category_name,s.create_time,s.update_time " +
                " from goods_spu s LEFT JOIN goods_brand b " +
                " on s.brand_id = b.id " +
                " LEFT JOIN goods_category c " +
                " on s.category_id = c.id " +
                "where  1=1 ";
        if (spu == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (spu.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (spu.getSpuNo() != null) {
            sql += " and spu_no = #{spuNo} ";
        }
        if (spu.getGoodsName() != null) {
            sql += " and goods_name = #{goodsName} ";
        }
        if (spu.getLowPrice() != null) {
            sql += " and low_price = #{lowPrice} ";
        }
        if (spu.getBrandId() != null) {
            sql += " and brand_id = #{brandId} ";
        }
        if (spu.getCategoryId() != null) {
            sql += " and category_id = #{categoryId} ";
        }
        return sql;
    }

    public String listSpuInfo(Spu spu) {
        String sql = "SELECT * from goods_spu where 1=1 ";
        if (spu == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (spu.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (spu.getSpuNo() != null) {
            sql += " and spu_no = #{spuNo} ";
        }
        if (spu.getGoodsName() != null) {
            sql += " and goods_name = #{goodsName} ";
        }
        if (spu.getLowPrice() != null) {
            sql += " and low_price = #{lowPrice} ";
        }
        if (spu.getBrandId() != null) {
            sql += " and brand_id = #{brandId} ";
        }
        if (spu.getCategoryId() != null) {
            sql += " and category_id = #{categoryId} ";
        }
        return sql;
    }

    public String listSpecification(Specification spec) {
        String sql = "select * from goods_spec where 1=1 ";
        if (null == spec) {
            sql += " and 1=0 ";
            return sql;
        }
        if (spec.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (spec.getSpecName() != null) {
            sql += " and spec_name = #{specName} ";
        }
        if (spec.getSpecNo() != null) {
            sql += " and spec_no = #{specNo} ";
        }
        return sql;
    }

    public String listSpecValue(SpecValue specValue) {
        String sql = "selct * from goods_spec_value where 1=1 ";
        if (specValue == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (specValue.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (specValue.getSpecId() != null) {
            sql += " and spec_id = #{specId} ";
        }
        if (specValue.getSpecValue() != null) {
            sql += " and spec_value = #{specValue} ";
        }
        return sql;
    }

    public String listSku(Sku sku) {
        String sql = "select * from goods_sku where 1=1 ";
        if (sku == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (sku.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (sku.getSkuNo() != null) {
            sql += " and sku_no = #{skuNo} ";
        }
        if (sku.getSkuName() != null) {
            sql += " and sku_name = #{skuName} ";
        }
        if (sku.getShopId() != null) {
            sql += " and shop_id = #{shopId}";
        }
        if (sku.getSpuId() != null) {
            sql += " and spu_id = #{spuId}";
        }
        if (sku.getPrice() != null) {
            sql += " and price = #{price} ";
        }
        if (sku.getStock() != null) {
            sql += " and stock = #{stock} ";
        }
        if (sku.getIsSale() != null) {
            sql += " and issale = #{issale} ";
        }
        return sql;
    }

    public String listCategory(Category category) {
        String sql = "select * from goods_category where 1=1 ";
        if (category == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (category.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (category.getCategoryName() != null) {
            sql += " and category_name = #{categoryName} ";
        }
        return sql;
    }

    public String listBrand(Brand brand) {
        String sql = "select * from goods_brand where 1=1 ";
        if (brand == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (brand.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (brand.getBrandName() != null) {
            sql += " and brand_name = #{brandName} ";
        }
        return sql;
    }

    public String listSpuSpec(SpuSpec spuSpec) {
        String sql = "select * from goods_spu_spec where 1=1 ";
        if (spuSpec == null) {
            sql += " and 1=0 ";
            return sql;
        }
        if (spuSpec.getId() != null) {
            sql += " and id = #{id} ";
        }
        if (spuSpec.getSpecId() != null) {
            sql += " and spec_id = #{specId} ";
        }
        if (spuSpec.getSpuId() != null) {
            sql += " and spu_id = #{spuId} ";
        }
        return sql;
    }

}
