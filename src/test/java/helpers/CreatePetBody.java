package helpers;

import constant.RequestCapability;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.*;

public class CreatePetBody implements RequestCapability {
    public JSONObject adddata(){
        Map<String, Object> petTags01 = new HashMap<String, Object>();
        petTags01.put("id", 300);
        petTags01.put("name", RandomData.RandomDataInput());

        List<Map<String, Object>> petTagsList = new ArrayList<Map<String, Object>>();
        petTagsList.add(petTags01);


        Map<String, Object> petCategory = new HashMap<String, Object>();
        petCategory.put("id", 200);
        petCategory.put("name", RandomData.RandomDataInput());

        Map<String, Object> payload = new HashMap<String, Object>();
        payload.put("id", 100);
        payload.put("category", petCategory);
        payload.put("name", "Pet Doggie");
        payload.put("photoUrls", Arrays.asList("Pictures"));
        payload.put("tags", petTagsList);
        payload.put("status", "available");

        JSONObject requestPayload = new JSONObject(payload);
        return requestPayload;
    }
    public ArrayList getPetIDByStatus(String status) {
        RequestSpecification request= RestAssured.given();
        String path="/pet/findByStatus";
        request.baseUri(baseUri).basePath(path);
        Response response=request.queryParam("status", status).get();
        return JsonPath.from(( response).asString()).get("id");

    }
}

