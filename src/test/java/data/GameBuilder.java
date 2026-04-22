package data;

import models.AdditionalData;
import models.Dlc;
import models.Requirements;
import models.Game;

import java.util.ArrayList;
import java.util.List;


public class GameBuilder {
    private String company=RandomData.CompanyName();
    private String description=RandomData.GameDescription();
    private String genre=RandomData.Genre();
    private Boolean isFree=RandomData.IsFree();
    private Double price=RandomData.Price(isFree);
    private String publishDate=RandomData.PublishDate();
    private Integer rating=RandomData.Rating();
    private Boolean requiredAge=RandomData.RequiredAge();
    private String title=RandomData.Title();
    private List<String> tags=RandomData.Tags(3);
    private Requirements requirements=generateRequirements();
    private List<Dlc> dlcs=new ArrayList<>();

    private Requirements generateRequirements(){
        Requirements r = new Requirements();
        r.setRamGb(RandomData.RamGb()); // 4..64
        r.setHardDrive(RandomData.HardDrive()); // 32..1024
        r.setOsName(RandomData.OsName());
        r.setVideoCard(RandomData.VideoCard());
        return r;
    }
    public GameBuilder withDlcs(int count) {
        this.dlcs = generateDlcs(count);
        return this;
    }
    private List<Dlc> generateDlcs(int count){
        List<Dlc> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateDlc());
        }
        return list;
    }

    private Dlc generateDlc(){
        Dlc d=new Dlc();
        d.setDescription(RandomData.DlcDescription());
        d.setDlcName(RandomData.DlcName());
        d.setIsDlcFree(RandomData.IsFree());
        d.setPrice(RandomData.Price(d.isDlcFree));
        d.setRating(RandomData.Rating());
        d.setSimilarDlc(generateSimilarDlc());
        return d;
    }
    private AdditionalData generateSimilarDlc() {
        AdditionalData s = new AdditionalData();
        s.setDlcNameFromAnotherGame(RandomData.DlcName());
        s.setIsFree(RandomData.IsFree());
        return s;
    }
    public Game build() {
        Game game = new Game();
        game.setCompany(company);
        game.setDescription(description);
        game.setDlcs(dlcs);
        game.setGenre(genre);
        game.setIsFree(isFree);
        game.setPrice(price);
        game.setPublish_date(publishDate);
        game.setRating(rating);
        game.setRequiredAge(requiredAge);
        game.setRequirements(requirements);
        game.setTags(tags);
        game.setTitle(title);
        return game;
    }
}
