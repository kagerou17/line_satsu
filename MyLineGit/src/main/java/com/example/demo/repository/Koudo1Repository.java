package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Koudo1;

@Repository
public interface Koudo1Repository extends JpaRepository<Koudo1,Integer>{
	

}
