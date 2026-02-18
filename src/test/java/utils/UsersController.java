package utils;

import io.restassured.common.mapper.TypeRef;
import models.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static spec.GameApiSpec.*;

public class UsersController {
    public static List<String>getUsers(){
        return given()
                .spec(success_request_no_auth)
                .when()
                .get("/users")
                .then()
                .spec(success_responseSpec)
                .extract().as(new TypeRef<List<String>>() {
                });

    }
    public static User getUser(Token jwtToken){
        return given()
                .spec(success_request_no_auth)
                .header("Authorization", "Bearer "+ jwtToken.getToken())
                .when()
                .get("/user")
                .then()
                .spec(success_responseSpec)
                .extract().as(User.class);
    }
    public static InfoWrapper putPasswordUser(Token jwtToken,String newPass){
        // TODO подумать и вынести отдельно
        AuthFields body= new AuthFields(null,newPass);
        return given()
                .spec(success_request_no_auth)
                .header("Authorization", "Bearer "+ jwtToken.getToken())
                .body(body)
                .when()
                .put("/user")
                .then()
                .spec(success_responseSpec)
                .log().all()
                .extract().as(InfoWrapper.class);
    }
    public static InfoWrapper deleteUser(Token jwtToken){
        return given()
                .spec(success_request_no_auth)
                .header("Authorization", "Bearer "+ jwtToken.getToken())
                .when()
                .delete("/user")
                .then()
                .spec(success_responseSpec)
                .extract().as(InfoWrapper.class);
    }
    public static Token createAuthToken(AuthFields body){
        return given()
                .spec(success_request_no_auth)
                .body(body)
                .when()
                .post("/login")
                .then()
                .spec(success_responseSpec)
                .extract().as(Token.class);
    }
    public static RegistrationResponse registrationNewUser(User userData){
        return given()
                .spec(success_request_no_auth)
                .header("accept", "application/json")
                .body(userData)
                .when()
                .post("/signup")
                .then()
                .spec(success_responseCreateSpec)
                .extract().as(RegistrationResponse.class);

    }
}
