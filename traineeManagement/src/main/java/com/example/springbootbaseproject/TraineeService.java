package com.example.springbootbaseproject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.springbootbaseproject.exceptions.TechnologyNotFoundException;
import com.example.springbootbaseproject.exceptions.TraineeNotFoundException;

@Service
public class TraineeService {
	@Autowired
	TraineeRepository traineeRepository;
	
	List<Trainee> getAllTrainee(){
		List<Trainee>traineelist= traineeRepository.findAll();
		
		if(traineelist.isEmpty()) {
			throw new TraineeNotFoundException("Trainee list is empty");
		}else {
			return traineelist;
		}
	}
	public Trainee getTraineeById(int id) {
		Optional<Trainee>trainee= traineeRepository.findById(id);
		
		 if(!trainee.isPresent()) {
			 throw new TraineeNotFoundException("Trainee id is not found");
		 }else {
			 return trainee.get();
		 }	 
	}
	
	public Trainee insertTrainee(Trainee t) throws DuplicateKeyException {
		if(traineeRepository.existsById(t.getId())) {
			throw new DuplicateKeyException("id is duplicate");
		}else {
			traineeRepository.save(t);
			return t;
		}
	}
	
	
	public Trainee updateTrainee(int id,Trainee T) {
	Trainee t=getTraineeById(id);
	 if(t==null) {
		 throw new TraineeNotFoundException("Employee id is not found");
	 }else {
		 Trainee updatedTrainee=traineeRepository.save(T);
		 return updatedTrainee;
	 }
	}
	
	
	public Trainee deleteTrainee(int id) {
		Optional<Trainee>t= traineeRepository.findById(id);
				
		 if(!t.isPresent()) {
			 throw new TraineeNotFoundException("Employee id is not found");
		 }else {
			 traineeRepository.deleteById(id);
			 return t.get();
		 }	
	}
	
	
	List<Trainee> getTraineeGroupedByTechnology(String tech){
		List<Trainee>traineelist= traineeRepository.findByTechnology(tech);
		
		if(traineelist.isEmpty()) {
			throw new TechnologyNotFoundException("No one from given tech");
		}else {
			return traineelist;
		}
	}
}
