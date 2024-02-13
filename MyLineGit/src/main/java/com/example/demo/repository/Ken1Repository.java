package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Ken1;

@Repository
public interface Ken1Repository extends JpaRepository<Ken1,Integer>{
	

}
