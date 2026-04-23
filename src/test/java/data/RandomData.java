package data;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.List;


public class RandomData {
    private static final Faker faker = new Faker();

    public static String UserName(){return "autotest."+faker.lorem().word()+faker.number().randomNumber();}
    public static String Password(){
        return faker.internet().password();
    }
    public static String CompanyName(){
        return faker.company().name();
    }
    public static String GameDescription(){
        return faker.lorem().paragraph(2);
    }
    public static String DlcDescription(){
        return "DLS "+faker.lorem().paragraph(2);
    }
    public static String DlcName(){
        return faker.app().name();
    }
    public static Boolean IsFree(){return faker.bool().bool();}
    public static Double Price(Boolean isFree){
        if (isFree) {return 0.0;}
            else return faker.number().randomDouble(2,0,99);
    }

    public static String Genre(){
        List<String> genreList = List.of("First-person shooter",
                "Puzzle",
                "Platformer",
                "Action",
                "Adventure",
                "Real-time strategy",
                "Action role-playing",
                "Dungeon crawl",
                "Roguelike",
                "Tactical role-playing",
                "Sports",
                "Simulation",
                "Fighting",
                "Massively multiplayer online",
                "Stealth",
                "Survival",
                "Rhythm",
                "Survival horror",
                "Text adventure",
                "Visual novel",
                "Real-time strategy",
                "Multiplayer online battle arena",
                "Tower defense",
                "Trivia",
                "Real-time tactics",
                "Hack and slash",
                "Battle royale",
                "Third-person shooter");
        return genreList.get(faker.random().nextInt(genreList.size()));

    }
    public static String PublishDate(){
        Date date = faker.date().past(2000, TimeUnit.DAYS);
        return date.toInstant().toString();
    }
    public static Integer Rating(){return faker.number().numberBetween(1,10);}
    public static Boolean RequiredAge(){return faker.bool().bool();}
    public static Integer HardDrive(){return faker.number().numberBetween(1,50);}

    public static String OsName(){
        List<String> osList = List.of(
            "Windows 10",
            "Windows 11",
            "macOS Ventura",
            "Ubuntu 22.04",
            "Android 14",
            "iOS 17"
    );

        return osList.get(faker.random().nextInt(osList.size()));
    }
    public static Integer RamGb(){
        int power=faker.number().numberBetween(2,6);
        return (int)Math.pow(2,power);
    }
    public static String VideoCard(){
        List<String> videoCardList=List.of(
                "RTX 4090", "RTX 4080", "RTX 4070 Ti", "RTX 4070", "RTX 4060 Ti", "RTX 4060",
                "RTX 3090 Ti", "RTX 3090", "RTX 3080 Ti", "RTX 3080", "RTX 3070 Ti", "RTX 3070", "RTX 3060 Ti", "RTX 3060",
                "RX 7900 XTX", "RX 7900 XT", "RX 7800 XT", "RX 7700 XT", "RX 7600",
                "RX 6950 XT", "RX 6900 XT", "RX 6800 XT", "RX 6800", "RX 6750 XT", "RX 6700 XT", "RX 6600 XT", "RX 6600",
                "Intel Arc A770", "Intel Arc A750", "Intel Arc A580", "Intel Arc A380"
        );
        return videoCardList.get(faker.random().nextInt(videoCardList.size()));
    }
    public static List<String> Tags(Integer cnt){
        List<String> tags=new ArrayList<>();
        for (int i=0;i<cnt;++i){
                tags.add(faker.hacker().verb());}
        return tags;
    }
    public static String Title(){
        List<String> titleList = List.of("Team Fortress Classic",
                "Team Fortress 2",
                "Left 4 Dead",
                "Left 4 Dead 2",
                "Day of Defeat",
                "Ricochet",
                "Dota 2",
                "Counter-Strike",
                "Counter-Strike: Source",
                "Counter-Strike: Global Offensive",
                "Super Mario Bros.",
                "Super Mario Bros. 2",
                "Super Mario Bros. 3",
                "Super Mario World",
                "Super Mario Sunshine",
                "Super Mario Galaxy",
                "Super Mario Galaxy 2",
                "Super Mario Odyssey",
                "Mario Kart 64",
                "Mario Kart: Double Dash",
                "Mario Kart DS",
                "Mario Kart Wii",
                "Mario Kart 7",
                "Mario Kart 8",
                "Mario Kart 8 Deluxe",
                "Animal Crossing",
                "Animal Crossing: Wild World",
                "Animal Crossing: City Folk",
                "Animal Crossing: New Leaf",
                "Civilization III",
                "Civilization IV",
                "Civilization V",
                "Civilization VI",
                "The Legend of Zelda",
                "The Legend of Zelda: Ocarina of Time",
                "The Legend of Zelda: Twilight Princess",
                "The Legend of Zelda: Skyward Sword",
                "The Legend of Zelda: Breath of the Wild",
                "Pong",
                "Pac-Man",
                "Starcraft",
                "Starcraft II",
                "Overwatch",
                "Hearthstone",
                "Halo: Combat Evolved",
                "Halo 2",
                "Halo 3",
                "Halo 3: ODST",
                "Halo: Reach",
                "Halo 4",
                "Halo 5: Guardians",
                "Wii Sports",
                "Wii Sports Resort",
                "Wii Play",
                "Wii Music",
                "Pokémon Red",
                "Pokémon Blue",
                "Pokémon Yellow",
                "Pokémon Gold",
                "Pokémon Silver",
                "Pokémon Crystal",
                "Pokémon Ruby",
                "Pokémon Sapphire",
                "Pokémon FireRed",
                "Pokémon LeafGreen",
                "Pokémon Emerald",
                "Pokémon Diamond",
                "Pokémon Pearl",
                "Pokémon Platinum",
                "Pokémon HeartGold",
                "Pokémon SoulSilver",
                "Pokémon Black",
                "Pokémon White",
                "Pokémon Black 2",
                "Pokémon White 2",
                "Pokémon X",
                "Pokémon Y",
                "Pokémon Omega Ruby",
                "Pokémon Alpha Sapphire",
                "Pokémon Sun",
                "Pokémon Moon",
                "Pokémon Ultra Sun",
                "Pokémon Ultra Moon",
                "Pokémon Sword",
                "Pokémon Shield",
                "Doom",
                "Doom II",
                "Doom 3: BFG",
                "Quake");
        return titleList.get(faker.random().nextInt(titleList.size()));

    }
    public static Integer Count(){return faker.number().numberBetween(1,10);}
}
