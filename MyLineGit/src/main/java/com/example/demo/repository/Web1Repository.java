package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Web1;

@Repository
public interface Web1Repository extends JpaRepository<Web1,Integer>{
	

}
