package com.stussy.stussyclon20220930changeun.service.admin;

import com.stussy.stussyclon20220930changeun.exception.dto.admin.CategoryResponseDto;
import com.stussy.stussyclon20220930changeun.exception.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclon20220930changeun.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        return null;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception{

    }
}
