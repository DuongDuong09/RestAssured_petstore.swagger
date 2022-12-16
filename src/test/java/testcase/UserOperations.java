package testcase;

import constant.RequestCapability;
import helpers.CreateUserBodyByJSONReader;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserOperations implements RequestCapability {
    CreateUserBodyByJSONReader reader=new CreateUserBodyByJSONReader();
    @Test(priority = 1, enabled = true)
    public void Create_list_user_with_given_input_array() throws IOException {
        given().
                baseUri(baseUri).basePath("/user/createWithArray").
                header(defaultHeader).
                header(acceptJSONHeader).
                    accept(ContentType.JSON).
                    contentType(ContentType.JSON).
                body(reader.UserDataAsArrayList(2)).post().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }

    @Test(priority = 2)
    public void Create_user() throws IOException {
        given().baseUri(baseUri).basePath("/user").
                header(defaultHeader).
                header(acceptJSONHeader).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(reader.UserDataAsString(1)).post().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }

    @Test(priority = 3)
    public void Get_user_by_username() throws IOException {
        String path = "/user/";
        String username =reader.UserDataAsString(1).get("username").toString() ;
        given().baseUri(baseUri).basePath(path.concat(username)).
                param("username", username).get().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }

    @Test(priority = 4)
    public void Update_user() throws IOException {
        given().baseUri(baseUri).basePath("/user/").
                header(defaultHeader).
                header(acceptJSONHeader).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(reader.UserDataAsString(0)).put( reader.UserDataAsString(1).get("username").toString()).
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }

    @Test(priority = 5)
    public void Log_user_into_the_system() throws IOException {
        String path = "/user/login";
        given().baseUri(baseUri).basePath(path).header(acceptJSONHeader).
                queryParam("username", reader.UserDataAsString(1).get("username").toString()).
                queryParam("password", reader.UserDataAsString(1).get("password").toString()).
                get().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }

    @Test(priority = 6)
    public void Delete_user() throws IOException {
        String path = "/user/";
        given().baseUri(baseUri).basePath(path.concat(reader.UserDataAsString(1).get("username").toString())).
                header(acceptJSONHeader).
                param("username", reader.UserDataAsString(1).get("username").toString())
                .delete().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }

    @Test(priority = 6)
    public void Log_out_current_login_user_session() {
        String path = "/user/logout";
        given().baseUri(baseUri).basePath(path).
                header(acceptJSONHeader).
                get().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
}

