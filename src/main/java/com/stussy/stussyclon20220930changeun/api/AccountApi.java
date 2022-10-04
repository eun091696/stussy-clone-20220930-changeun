package com.stussy.stussyclon20220930changeun.api;

import com.stussy.stussyclon20220930changeun.dto.RegisterReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto) {
        System.out.println("회원가입 요청 데이터:" + registerReqDto);
        return null;
    }

}
