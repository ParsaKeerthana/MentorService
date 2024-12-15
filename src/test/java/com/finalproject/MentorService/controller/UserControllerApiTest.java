package com.finalproject.MentorService.controller;

import com.finalproject.MentorService.model.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class UserControllerApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = 8080;
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    void registerUser_success() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .statusCode(200)
                .body("username", equalTo("testuser"));
    }

    @Test
    void registerAsMentor_success() {
        Mentor mentor = new Mentor();
        mentor.setName("John Doe");
        mentor.setEmail("john.doe@example.com");
        given()
                .contentType(ContentType.JSON)
                .body(mentor)
                .when()
                .post("/mentors")
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"));
    }

    @Test
    void followUser_success() {
        FollowRequest followRequest = new FollowRequest();
        followRequest.setMenteeId("menteeId");
        followRequest.setMentorId("mentorId");
        given()
                .contentType(ContentType.JSON)
                .body(followRequest)
                .when()
                .post("/followRequests")
                .then()
                .statusCode(200)
                .body("menteeId", equalTo("menteeId"));
    }

    @Test
    void getAllUsers_returns200() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users")
                .then()
                .statusCode(200);
    }

    @Test
    void getAllMentors_returns200() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/mentors")
                .then()
                .statusCode(200);
    }

    @Test
    void getAllMentees_returns200() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/mentees")
                .then()
                .statusCode(200);
    }

    @Test
    void getFollowRequests_returns200() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/followRequests")
                .then()
                .statusCode(200);
    }

    @Test
    void getAllFollows_returns200() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/follows")
                .then()
                .statusCode(200);
    }



}