package com.stussy.stussyclon20220930changeun.service;

import com.stussy.stussyclon20220930changeun.domain.User;
import com.stussy.stussyclon20220930changeun.exception.CustomInternalServerErrorException;
import com.stussy.stussyclon20220930changeun.repository.AccountRepository;
import com.stussy.stussyclon20220930changeun.security.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = null;

        try {
            user = accountRepository.findUserByEmail(email);

        } catch (Exception e) {
            throw new CustomInternalServerErrorException("회원 정보 조회 오류");
        }

        if(user == null) {
            throw new UsernameNotFoundException("잘못된 사용자 정보");
        }

        return new PrincipalDetails(user);
    }
}
