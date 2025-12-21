package repository;

public interface BookRepository {
    void addBook(String id, String title, String author, String category, int qty);
}
