package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Web3;

@Repository
public interface Web3Repository extends JpaRepository<Web3,Integer>{
	

}
