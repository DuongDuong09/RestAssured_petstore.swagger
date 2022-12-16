package testcase;

import constant.RequestCapability;
import helpers.CreateStoreBodyByExcelReader;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StoreOperations implements RequestCapability {
    CreateStoreBodyByExcelReader excel=new CreateStoreBodyByExcelReader();
    @Test(priority = 1)
    public void Place_order_for_pet() throws IOException {
        given().
                baseUri(baseUri).basePath("/store/order").
                header(defaultHeader).
                header(acceptJSONHeader).
                    accept(ContentType.JSON).
                    contentType(ContentType.JSON).
                body(excel.adddata().toString()).post().
                then().
                assertThat().
                statusCode(200).
                log().body().extract().response();;
    }
    @Test(priority = 2)
    public void Find_purchase_order_byID() throws IOException {
        String path = "/store/order/";
        given().
                baseUri(baseUri).
                basePath(path.concat(excel.adddata().get("id").toString())).
                header(acceptJSONHeader).
                param("orderId",excel.adddata().get("id")).
                get().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
    @Test(priority = 3)
    public void Delete_purchase_found_byID() throws IOException {
        String path="/store/order/";
        given().
                baseUri(baseUri).
                header(acceptJSONHeader).
                param("orderId ", excel.adddata().get("id")).
                delete(path.concat(excel.adddata().get("id").toString())).
                then().
                assertThat().
                statusCode(404).
                log().body().extract().response();
    }
    @Test(priority = 4)
    public void Return_pet_inventories_by_status(){
        String path = "/store/inventory";
        given().
                baseUri(baseUri).basePath(path).
                header(acceptJSONHeader).
                get().then().
                assertThat().
                statusCode(200).
                log().body().extract().response();
    }
}
