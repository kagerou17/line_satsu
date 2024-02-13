package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ken2;

@Repository
public interface Ken2Repository extends JpaRepository<Ken2,Integer>{
	

}
