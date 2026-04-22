package data;

import models.Game;
import models.UserRequest;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    private String userName=RandomData.UserName();
    private String userPassword=RandomData.Password();
    private List<Game> games=new ArrayList<>();
    public UserBuilder withGames(int count) {
        this.games = generateGames(count);
        return this;
    }
    private List<Game> generateGames(int count){
        List<Game> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int cntDlcs=RandomData.Count();
            Game g= new GameBuilder()
                    .withDlcs(cntDlcs)
                    .build();
            list.add(g);
        }
        return list;
    }
    public UserRequest build(){
        UserRequest user=new UserRequest();
        user.setLogin(userName);
        user.setPass(userPassword);
        user.setGames(games);
        return user;
    }
}
