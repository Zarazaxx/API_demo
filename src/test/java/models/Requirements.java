package models;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requirements {
    private Integer hardDrive;
    private String osName;
    private Integer ramGb;
    private String videoCard;
}
