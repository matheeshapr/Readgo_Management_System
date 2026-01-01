package model.dto;

import java.time.LocalDate;

public class ReturnDTO {
    private String rentId;
    private String bookId;
    private LocalDate returnDate;
    private int lateDays;
    private double fine;

    public ReturnDTO(String rentId, String bookId, LocalDate returnDate, int lateDays, double fine) {
        this.rentId = rentId;
        this.bookId = bookId;
        this.returnDate = returnDate;
        this.lateDays = lateDays;
        this.fine = fine;
    }

    public String getRentId() { return rentId; }
    public String getBookId() { return bookId; }
    public LocalDate getReturnDate() { return returnDate; }
    public int getLateDays() { return lateDays; }
    public double getFine() { return fine; }
}