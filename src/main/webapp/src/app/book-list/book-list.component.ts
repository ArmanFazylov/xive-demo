import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BookService } from "../book.service";
import {Book, IBook} from "../book";

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {
  books: Book[];
  config: any;

  constructor(
    private bookService: BookService,
    private router: Router) {
    this.config = {
      currentPage: 1,
      itemsPerPage: 10,
      totalItems: 200
    };
  }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
   this.bookService.getBooksList(this.config.currentPage, this.config.itemsPerPage)
      .subscribe(data => {
        this.books = data.content;
        this.config.totalItems = data.totalElements;
      }, error => {
        console.log(error);
      });
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
    this.reloadData();
  }

}
