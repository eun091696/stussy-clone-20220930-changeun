package com.stussy.stussyclon20220930changeun.repository;

import com.stussy.stussyclon20220930changeun.domain.CollectionsProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRepository {
    public List<CollectionsProduct> getProductList(Map<String, Object> map) throws Exception;
}
