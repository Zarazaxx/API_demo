package tests;

import data.RandomData;
import data.UserBuilder;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.AuthController;
import utils.UsersController;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersApiTests {

    @DisplayName("Получение последних 100 зарегистрированных пользователей")
    @Test
    void listLastUsers (){
        UsersController userApi=new UsersController(null);
        Response users=userApi.getUsers();
        assertThat(users.statusCode()).isEqualTo(200);
        assertThat(users.getBody().asString()).isNotEmpty();
    }
    @DisplayName("Получение профиля своего пользователя")
    @Test
    void getMyUser(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
        Token token= auth.createAuthToken(loginRequest).as(Token.class);
        UsersController userApi=new UsersController(token.getToken());
        Response responseMyUser=userApi.getUser();
        assertThat(responseMyUser.statusCode()).isEqualTo(200);
        UserResponse myUser= responseMyUser.as(UserResponse.class);
        assertThat(myUser.getLogin()).isEqualTo(newUser.getLogin());
        assertThat(myUser.getPass()).isEqualTo(newUser.getPass());
    }
    @DisplayName("Изменение пароля")
    @Test
    void changePassword(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
        Token token= auth.createAuthToken(loginRequest).as(Token.class);
        UsersController userApi=new UsersController(token.getToken());
        LoginRequest onlyPassworg= new LoginRequest(null, RandomData.Password());
        Response responseChangePassword=userApi.putPasswordUser(onlyPassworg);
        assertThat(responseChangePassword.statusCode()).isEqualTo(200);
        InfoWrapper user=responseChangePassword.as(InfoWrapper.class);
        assertThat(user.getInfo().getStatus()).isEqualTo("success");
    }
    @DisplayName("Удаление пользователя")
    @Test
    void deleteMyUser(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
        Token token= auth.createAuthToken(loginRequest).as(Token.class);
        UsersController userApi=new UsersController(token.getToken());
        Response responseDeleteUser=userApi.deleteUser();
        assertThat(responseDeleteUser.statusCode()).isEqualTo(200);
        InfoWrapper user=responseDeleteUser.as(InfoWrapper.class);
        assertThat(user.getInfo().getStatus()).isEqualTo("success");
    }

}
