package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Game4;

@Repository
public interface Game4Repository extends JpaRepository<Game4,Integer>{
	

}
