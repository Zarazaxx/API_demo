package tests;

import data.RandomData;
import data.UserBuilder;
import help.fixtures.TestFixtures;
import io.restassured.response.Response;
import models.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.AuthController;
import utils.UsersController;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class UsersApiTests {
    @DisplayName("Get last 100 users")
    @Test
    void listLastUsers (){
        UsersController userApi=new UsersController(null);
        Response users=userApi.getUsers();
        assertThat(users.statusCode()).isEqualTo(200);
        assertThat(users.getBody().asString()).isNotEmpty();
    }
    @DisplayName("Get profile")
    @Test
    @Tag("User")
    @Tag("positive")
    void getMyUser(){
        AuthController auth=new AuthController();
        UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
        LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
        Token token= auth.createAuthToken(loginRequest).as(Token.class);
        UsersController userApi=new UsersController(token.getToken());
        Response responseMyUser=userApi.getUser();
        assertThat(responseMyUser.statusCode()).isEqualTo(200);
        responseMyUser.then().body(matchesJsonSchemaInClasspath("schemas/UserResponse.json"));
        UserResponse myUser= responseMyUser.as(UserResponse.class);
        assertThat(myUser.getLogin()).isEqualTo(newUser.getLogin());
        assertThat(myUser.getPass()).isEqualTo(newUser.getPass());
    }
    @DisplayName("Change password")
    @Test
    @Tag("User")
    @Tag("positive")
    void changePassword(){
        Token token= TestFixtures.createAndLoginUser();
        UsersController userApi=new UsersController(token.getToken());
        LoginRequest onlyPassword= new LoginRequest(null, RandomData.Password());
        Response responseChangePassword=userApi.putPasswordUser(onlyPassword);
        assertThat(responseChangePassword.statusCode()).isEqualTo(200);
        responseChangePassword.then().body(matchesJsonSchemaInClasspath("schemas/InfoResponse.json"));
        InfoWrapper user=responseChangePassword.as(InfoWrapper.class);
        assertThat(user.getInfo().getStatus()).isEqualTo("success");
    }
    @DisplayName("Delete user")
    @Test
    @Tag("User")
    @Tag("positive")
    void deleteMyUser(){
        Token token= TestFixtures.createAndLoginUser();
        UsersController userApi=new UsersController(token.getToken());
        Response responseDeleteUser=userApi.deleteUser();
        assertThat(responseDeleteUser.statusCode()).isEqualTo(200);
        responseDeleteUser.then().body(matchesJsonSchemaInClasspath("schemas/InfoResponse.json"));
        InfoWrapper user=responseDeleteUser.as(InfoWrapper.class);
        assertThat(user.getInfo().getStatus()).isEqualTo("success");
    }
    @DisplayName("Get profile without token")
    @Test
    @Tag("negative")
    @Tag("User")
    void getUser_withoutToken_shouldFail() {
        UsersController userApi = new UsersController(null);
        Response response = userApi.getUser();
        assertThat(response.statusCode()).isEqualTo(401);
    }
    @DisplayName("Get profile with invalid token")
    @Test
    @Tag("negative")
    @Tag("User")
    void getUser_invalidToken_shouldFail() {
        UsersController userApi = new UsersController("invalid_token");
        Response response = userApi.getUser();
        assertThat(response.statusCode()).isEqualTo(401);
    }
    @DisplayName("Get deleted profile")
    @Test
    @Tag("negative")
    @Tag("User")
    void getUser_deletedUser_shouldFail() {
        Token token = TestFixtures.createAndLoginUser();
        UsersController userApi = new UsersController(token.getToken());
        userApi.deleteUser(); // удалили
        Response response = userApi.getUser();
        assertThat(response.statusCode()).isEqualTo(401);
    }

    @DisplayName("Get profile")@Test
    @Tag("negative")
    @Tag("User")
    void changePassword_withoutToken_shouldFail() {
        UsersController userApi = new UsersController(null);
        LoginRequest request = new LoginRequest(null, RandomData.Password());
        Response response = userApi.putPasswordUser(request);
        assertThat(response.statusCode()).isEqualTo(401);

    }
    @DisplayName("Change password with empty password")
    @Test
    @Tag("negative")
    @Tag("User")
    void changePassword_emptyPassword_shouldFail() {
        Token token = TestFixtures.createAndLoginUser();
        UsersController userApi = new UsersController(token.getToken());
        LoginRequest request = new LoginRequest(null, "");
        Response response = userApi.putPasswordUser(request);
        assertThat(response.statusCode()).isEqualTo(400);
        assertThat(response.jsonPath().getString("info.status")).isEqualTo("fail");
    }
    @DisplayName("Delete user without token")
    @Test
    @Tag("negative")
    @Tag("User")
    void deleteUser_withoutToken_shouldFail() {
        UsersController userApi = new UsersController(null);
        Response response = userApi.deleteUser();
        assertThat(response.statusCode()).isEqualTo(401);
    }
    @DisplayName("Delete user twice")
    @Test
    @Tag("negative")
    @Tag("User")
    void deleteUser_twice_shouldFail() {
        Token token = TestFixtures.createAndLoginUser();
        UsersController userApi = new UsersController(token.getToken());
        userApi.deleteUser();
        Response response = userApi.deleteUser();
        assertThat(response.statusCode()).isEqualTo(401);
    }
}
