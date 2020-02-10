import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private BASE_URL = 'http://localhost:8080/api/v1/books';

  constructor(private http: HttpClient) {}

  getBook(id: number): Observable<any> {
    return this.http.get(`${this.BASE_URL}/${id}`);
  }

  createBook(book: Object): Observable<Object> {
    return this.http.post(`${this.BASE_URL}`, book);
  }

  updateBook(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.BASE_URL}/${id}`, value);
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(`${this.BASE_URL}/${id}`, { responseType: 'text' });
  }

  getBooksList(page: number, size: number): Observable<any> {
    let param: any = {'page': page - 1, 'size': size};
    return this.http.get(`${this.BASE_URL}`, {params: param});
  }
}
