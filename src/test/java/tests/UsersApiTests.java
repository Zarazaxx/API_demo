package tests;

import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.UsersController;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.UsersController.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersApiTests {
    Token jwtToken;
    String login="test020226";
    String pass="test020226";
    String newPass="test020226new";
    @BeforeAll
    void getJwtToken(){
        AuthFields testUser=new AuthFields(login,newPass);
        jwtToken = UsersController.createAuthToken(testUser);
    }

    @Test
    void listLastUsers (){
        List<String> Users=getUsers();
        assertThat(Users.size()).isEqualTo(100);
    }
    @Test
    void getMyUser(){
        User myUser= getUser(jwtToken);
        assertThat(myUser.getLogin()).isEqualTo(login);
        assertThat(myUser.getPass()).isEqualTo(pass);
    }
    @Test
    void changePassword(){
        InfoWrapper user=putPasswordUser(jwtToken,newPass);
        assertThat(user.getInfo().getStatus()).isEqualTo("success");
    }
    @Test
    void deleteMyUser(){
        InfoWrapper user=deleteUser(jwtToken);
        assertThat(user.getInfo().getStatus()).isEqualTo("success");
    }
    @Test
    void createNewUser(){
        String loginReg="test"+ new Date();
        String passReg=loginReg;
        User newUser= new User(null,null,loginReg,passReg);
        RegistrationResponse newRegistrationUser= registrationNewUser(newUser);
        assertThat(newRegistrationUser.getInfo().getStatus()).isEqualTo("success");
        assertThat(newRegistrationUser.getRegister_data().getGames().size()).isEqualTo(0);
        assertThat(newRegistrationUser.getRegister_data().getId()).isNotNull();
        assertThat(newRegistrationUser.getRegister_data().getLogin()).isEqualTo(loginReg);
        assertThat(newRegistrationUser.getRegister_data().getPass()).isEqualTo(passReg);
    }
}
