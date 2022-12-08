package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestRequest {

    public static Response getRequest(String baseUri, String path, Map<String, String> params) {

        return given()
                .baseUri(baseUri)
                .basePath(path)
                .params(params)
                .log().uri()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .log().body()
                .extract()
                .response();
    }
}