package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dlc {
    private String description;
    private String dlcName;
    private Boolean isDlcFree;
    private Double price;
    private Integer rating;
    private AdditionalData similarDlc;

}
