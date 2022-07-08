package com.example.demoboot.mapper;

import com.example.demoboot.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryMapper {
    //查
    List<Category> findall();

    Category findBycid(int cid);

    //增
    int saveCategory(Category category);

    //删
    int deleteCategory(int cid);

    //改
    int updateCategory(Category category);
}
