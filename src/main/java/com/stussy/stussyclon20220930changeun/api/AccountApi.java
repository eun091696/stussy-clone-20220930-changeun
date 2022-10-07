package com.stussy.stussyclon20220930changeun.api;

import com.stussy.stussyclon20220930changeun.aop.annotation.LogAspect;
import com.stussy.stussyclon20220930changeun.dto.CMRespDto;
import com.stussy.stussyclon20220930changeun.dto.RegisterReqDto;
import com.stussy.stussyclon20220930changeun.dto.validation.ValidationSequence;
import com.stussy.stussyclon20220930changeun.exception.CustomValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Sequencer;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/account")
@RestController
public class AccountApi {

    @LogAspect
    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated(ValidationSequence.class) @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) {
        return ResponseEntity.created(null).body(new CMRespDto<>("회원가입 성공", registerReqDto));
    }

}
