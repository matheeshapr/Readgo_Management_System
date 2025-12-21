package service.impl;

import repository.BookRepository;
import repository.impl.BookRepositoryimpl;
import service.BookService;

public class BookServiceimpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryimpl();

    @Override
    public void addBook(String id, String title, String author, String category, int qty) {
        bookRepository.addBook(id, title, author, category, qty);
    }
}