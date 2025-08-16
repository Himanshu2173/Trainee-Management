package com.example.springbootbaseproject;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TraineeRepository  extends JpaRepository<Trainee, Integer>{
	
	List<Trainee>findByTechnology(String tech);
}