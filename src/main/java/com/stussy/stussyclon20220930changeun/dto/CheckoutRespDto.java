package com.stussy.stussyclon20220930changeun.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class CheckoutRespDto {
    private int pdtDtlId;
    private int pdtId;
    private String pdtName;
    private int pdtPrice;
    private String pdtColor;
    private int sizeId;
    private String sizeName;
    private String saveName;
}
