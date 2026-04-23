package tests;

import data.UserBuilder;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AuthController;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthApiTests {
    @DisplayName("Create new user")
    @Test
    void createNewUser(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        Response response = auth.registrationNewUser(newUser);
        assertThat(response.statusCode()).isEqualTo(201);
        RegistrationResponse newRegistrationUser=response.as(RegistrationResponse.class);
        assertThat(newRegistrationUser.getInfo().getStatus()).isEqualTo("success");
        assertThat(newRegistrationUser.getRegister_data().getId()).isNotNull();
        assertThat(newRegistrationUser.getRegister_data().getLogin()).isEqualTo(newUser.getLogin());
        assertThat(newRegistrationUser.getRegister_data().getPass()).isEqualTo(newUser.getPass());
        assertThat(newRegistrationUser.getRegister_data().getGames().size()).isEqualTo(1);
    }
    @DisplayName("Login user")
    @Test
    void login(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
        Response responseLogin= auth.createAuthToken(loginRequest);
        assertThat(responseLogin.statusCode()).isEqualTo(200);
        assertThat(responseLogin.getBody().asString()).isNotEmpty();
    }
}
