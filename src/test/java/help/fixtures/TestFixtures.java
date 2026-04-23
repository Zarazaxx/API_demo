package help.fixtures;

import data.UserBuilder;
import models.LoginRequest;
import models.Token;
import models.UserRequest;
import utils.AuthController;

public class TestFixtures {
    public static Token createAndLoginUser(){
    AuthController auth=new AuthController();
    UserRequest newUser= new UserBuilder().withGames(1).build();
        auth.registrationNewUser(newUser);
    LoginRequest loginRequest=new LoginRequest(newUser.getLogin(), newUser.getPass());
    return auth.createAuthToken(loginRequest).as(Token.class);
    }
}
