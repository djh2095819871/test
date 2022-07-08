package com.example.demoboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class vote_Record {
    private int vid;
    private vote_User vote_user;
    private vote_Info vote_info;
}
