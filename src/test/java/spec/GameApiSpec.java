package spec;

import config.ServerConfig;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.*;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.with;

public class GameApiSpec {
    static ServerConfig config = ConfigFactory.create(ServerConfig.class);
    static String baseUri = config.uri();
    public static RequestSpecification success_request_no_auth = with()
            .baseUri("http://"+baseUri)
            .basePath("/api")
            .port(8080)
            .log().all()
            .contentType(ContentType.JSON);

    public static ResponseSpecification success_responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();
    public static ResponseSpecification success_responseCreateSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectContentType(ContentType.JSON)
            .build();
}
