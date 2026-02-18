package models;

import lombok.*;


import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private String company;
    private String discription;
    private List<Dlc> dils;
    private Integer gameId;
    private String genre;
    private Boolean isFree;
    private Double price;
    private String publishDate;
    private Integer rating;
    private Boolean requiredAge;
    private Requirement requirements;
    private List<String> tags;
    private String title;

}
