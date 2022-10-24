package com.stussy.stussyclon20220930changeun.service.admin;

import com.stussy.stussyclon20220930changeun.dto.admin.CategoryResponseDto;
import com.stussy.stussyclon20220930changeun.dto.admin.ProductRegisterReqDto;

import java.util.List;

public interface ProductManagementService {

    public List<CategoryResponseDto> getCategoryList()throws Exception;

    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception;
}
