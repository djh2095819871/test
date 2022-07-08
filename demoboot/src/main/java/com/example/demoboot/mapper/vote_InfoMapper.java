package com.example.demoboot.mapper;

import com.example.demoboot.entity.vote_Info;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vote_InfoMapper {

    List<vote_Info> findall();

    vote_Info findBypid(int pid);

    List<vote_Info> findallsort();

    //增
    int savevote_Info(vote_Info vote_info);

    //删
    int deletevote_Info(int pid);

    //改
    int updatevote_Info(vote_Info vote_info);
}
