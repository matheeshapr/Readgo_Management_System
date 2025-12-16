package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO {
    private String id;
    private String title;
    private String author;
    private String category;
    private int qty;
}
