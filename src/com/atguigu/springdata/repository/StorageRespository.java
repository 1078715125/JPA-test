package com.atguigu.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.atguigu.springdata.entity.Storage;

public interface StorageRespository extends JpaSpecificationExecutor<Storage>,
		JpaRepository<Storage, Long> {

}
