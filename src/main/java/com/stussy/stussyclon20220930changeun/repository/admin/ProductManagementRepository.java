package com.stussy.stussyclon20220930changeun.repository.admin;

import com.stussy.stussyclon20220930changeun.domain.Product;
import com.stussy.stussyclon20220930changeun.domain.ProductCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductManagementRepository {
    public List<ProductCategory> getCategoryList() throws Exception;
    public int saveProductMst(Product product) throws Exception;
}
