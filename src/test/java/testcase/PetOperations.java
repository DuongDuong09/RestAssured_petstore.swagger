package testcase;

import constant.RequestCapability;
import io.restassured.http.ContentType;
import helpers.CreatePetBody;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetOperations implements RequestCapability {
    @Test(priority = 1)
    public void Upload_an_image(){
        CreatePetBody projectInfor=new CreatePetBody();
        String petId=projectInfor.getPetIDByStatus("available").get(1).toString();
        given().baseUri(baseUri).
                basePath("/pet/".concat(petId).concat("/uploadImage")).
                header(defaultHeader).
                header(multiHeader).
                queryParam("petId ",petId).
                multiPart("additionalMetadata","data").
                multiPart("file", new File("C:/Users/duonguye/Pictures/Screenshots/Screenshot (1).png")).
                post().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
    @Test(priority = 2)
    public void Add_new_pet_to_the_store(){
        CreatePetBody pet=new CreatePetBody();
        JSONObject requestPayload = new JSONObject(pet.adddata());
        given().baseUri(baseUri).
                basePath("/pet").
                header(defaultHeader).
                header(acceptJSONHeader).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestPayload.toJSONString()).post().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();;
    }
    @Test(priority = 3)
    public void Update_existing_pet() {
        CreatePetBody pet=new CreatePetBody();
        JSONObject requestPayload = new JSONObject(pet.adddata());
        given().baseUri(baseUri).basePath("/pet").
                header(defaultHeader).
                header(acceptJSONHeader).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestPayload.toJSONString()).put().
                then().
                assertThat().
                statusCode(200).
                log().all().extract().response();

    }
    @Test(priority = 4)
    public void Find_pet_by_status(){
        String path="/pet/findByStatus";
        given().baseUri(baseUri).basePath(path).
                queryParam("status", "available").get().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();;
    }
    @Test(priority = 5)
    public void Find_pet_by_ID(){
        String path="/pet/";
        CreatePetBody projectInfor=new CreatePetBody();
        String petID=String.valueOf(projectInfor.getPetIDByStatus("available").get(1));
        given().baseUri(baseUri).
                basePath(path.concat(petID)).
                header(acceptJSONHeader).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                queryParam("petId", petID).
                get().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
    @Test(priority = 6)
    public void Update_pet_with_form_data(){
        CreatePetBody projectInfor=new CreatePetBody();
        String petID=String.valueOf(projectInfor.getPetIDByStatus("available").get(1));
        given().
                baseUri(baseUri).
                basePath("/pet/".concat(petID)).
                header(defaultHeader).
                header(acceptJSONHeader).
                param("petId",Integer.parseInt(petID)).
                formParam("name", "Doggie").
                formParam("status", "sold").
                post().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
    @Test(priority = 7)
    public void Delete_pet(){
        String path="/pet/";
        CreatePetBody projectInfor=new CreatePetBody();
        String petID=String.valueOf(projectInfor.getPetIDByStatus("available").get(1));
        given().baseUri(baseUri).
                basePath(path.concat(petID)).
                header(acceptJSONHeader).
                queryParam("petId", petID).
                delete().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
}

