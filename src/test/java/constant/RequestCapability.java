package constant;

import io.restassured.http.Header;

public interface RequestCapability {
    Header defaultHeader=new Header("Content-type","application/json; charset-UTF-8");
    Header multiHeader=new Header("Content-type","multipart/form-data");
    Header acceptJSONHeader=new Header("Accept"," application/json");
    String baseUri="https://petstore.swagger.io/v2";

}