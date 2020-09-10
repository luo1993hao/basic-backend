package com.basicbackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class BaseApiTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        initAssured();
        resetDb();
    }

    private void initAssured() {
        RestAssured.basePath = "/api";
        RestAssured.requestSpecification =
                new RequestSpecBuilder().setContentType("application/json").build();

        RestAssured.config =
                RestAssuredConfig.config()
                        .objectMapperConfig(
                                new ObjectMapperConfig()
                                        .jackson2ObjectMapperFactory(
                                                (cls, charset) -> objectMapper));
    }

    private void resetDb() {
        List<String> tableNames = Lists.newArrayList(
                "ticket");

        String sql = tableNames.stream()
                .map(table -> "TRUNCATE TABLE " + table + ";")
                .collect(Collectors.joining());

        jdbcTemplate.update(sql, new MapSqlParameterSource());
    }

    protected MockMvcRequestSpecification given() {
        return RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .webAppContextSetup(context);
    }
}
