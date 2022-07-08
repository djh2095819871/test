package com.example.demoboot.mapper;

import com.example.demoboot.entity.vote_User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vote_UserMapper {
    List<vote_User> findall();

    vote_User findBynum(int number);

    vote_User login(int number);

    int insertUser(vote_User vote_user);

    int deleteUser(int number);

    int updateUser(vote_User vote_user);
}
