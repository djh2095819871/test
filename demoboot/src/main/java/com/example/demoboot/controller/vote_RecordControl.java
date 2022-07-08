package com.example.demoboot.controller;

import com.example.demoboot.entity.Category;
import com.example.demoboot.entity.vote_Record;
import com.example.demoboot.service.vote_Recordservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class vote_RecordControl {
    @Autowired
    private vote_Recordservice vote_recordservice;

    @ResponseBody
    @RequestMapping("/rfindall")
    public List<vote_Record> findall() {
        return vote_recordservice.findall();
    }

    @ResponseBody
    @RequestMapping("/findbypid")
    public List<vote_Record> findbypid(int pid){
        return vote_recordservice.findbypid(pid);
    }

    @ResponseBody
    @RequestMapping("/findbynum")
    public List<vote_Record> findbynum(int number){
        return vote_recordservice.findbynum(number);
    }

    @ResponseBody
    @RequestMapping("/rinsert")
    public int savevoterecord(vote_Record vote_record) {
        return vote_recordservice.savevote_Record(vote_record);
    }

}
