package com.example.demoboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class vote_User {
    private int number;
    private String pwd;
    private String name;
    private String age;
    private String sex;
    private String phone;
    private int role;
}
