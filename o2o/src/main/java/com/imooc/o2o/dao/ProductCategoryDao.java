package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ProductCategory;

public interface ProductCategoryDao {

	/**
	 * 通过shopId查询店铺商品分类
	 */
	List<ProductCategory> queryProductCategoryList(Long shopId);
	
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId")long shopId);
}
