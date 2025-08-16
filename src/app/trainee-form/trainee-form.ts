import { Component, OnInit } from '@angular/core';
import { Trainee, TraineeService } from '../traineeService';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-trainee-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './trainee-form.html',
  styleUrls: ['./trainee-form.css']
})
export class TraineeForm implements OnInit {
  trainee: Trainee = { id: 0, name: '', stipend: 0, technology: '' };
  trainees: Trainee[] = [];
  technologies: string[] = [];
  ids: number[] = [];
  selectedId: number | null = null;
  selectedTech: string = "";
  mode: 'add' | 'update' | 'delete' = 'add';

  constructor(private traineeService: TraineeService) { }

  ngOnInit(): void {
    this.fetchTrainees();
  }

  fetchTrainees() {
    this.traineeService.getAllTrainees().subscribe({
      next: (data) => {
        this.trainees = data;
        this.technologies = [...new Set(data.map(t => t.technology))];
        this.ids = data.map(t => t.id!);
      },
      error: (err) => console.error(err)
    });
  }

  onSubmit(form: NgForm) {
  if (this.mode === 'add' && form.valid) {
    this.traineeService.addTrainee(this.trainee).subscribe({
      next: () => { alert('Trainee added successfully!'); this.resetForm(form); this.fetchTrainees(); },
      error: (err) => console.error(err)
    });
  } 
  else if (this.mode === 'update' && this.selectedId && form.valid) {
    this.traineeService.updateTrainee(this.selectedId, this.trainee).subscribe({
      next: () => { alert('Trainee updated successfully!'); this.resetForm(form); this.fetchTrainees(); },
      error: (err) => console.error(err)
    });
  } 
  else if (this.mode === 'delete' && this.selectedId) {   // 🚀 no form.valid check
    console.log("delete initiated");
    this.traineeService.deleteTrainee(this.selectedId).subscribe({
      next: () => { alert('Trainee deleted successfully!'); this.resetForm(form); this.fetchTrainees(); },
      error: (err) => console.error(err)
    });
  }
}

fetchByTechnology() {
  if (this.selectedTech === '') {
    this.fetchTrainees();
  } else {
    this.traineeService.getTraineeGroupedByTechnology(this.selectedTech).subscribe({
      next: (data) => this.trainees = data,
      error: (err) => console.error(err)
    });
  }
}

onSelectId(id: number) {
  this.selectedId = id;
  this.traineeService.getTraineeById(id).subscribe({   // fetch from backend
    next: (data) => {
      this.trainee = { ...data };
    },
    error: (err) => console.error(err)
  });
}

resetForm(form: NgForm) {
  this.mode = 'add';
  this.selectedId = null;
  form.resetForm();
  this.trainee = { id: 0, name: '', stipend: 0, technology: '' };
}
}