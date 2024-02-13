package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Web2;

@Repository
public interface Web2Repository extends JpaRepository<Web2,Integer>{
	

}
