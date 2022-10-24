package com.stussy.stussyclon20220930changeun.dto;

import com.stussy.stussyclon20220930changeun.domain.User;
import com.stussy.stussyclon20220930changeun.dto.validation.ValidationGroups;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterReqDto {
    //회원가입 정보는 해당 Dto에서만 검사하면 되어 여기서 유연성 검사 진행
    //이름은 한글자 이상 들어와야함, 세글자 넘을 수 없음. 무조건 한글
    @NotBlank(message = "이름은 비워둘 수 없습니다", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 3, message = "이름은 3글자 까지만 입력가능합니다", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능합니다", groups = ValidationGroups.PatternCheckGroup.class)
    private String lastName;

    @NotBlank(message = "성은 비워둘 수 없습니다", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 1, max = 2, message = "성은 2글자 까지만 입력이 가능합니다", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능합니다", groups = ValidationGroups.PatternCheckGroup.class)
    private String firstName;

    @NotBlank(message = "이메일은 비워둘 수 없습니다", groups = ValidationGroups.NotBlankGroup.class)
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 비워둘 수 없습니다", groups = ValidationGroups.NotBlankGroup.class)
    @Size(min = 8, max = 16, message = "비밀번호는 8자 부터 16자까지 입력하여야 합니다", groups = ValidationGroups.SizeGroup.class)
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-~!@#$%^&*_]*$", message = "비밀번호는 특수기호, 영문, 숫자를 모두 포함해야합니다.", groups = ValidationGroups.PatternCheckGroup.class)
    private String password;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(new BCryptPasswordEncoder().encode(password)) //new BCryptPasswordEncoder().encode()는 DB에 암호화로 나타난다.
                .name(firstName + lastName)
                .role_id(1)
                .build();
    }
}
