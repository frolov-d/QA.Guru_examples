package com.jsd.qa_guru_examples.api;

import io.qameta.allure.restassured.AllureRestAssured;
import com.jsd.qa_guru_examples.api.models.lombok.LoginBodyLombokModel;
import com.jsd.qa_guru_examples.api.models.lombok.LoginResponseLombokModel;
import com.jsd.qa_guru_examples.api.models.pojo.LoginBodyPojoModel;
import com.jsd.qa_guru_examples.api.models.pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.jsd.qa_guru_examples.api.helpers.CustomAllureListener.withCustomTemplates;
import static com.jsd.qa_guru_examples.api.specs.LoginSpec.loginRequestSpec;
import static com.jsd.qa_guru_examples.api.specs.LoginSpec.loginResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;

public class ReqresExtendedTests {

    @Test
    void successfulLoginTest() {
        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        given()
                .log().uri()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulLoginWithPojoTest() {
//        String body = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        LoginBodyPojoModel loginBody = new LoginBodyPojoModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponsePojoModel response = given()
                .log().uri()
                .body(loginBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponsePojoModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithLombokTest() {

        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .log().uri()
                .body(loginBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithAllureTest() {

        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured())
                .log().uri()
                .body(loginBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithCustomAllureTest() {

        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates())
                .log().uri()
                .body(loginBody)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void successfulLoginWithAllureStepsTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel response = step("Make request", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .body(loginBody)
                        .contentType(JSON)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void successfulLoginWithAllureSpecsTest() {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail("eve.holt@reqres.in");
        loginBody.setPassword("cityslicka");

        LoginResponseLombokModel response = step("Make request", () ->
                given()
                        .spec(loginRequestSpec)
                        .body(loginBody)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @ParameterizedTest()
    @CsvSource(value = {"eve.holt@reqres.in:cityslicka", "tEst:test", "Java:java"}, delimiter = ':')
    void successfulLoginParametrizedTest(String login, String password) {
        LoginBodyLombokModel loginBody = new LoginBodyLombokModel();
        loginBody.setEmail(login);
        loginBody.setPassword(password);

        LoginResponseLombokModel response = step("Make request", () ->
                given(loginRequestSpec)
                        .body(loginBody)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseLombokModel.class));

        step("Verify response", () ->
                assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4"));
    }

}
