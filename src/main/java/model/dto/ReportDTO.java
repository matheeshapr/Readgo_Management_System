package model.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ReportDTO {

    private String name;
    private String id;
    private String description;
    private Date date;
}
