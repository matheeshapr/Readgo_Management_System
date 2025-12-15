package model.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Books {
    private String id;
    private String title;
    private String author;
    private String category;
    private int qty;
}
