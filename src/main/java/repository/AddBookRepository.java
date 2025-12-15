package repository;

import model.dto.BookDTO;

public interface AddBookRepository {
    void addBook(String id, String title, String author, String category, int qty);
}
