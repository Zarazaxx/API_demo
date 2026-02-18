package models;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Requirement {
    private Integer hardDrive;
    private String osName;
    private Integer ramGb;
    private String videoCard;
}
