package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Koudo2;

@Repository
public interface Koudo2Repository extends JpaRepository<Koudo2,Integer>{
	

}
