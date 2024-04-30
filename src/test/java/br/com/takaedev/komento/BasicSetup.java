package br.com.takaedev.komento;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static java.util.Collections.singletonList;


public class BasicSetup {
    @LocalServerPort
    private Integer port;

    //    static MongoDBContainer mongo = new MongoDBContainer("mongo:4.0.10");
    static PostgreSQLContainer postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.2"))
            .withDatabaseName("komento-api-testing")
            .withUsername("test")
            .withPassword("tset");


    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void beforeEach() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }


    protected String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("grant_type", singletonList("password"));
        map.put("client_id", singletonList("komento-api-client"));
        map.put("username", singletonList("admin"));
        map.put("password", singletonList("admin"));
        String authServerUrl = "http://localhost:9090/realms/komento-api/protocol/openid-connect/token";
        var request = new HttpEntity<>(map, httpHeaders);

        KeyCloakToken token = restTemplate.postForObject(
                authServerUrl,
                request,
                KeyCloakToken.class
        );

        assert token != null;
        return token.accessToken();
    }

    record KeyCloakToken(@JsonProperty("access_token") String accessToken) {
    }
}
