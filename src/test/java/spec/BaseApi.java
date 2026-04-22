package spec;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseApi {

        private final String token;

        protected BaseApi() {
            this.token = null;
        }

        protected BaseApi(String token) {
            this.token = token;
        }

        protected RequestSpecification request() {
            RequestSpecification spec = given()
                    .spec(BaseSpecs.baseRequestSpec())
                    .filter(new AllureRestAssured());

            if (token != null) {
                spec.auth().oauth2(token);
            }

            return spec;
        }
    }

