package com.stussy.stussyclon20220930changeun.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CMRespDto<T> {
    private  String msg;
    private  T data;
}
