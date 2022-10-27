package com.stussy.stussyclon20220930changeun.service.admin;

import com.stussy.stussyclon20220930changeun.domain.ProductImg;
import com.stussy.stussyclon20220930changeun.dto.admin.*;
import com.stussy.stussyclon20220930changeun.exception.CustomInternalServerErrorException;
import com.stussy.stussyclon20220930changeun.exception.CustomValidationException;
import com.stussy.stussyclon20220930changeun.repository.admin.ProductManagementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductManagementServiceImpl implements ProductManagementService {

    @Value("${file.path}")
    private String filePath;

    private final ProductManagementRepository productManagementRepository;

    @Override
    public List<CategoryResponseDto> getCategoryList() throws Exception {
        List<CategoryResponseDto> categoryResponseDtos = new ArrayList<CategoryResponseDto>();
        productManagementRepository.getCategoryList().forEach(category -> {
            categoryResponseDtos.add(category.toDto());
        });

        return categoryResponseDtos;
    }

    @Override
    public void registerMst(ProductRegisterReqDto productRegisterReqDto) throws Exception{
        if(productManagementRepository.saveProductMst(productRegisterReqDto.toEntity()) == 0) {
            throw new CustomInternalServerErrorException("상품 등록 실패");
        }
    }

    @Override
    public List<ProductMstOptionRespDto> getProductMstList() throws Exception {
        List<ProductMstOptionRespDto> list = new ArrayList<ProductMstOptionRespDto>();
        productManagementRepository.getProductMstList().forEach(pdtMst -> {
            list.add(pdtMst.toDto());
        });
        return list;
    }


    @Override
    public List<?> getSizeList(int productId) throws Exception {
        List<ProductSizeOptionRespDto> list = new ArrayList<ProductSizeOptionRespDto>();

        productManagementRepository.getSizeList(productId).forEach(size -> {
            list.add(size.toDto());
        });
        return list;
    }

    @Override
    public void checkDuplicatedColor(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.findProductColor(productRegisterDtlReqDto.toEntity()) > 0) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미 등록된 상품입니다.");
            throw new CustomValidationException("Duplicated Error", errorMap);
        }
    }

    @Override
    public void registerDtl(ProductRegisterDtlReqDto productRegisterDtlReqDto) throws Exception {
        if(productManagementRepository.saveProductDtl(productRegisterDtlReqDto.toEntity()) == 0) {
            throw new CustomInternalServerErrorException("상품 등록 오류");
        }
    }

    @Override
    public void registerImg(ProductImgReqDto productImgReqDto) throws Exception {
        log.info("pdtId >>>" + productImgReqDto.getPdtId());

        if(productImgReqDto.getFiles() == null) {
            Map<String, String> errorMap = new HashMap<String, String>();
            errorMap.put("error", "이미지를 선택하지 않았습니다.");
            throw new CustomValidationException("Img Error", errorMap);
        }

        List<ProductImg> productImgs = new ArrayList<ProductImg>();

        productImgReqDto.getFiles().forEach(file -> {
            String originName = file.getOriginalFilename();
            String extension = originName.substring(originName.lastIndexOf(".")); //확장자 파일 .png같은거 빼고 모아주는 기능
            String saveName = UUID.randomUUID().toString().replaceAll("_", "") + extension;

            Path path = Paths.get(filePath + "product/" + saveName); //(이미지 저장 경로 입력)

            File f  = new File(filePath + "product");
            if(!f.exists()) {
                f.mkdirs(); //s가 붙으면 하위 폴더까지 만들어 준다.
            }

            try {
                Files.write(path, file.getBytes());
            } catch (IOException e) {
                throw new CustomInternalServerErrorException(e.getMessage());
            }

            productImgs.add(ProductImg.builder()
                            .pdt_id(productImgReqDto.getPdtId())
                            .origin_name(originName)
                            .save_name(saveName)
                            .build());
        });

        productManagementRepository.saveProductImg(productImgs);
    }
}
