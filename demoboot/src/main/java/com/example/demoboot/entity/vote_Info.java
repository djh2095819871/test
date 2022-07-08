package com.example.demoboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class vote_Info {
    private String pname;
    private int pid;
    private Category category;
    //private String cid;
    private String remarks;
    private String description;
    private int count;
    private String detail;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pdate;
    private String img;
}
