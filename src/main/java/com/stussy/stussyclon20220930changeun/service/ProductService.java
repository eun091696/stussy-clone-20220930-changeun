package com.stussy.stussyclon20220930changeun.service;

import com.stussy.stussyclon20220930changeun.dto.CollectionListRespDto;

import java.util.List;

public interface ProductService {
    public List<CollectionListRespDto> getProductList(String category, int page) throws Exception;
}
