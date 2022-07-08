package com.example.demoboot.service;

import com.example.demoboot.entity.Category;
import com.example.demoboot.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Categoryservice {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> findall(){
        return categoryMapper.findall();
    }

    public Category findBycid(int cid){
        return categoryMapper.findBycid(cid);
    }

    public int saveCategory(Category category){
        return categoryMapper.saveCategory(category);
    }

    public int deleteCategory(int cid){
        return categoryMapper.deleteCategory(cid);
    }

    public int updateCategory(Category category){
        return categoryMapper.updateCategory(category);
    }
}
