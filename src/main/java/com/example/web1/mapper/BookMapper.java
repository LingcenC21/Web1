package com.example.web1.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web1.model.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
