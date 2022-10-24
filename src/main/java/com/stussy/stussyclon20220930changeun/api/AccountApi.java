package com.stussy.stussyclon20220930changeun.api;

import com.stussy.stussyclon20220930changeun.aop.annotation.LogAspect;
import com.stussy.stussyclon20220930changeun.dto.CMRespDto;
import com.stussy.stussyclon20220930changeun.dto.RegisterReqDto;
import com.stussy.stussyclon20220930changeun.dto.validation.ValidationSequence;
import com.stussy.stussyclon20220930changeun.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService accountService;

    @LogAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) throws Exception {

        accountService.duplicateEmail(registerReqDto);
        accountService.register(registerReqDto);

        return ResponseEntity.created(URI.create("/account/login")).body(new CMRespDto<>("회원가입 성공", registerReqDto.getEmail()));
    }

}
