package com.example.springbootbaseproject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200/")

@RestController
public class TraineeRestController {
	
	@Autowired
	TraineeService traineeService;
	
	@GetMapping(value="/trainees",produces = "application/json")
	public List<Trainee> fetchAllTrainees(){
		
		return traineeService.getAllTrainee();
	}
	
	@GetMapping("/trainee/{id}")
	public Trainee fetchTrainee(@PathVariable("id") int identity) {
		
		return traineeService.getTraineeById(identity);
	}
	@PutMapping("/trainee/{id}")
	public Trainee updateTrainee(@PathVariable("id") int identity, @Valid @RequestBody  Trainee t) {
		
		return traineeService.updateTrainee(identity, t);
	}
	@PostMapping("/trainee")
    public Trainee createTrainee(@Valid  @RequestBody Trainee t) {
		
		return traineeService.insertTrainee(t);
	}

	@DeleteMapping("/trainee/{id}")
	public Trainee deleteTraineeById(@PathVariable("id") int identity) {
		
		return traineeService.deleteTrainee(identity);
	}

	@GetMapping("/trainee/tech/{technology}")
    public List<Trainee> getTraineesGroupedByTechnology(@PathVariable("technology") String tech) {
		List<Trainee>  results = traineeService.getTraineeGroupedByTechnology(tech);
       
        return results;
    }

}
