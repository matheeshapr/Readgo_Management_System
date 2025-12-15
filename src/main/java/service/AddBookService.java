package service;

import model.entity.Books;
import repository.AddBookRepository;

public class AddBookService {

    private AddBookRepository addBookRepository = new AddBookRepository();

    public String saveBook (Books books){
        return addBookRepository.save(books);
    }
}
