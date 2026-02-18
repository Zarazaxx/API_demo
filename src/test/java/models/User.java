package models;

import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private List<Game> games;
    private Integer id;
    private String login;
    private String pass;
}
