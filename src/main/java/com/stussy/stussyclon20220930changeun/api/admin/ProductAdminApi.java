package com.stussy.stussyclon20220930changeun.api.admin;

import com.stussy.stussyclon20220930changeun.aop.annotation.LogAspect;
import com.stussy.stussyclon20220930changeun.aop.annotation.ValidAspect;
import com.stussy.stussyclon20220930changeun.exception.dto.CMRespDto;
import com.stussy.stussyclon20220930changeun.exception.dto.admin.ProductRegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class ProductAdminApi {

    @LogAspect
    @ValidAspect
    @PostMapping("/product")
    public ResponseEntity<?>registerProductMst(@Valid @RequestBody ProductRegisterReqDto productRegisterReqDto,
                                               BindingResult bindingResult) {

        return ResponseEntity.created(null)
                .body(new CMRespDto<>("Register Successfully", "null"));
    }

    @GetMapping("/product/category")
    public ResponseEntity<?>getCategoryList(){
        return ResponseEntity.ok().body(new CMRespDto<>("Get Successfully",null));
    }
}
