package service.impl;

import model.dto.BookDTO;
import repository.AddBookRepository;
import repository.impl.AddBookRepositoryimpl;
import service.AddBookService;

public class AddBookServiceimpl implements AddBookService {

    private AddBookRepository bookRepository = new AddBookRepositoryimpl();

    @Override
    public void addBook(String id, String title, String author, String category, int qty) {
        bookRepository.addBook(id, title, author, category, qty);
    }
}