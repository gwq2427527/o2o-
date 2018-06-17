package com.imooc.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;

@Service //需要用spring来托管这个类
public class ProductCategoryServiceImpl implements ProductCategoryService {
	//spring在运行时 动态的注入到ProductCategoryDao mybatis的实现类
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {

		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new ProductCategoryOperationException("店铺类别创建失败");
				} else {
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}

			} catch (Exception e) {
				throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
			}
		} else {
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException {
		// TODO将此类别下的商品里的类别ID置为空
		//解除tb_product里的商品与该product category id的关联
		//因为商品和商品类别建立了联系，在删除商品类别之前，需要将商品里面的和商品类别有关的删除
		try {
			int efffectNum = productDao.updateProductCategoryToNull(productCategoryId);
			if(efffectNum<0) {
				throw new RuntimeException("商品类别更新失败");
			}
		}catch(Exception e) {
			throw new RuntimeException("delete product category error " +e.getMessage());
		}
		//删除该product category
		try {
			int efffectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(efffectNum<=0) {
				throw new ProductCategoryOperationException("删除失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e) {
			throw new ProductCategoryOperationException("delete product category error"+e.getMessage());
		}
		
	}
}
