package com.stussy.stussyclon20220930changeun.dto.admin;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductMstOptionRespDto {
    private int pdtId;
    private String category;
    private String pdtName;


}
