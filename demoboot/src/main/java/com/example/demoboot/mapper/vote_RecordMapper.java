package com.example.demoboot.mapper;

import com.example.demoboot.entity.vote_Record;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vote_RecordMapper {

    List<vote_Record> findall();

    List<vote_Record> findbypid(int pid);

    List<vote_Record> findbynum(int number);

    int savevote_Record(vote_Record vote_record);
}
