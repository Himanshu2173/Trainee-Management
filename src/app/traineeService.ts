
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
export interface Trainee {
  id?: number;
  name: string;
  stipend: number;
  technology: string;
}
@Injectable({ providedIn: 'root' })
export class TraineeService {
  private apiUrl = 'http://localhost:8081/trainee';
  constructor(private http: HttpClient) { }
  addTrainee(trainee: Trainee): Observable<Trainee> {
    return this.http.post<Trainee>(this.apiUrl, trainee);
  }
  getTraineeById(id: number): Observable<Trainee> {
    return this.http.get<Trainee>(`${this.apiUrl}/${id}`);
  }
  getAllTrainees(): Observable<Trainee[]> {
    return this.http.get<Trainee[]>(this.apiUrl + "s");
  }

  getTraineeGroupedByTechnology(tech: String): Observable<Trainee[]> {
    return this.http.get<Trainee[]>(`${this.apiUrl}/tech/${tech}`);
  }

  updateTrainee(id: number, t: Trainee): Observable<Trainee> {
    return this.http.put<Trainee>(`${this.apiUrl}/${id}`, t)
  }

  deleteTrainee(id: number): Observable<Trainee> {
    return this.http.delete<Trainee>(`${this.apiUrl}/${id}`)
  }
}
