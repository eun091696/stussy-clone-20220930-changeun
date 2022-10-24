package com.stussy.stussyclon20220930changeun.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CollectionListRespDto {
    private int productId;
    private String productName;
    private int productPrice;
    private String mainImg;
    private int productTotalCount;
}
