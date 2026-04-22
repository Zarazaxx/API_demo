package models;

import lombok.*;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private String company;
    private String description;
    private List<Dlc> dlcs;
    private Integer gameId;
    private String genre;
    private Boolean isFree;
    private Double price;
    private String publish_date;
    private Integer rating;
    private Boolean requiredAge;
    private Requirements requirements;
    private List<String> tags;
    private String title;

}
