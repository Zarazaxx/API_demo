package spec;

import config.ServerConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.filter.log.LogDetail.ALL;

public class BaseSpecs {
    static ServerConfig config = ConfigFactory.create(ServerConfig.class);
    static String baseUri = config.uri();
    public static RequestSpecification baseRequestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("http://"+baseUri)
                .setBasePath("/api")
                .setPort(8080)
                .log(ALL)
                .setContentType(ContentType.JSON)
                .build();
    }
}
