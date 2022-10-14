package com.stussy.stussyclon20220930changeun.service;

import com.stussy.stussyclon20220930changeun.exception.dto.RegisterReqDto;

public interface AccountService {

    public void duplicateEmail(RegisterReqDto registerReqDto) throws Exception;
    public void register(RegisterReqDto registerReqDto) throws Exception;
}
