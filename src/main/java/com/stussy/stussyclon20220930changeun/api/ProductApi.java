package com.stussy.stussyclon20220930changeun.api;

import com.stussy.stussyclon20220930changeun.dto.CMRespDto;
import com.stussy.stussyclon20220930changeun.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @GetMapping("/collections/{category}")
    public ResponseEntity<?>getCollections(@PathVariable String category, int page) throws Exception {
        return ResponseEntity.ok(new CMRespDto<>("Successfully", productService.getProductList(category, page)));
    }
}
