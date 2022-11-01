package com.stussy.stussyclon20220930changeun.controller;

import com.stussy.stussyclon20220930changeun.dto.CheckoutRespDto;
import com.stussy.stussyclon20220930changeun.security.PrincipalDetails;
import com.stussy.stussyclon20220930changeun.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/collections/{category}")
    public String loadCollections(@PathVariable String category) {
        return "product/collections_scroll";
    }

    @GetMapping("/product/{pdtId}")
    public String loadProductDetail(@PathVariable String pdtId){
        return "product/product_detail";
    }

    @GetMapping("/checkout")
    public String loadPayment(Model model, //서버사이드 렌더링을 사용하기 때문에 Model객체가 필요하다. (Model자체가 dto 역할을 하기 때문에 dto 작성 필요 없음)
                              @RequestParam int pdtDtlId,
                              @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception{
        CheckoutRespDto checkoutRespDto = productService.getCheckoutProduct(pdtDtlId);
        model.addAttribute("data", checkoutRespDto);
        model.addAttribute("user", principalDetails.getUser());
        return "product/product_order";

    }
}
