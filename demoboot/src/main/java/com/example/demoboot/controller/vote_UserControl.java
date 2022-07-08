package com.example.demoboot.controller;


import com.example.demoboot.entity.vote_Info;
import com.example.demoboot.entity.vote_User;
import com.example.demoboot.service.vote_Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller
public class vote_UserControl {
    @Autowired
    private vote_Userservice vote_userservice;

    @RequestMapping("/login")
    public String login(HttpSession session, int number, String pwd){
        try {
            vote_User user = vote_userservice.login(number);
            if (user.getPwd().equals(pwd)){
                session.setAttribute("ms",user);
                session.setAttribute("uinfo",0);
            }
            else{
                session.setAttribute("msg","用户名或密码错误");
                return "/dengru";
            }

        }catch (Exception e){
            session.setAttribute("msg","用户名或密码错误");
            return "/dengru";
        }
        session.setAttribute("number",number);
       // return "forward:/";
        return "redirect:index";
    }
    @RequestMapping("/uupdate")
    public String updateUser(int number,String pwd,String oldpassword,HttpSession session) {
        vote_User user=vote_userservice.findBynum(number);
        if(user.getPwd().equals(oldpassword)){
            user.setPwd(pwd);
            vote_userservice.updateUser(user);
            return "dengru";
        }
        else{
            session.setAttribute("msgpassword","原密码错误");
            return "nopassword";
        }
    }



    @RequestMapping("/insertuser")
    public String register(vote_User vote_user,HttpSession session){
        try{
        if(vote_userservice.register(vote_user)==0){
            session.setAttribute("msg","账户已存在");
            return "/register";
        }
        }catch (Exception e){
            session.setAttribute("msg","账户已存在");
            return "/register";
        }
        return "redirect:dengru";
    }

        @RequestMapping("/updaterole")
        public String shouquan(int role,int number){
            vote_User vote_user=vote_userservice.findBynum(number);
            vote_user.setRole(role);
            vote_userservice.updateUser(vote_user);
            return "userpro";
    }
}



