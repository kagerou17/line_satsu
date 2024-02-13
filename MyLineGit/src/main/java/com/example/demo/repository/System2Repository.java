package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.System2;

@Repository
public interface System2Repository extends JpaRepository<System2,Integer>{
	

}
