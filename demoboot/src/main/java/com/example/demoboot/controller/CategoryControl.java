package com.example.demoboot.controller;

import com.example.demoboot.entity.Category;
import com.example.demoboot.service.Categoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoryControl {
    @Autowired
    private Categoryservice categoryservice;


    @RequestMapping("/insertcategory")
    public String insertcategory(String cname) {
        Category category=new Category();
        category.setCname(cname);
        categoryservice.saveCategory(category);
        return "xitong";
    }

}
