package model.dto;

import java.time.LocalDate;

public class RentDTO {
    private String rentId;
    private String bookId;
    private String custId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String status;

    public RentDTO(String rentId, String bookId, String custId, LocalDate issueDate, LocalDate dueDate, String status) {
        this.rentId = rentId;
        this.bookId = bookId;
        this.custId = custId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters
    public String getRentId() { return rentId; }
    public void setRentId(String rentId) { this.rentId = rentId; }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getCustId() { return custId; }
    public void setCustId(String custId) { this.custId = custId; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}