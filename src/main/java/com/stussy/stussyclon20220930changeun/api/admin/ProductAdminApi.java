package com.stussy.stussyclon20220930changeun.api.admin;

import com.stussy.stussyclon20220930changeun.aop.annotation.LogAspect;
import com.stussy.stussyclon20220930changeun.aop.annotation.ValidAspect;
import com.stussy.stussyclon20220930changeun.exception.dto.CMRespDto;
import com.stussy.stussyclon20220930changeun.exception.dto.admin.ProductRegisterReqDto;
import com.stussy.stussyclon20220930changeun.service.admin.ProductManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Random;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductAdminApi {
    private final ProductManagementService productManagementService;

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?>registerProductMst(@Valid @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                               BindingResult bindingResult) throws Exception {
        String name = productRegisterReqDto.getName();

        Random random = new Random();

        for(int i = 0; i < 100; i++) {

            productRegisterReqDto.setCategory(i / 10 + 1);
            productRegisterReqDto.setName(name + (i + 1));
            productRegisterReqDto.setPrice(random.nextInt((10) + 1) * 100000);
            productManagementService.registerMst(productRegisterReqDto);
        }



        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", true));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?>getCategoryList() throws  Exception{
        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully",productManagementService.getCategoryList()));
    }
}