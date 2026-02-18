package models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RegistrationResponse {
    private InfoResponse info;
    private User register_data;
}
