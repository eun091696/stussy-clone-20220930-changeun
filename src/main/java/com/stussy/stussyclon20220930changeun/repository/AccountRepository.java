package com.stussy.stussyclon20220930changeun.repository;

import com.stussy.stussyclon20220930changeun.domain.User;
import org.apache.ibatis.annotations.Mapper;


//implements는 account.xml로 된다.
@Mapper //경로는 org.apache.ibatis를 선택
public interface AccountRepository {

    public User findUserByEmail(String email) throws Exception;
    public int saveUser(User user) throws Exception;
//    리턴타입이 int인 경우는 insert, delete, update의 결과 값은 num으로 나타내기 때문이다.

}
