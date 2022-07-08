package com.example.demoboot.service;

import com.example.demoboot.entity.vote_Info;
import com.example.demoboot.entity.vote_Record;

import com.example.demoboot.mapper.vote_RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class vote_Recordservice {
    @Autowired
    private vote_RecordMapper vote_recordMapper;

    public List<vote_Record> findall(){return vote_recordMapper.findall();}

    public List<vote_Record> findbypid(int pid){return vote_recordMapper.findbypid(pid);}

    public List<vote_Record> findbynum(int number){return vote_recordMapper.findbynum(number);}

    public int savevote_Record(vote_Record vote_record){return vote_recordMapper.savevote_Record(vote_record);}
}
