package com.atguigu.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atguigu.springdata.entity.News;

public interface NewsRepository extends JpaRepository<News, Integer>{

	News getById(Integer id);
}
