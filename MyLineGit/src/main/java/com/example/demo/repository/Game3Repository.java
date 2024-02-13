package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Game3;

@Repository
public interface Game3Repository extends JpaRepository<Game3,Integer>{
	

}
