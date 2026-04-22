package utils;

import io.restassured.response.Response;

import models.LoginRequest;
import models.UserRequest;
import spec.BaseApi;



public class AuthController extends BaseApi {
    public Response createAuthToken(LoginRequest body){
        return request()
                .body(body)
                .when()
                .post("/login")
                .then()
                .extract().response();
    }
    public Response registrationNewUser(UserRequest user) {
        return request()
                .body(user)
                .header("accept", "*/*")
                .when()
                .post("/signup")
                .then()
                .log().all()
                .extract().response();
    }
}
