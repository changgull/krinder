package dev.changgull.core;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApi extends Base {
    private RequestSpecification _request;

    protected void buildRequest(String baseUri) {
        RestAssured.defaultParser = Parser.JSON;
        Header header = new Header("User-Agent", "Krinder-by-changgull.dev");
        setRequest(given().header(header).baseUri(baseUri));
    }

    protected void setRequest(RequestSpecification request) {
        _request = request;
    }

    public RequestSpecification getRequest() {
        return _request;
    }
}
