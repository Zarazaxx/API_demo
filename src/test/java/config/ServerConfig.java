package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:serverConfig.properties")

public interface ServerConfig extends Config {
    String uri();

}
