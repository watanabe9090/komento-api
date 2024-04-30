package br.com.takaedev.komento.threads;

import br.com.takaedev.komento.BasicSetup;
import br.com.takaedev.komento.base.InvalidAttributeException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ThreadControllerTest extends BasicSetup {

    @Test
    public void saveThreadFlow() throws InvalidAttributeException {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "name": "Post-Test"
                        }
                        """)
                .when()
                .post("/api/v1/threads")
                .then()
                .statusCode(201);
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v1/threads/Post-Test")
                .then()
                .statusCode(200)
                .and()
                .body("name", equalTo("Post-Test"));
    }


}