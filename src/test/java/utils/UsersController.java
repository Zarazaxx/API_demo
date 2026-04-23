package utils;

import io.restassured.response.Response;
import models.*;
import spec.BaseApi;



public class UsersController extends BaseApi {
    public UsersController(String token) {
        super(token);
    }
    public Response getUsers(){
        return request()
                .when()
                .get("/users")
                .then()
                .log().all()
                .extract().response();

    }
    public Response getUser(){
        return request()
                .when()
                .get("/user")
                .then()
                .log().all()
                .extract().response();
    }
    public Response putPasswordUser(LoginRequest body){
        return request()
                .body(body)
                .when()
                .put("/user")
                .then()
                .log().all()
                .extract().response();
    }
    public Response deleteUser(){
        return request()
                .when()
                .delete("/user")
                .then()
                .log().all()
                .extract().response();
    }

}
