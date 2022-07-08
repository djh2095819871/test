package com.example.demoboot.service;

import com.example.demoboot.entity.vote_User;
import com.example.demoboot.mapper.vote_UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ObjectUtils;

import java.util.List;

@Service
public class vote_Userservice {
    @Autowired
    private vote_UserMapper vote_userMapper;
    public List<vote_User> findall(){
        return vote_userMapper.findall();
    }

    public vote_User findBynum(int number){
        return vote_userMapper.findBynum(number);
    }

    public vote_User login(int number){
        vote_User user=vote_userMapper.findBynum(number);
        return user;
    }
    public int insertUser(vote_User vote_user){
        return vote_userMapper.insertUser(vote_user);
    }

    public int register(vote_User vote_user){
        return vote_userMapper.insertUser(vote_user);
    }

    public int deleteUser(int number){
        return vote_userMapper.deleteUser(number);
    }

    public int updateUser(vote_User vote_user){
        return vote_userMapper.updateUser(vote_user);
    }
}
