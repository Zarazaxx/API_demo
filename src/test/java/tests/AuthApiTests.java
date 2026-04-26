package tests;

import data.RandomData;
import data.UserBuilder;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.AuthController;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthApiTests {
    @DisplayName("Create new user")
    @Test
    @Tag("positive")
    @Tag("signup")
    void createNewUser(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        Response response = auth.registrationNewUser(newUser);
        assertThat(response.statusCode()).isEqualTo(201);
        response.then().body(matchesJsonSchemaInClasspath("schemas/RegistrationResponse.json"));
        RegistrationResponse newRegistrationUser=response.as(RegistrationResponse.class);
        assertThat(newRegistrationUser.getInfo().getStatus()).isEqualTo("success");
        assertThat(newRegistrationUser.getRegister_data().getId()).isNotNull();
        assertThat(newRegistrationUser.getRegister_data().getLogin()).isEqualTo(newUser.getLogin());
        assertThat(newRegistrationUser.getRegister_data().getPass()).isEqualTo(newUser.getPass());
        assertThat(newRegistrationUser.getRegister_data().getGames().size()).isEqualTo(1);
    }
    @DisplayName("Login user")
    @Test
    @Tag("positive")
    @Tag("login")
    void login(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
        Response responseLogin= auth.createAuthToken(loginRequest);
        assertThat(responseLogin.statusCode()).isEqualTo(200);
        responseLogin.then().body(matchesJsonSchemaInClasspath("schemas/LoginResponse.json"));
        assertThat(responseLogin.getBody().asString()).isNotEmpty();
    }

    @DisplayName("Create new user with empty login")
    @Test
    @Tag("negative")
    @Tag("signup")
    void createUser_emptyLogin_shouldFail() {
        AuthController auth = new AuthController();
        UserRequest user = new UserBuilder()
                .withUsername("")
                .build();

        Response response = auth.registrationNewUser(user);
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("info.status")).isEqualTo("fail");
    }
    @DisplayName("Create new user with null login")
    @Test
    @Tag("negative")
    @Tag("signup")
    void createUser_nullLogin_shouldFail() {
        AuthController auth = new AuthController();
        UserRequest user = new UserBuilder()
                .withUsername(null)
                .build();
        Response response = auth.registrationNewUser(user);
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("info.status")).isEqualTo("fail");
    }
    @DisplayName("Create new user with empty password")
    @Test
    @Tag("negative")
    @Tag("signup")
    void createUser_emptyPassword_shouldFail() {
        AuthController auth = new AuthController();
        UserRequest user = new UserBuilder()
                .withPassword(null)
                .build();
        Response response = auth.registrationNewUser(user);
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("info.status")).isEqualTo("fail");
    }
    @DisplayName("Create new duplicate user")
    @Test
    @Tag("negative")
    @Tag("signup")
    void createUser_duplicateUser_shouldFail() {
        AuthController auth = new AuthController();
        UserRequest user = new UserBuilder().build();
        auth.registrationNewUser(user); // первая регистрация
        Response response = auth.registrationNewUser(user); // повторная регистрация пользователя
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("info.status")).isEqualTo("fail");
        assertThat(response.jsonPath().getString("info.message")).isEqualTo("Login already exist");
    }
    @DisplayName("Login user with empty login")
    @Test
    @Tag("negative")
    @Tag("login")
    void login_emptyLogin_shouldFail(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(null, newUser.getPass());
        Response responseLogin= auth.createAuthToken(loginRequest);
        assertThat(responseLogin.statusCode()).isEqualTo(500);
    }
    @DisplayName("Login user with empty password")
    @Test
    @Tag("negative")
    @Tag("login")
    void login_emptyPassword_shouldFail(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), null);
        Response responseLogin= auth.createAuthToken(loginRequest);
        assertThat(responseLogin.statusCode()).isEqualTo(500);
    }
    @DisplayName("Login user with error password")
    @Test
    @Tag("negative")
    @Tag("login")
    void login_errorPassword_shouldFail(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), RandomData.Password());
        Response responseLogin= auth.createAuthToken(loginRequest);
        assertThat(responseLogin.statusCode()).isEqualTo(401);
    }
}
