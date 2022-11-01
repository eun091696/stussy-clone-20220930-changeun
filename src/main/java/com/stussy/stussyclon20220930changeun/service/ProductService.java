package com.stussy.stussyclon20220930changeun.service;

import com.stussy.stussyclon20220930changeun.dto.CheckoutRespDto;
import com.stussy.stussyclon20220930changeun.dto.CollectionListRespDto;
import com.stussy.stussyclon20220930changeun.dto.ProductRespDto;

import java.util.List;

public interface ProductService {
    public List<CollectionListRespDto> getProductList(String category, int page) throws Exception;

    public ProductRespDto getProduct(int pdtId) throws Exception;

    public CheckoutRespDto getCheckoutProduct(int pdtDtlId) throws Exception;
}
