package service;

import model.dto.BookDTO;

public interface AddBookService {
    void addBook(String id, String title, String author, String category, int qty);
}
