package com.example.springbootbaseproject;

import java.util.UUID;

import org.hibernate.annotations.Generated;
import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@CrossOrigin(origins = "http://localhost:4200/")
@Entity
public class Trainee {
	
	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	private String name;
	@Min(1000)
	@Max(1000000)
	private int stipend;
	private String technology;
	
	public Trainee() {}
	public Trainee(int id, String name, int stipend, String technology) {
		super();
		this.id = id;
		this.name = name;
		this.stipend = stipend;
		this.technology = technology;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStipend() {
		return stipend;
	}
	public void setStipend(int stipend) {
		this.stipend = stipend;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	
}
