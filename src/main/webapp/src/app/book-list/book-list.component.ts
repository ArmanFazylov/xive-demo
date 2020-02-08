import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookDetailsComponent } from '../book-details/book-details.component';
import { Observable } from "rxjs";
import { BookService } from "../book.service";
import { Book } from "../book";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
  books: Observable<Book[]>;
  p: number = 1;
  config: any;

  constructor(
    private bookService: BookService,
    private router: Router) {
    this.config = {
      currentPage: 1,
      itemsPerPage: 10,
      totalItems: 100
    };
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.books = this.bookService.getBooksList(this.config.currentPage, this.config.itemsPerPage);
  }

  deleteBook(id: number) {
    this.bookService.deleteBook(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => {
          console.log(error)
        });
  }

  bookDetails(id: number) {
    this.router.navigate(['details', id]);
  }

  pageChange(newPage: number) {
    this.config.currentPage = newPage;
    this.bookService.getBooksList(newPage, this.config.itemsPerPage)
  }

}
