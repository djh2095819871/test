package com.example.demoboot.service;

import com.example.demoboot.entity.Category;
import com.example.demoboot.entity.vote_Info;
import com.example.demoboot.mapper.vote_InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class vote_Infoservice {
    @Autowired
    private vote_InfoMapper vote_infoMapper;

    public List<vote_Info> findall(){
        return vote_infoMapper.findall();
    }

    public List<vote_Info> findallsort(){return vote_infoMapper.findallsort();}

    public vote_Info findBypid(int pid){
        return vote_infoMapper.findBypid(pid);
    }

    public int savevote_Info(vote_Info vote_info){
        return vote_infoMapper.savevote_Info(vote_info);
    }

    public int deletevote_Info(int pid){
        return vote_infoMapper.deletevote_Info(pid);
    }

    public int updatevote_Info(vote_Info vote_info){
        return vote_infoMapper.updatevote_Info(vote_info);
    }
}
