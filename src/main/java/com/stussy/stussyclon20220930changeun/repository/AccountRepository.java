package com.stussy.stussyclon20220930changeun.repository;

import com.stussy.stussyclon20220930changeun.domain.User;
import org.apache.ibatis.annotations.Mapper;


//implements는 account.xml로 된다.
@Mapper //경로는 org.apache.ibatis를 선택
public interface AccountRepository {

    public User findUserByEmail(String email) throws Exception;
    public int saveUser(User user) throws Exception;

}
