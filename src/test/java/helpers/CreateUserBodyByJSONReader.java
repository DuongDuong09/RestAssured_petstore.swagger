package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.User_Body;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateUserBodyByJSONReader {

    public static User_Body getUserInforByid(int idInput) throws IOException {
        User_Body user_body = new User_Body();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        List<User_Body> userBodyList = objectMapper.readValue(Paths.get("src/test/resources/UserBody.json").toFile(),
                new TypeReference<List<User_Body>>() {
                });
        for (User_Body emp : userBodyList) {
            if (emp.getId()==(idInput)) {
                user_body = new User_Body(idInput, emp.getUsername(), emp.getFirstName(), emp.getLastName(), emp.getEmail(),
                        emp.getPassword(), emp.getPhone(), emp.getUserStatus());
                break;
            }
        }
        return user_body;
    }
    public Map userDataAdded(int idInput) throws IOException {
        Map<String, Object> use = new HashMap<String, Object>();
        use.put("id",  getUserInforByid(idInput).getId());
        use.put("username",  getUserInforByid(idInput).getUsername());
        use.put("firstName",  getUserInforByid(idInput).getFirstName());
        use.put("lastName",  getUserInforByid(idInput).getLastName());
        use.put("email",  getUserInforByid(idInput).getEmail());
        use.put("password",  getUserInforByid(idInput).getPassword());
        use.put("phone", getUserInforByid(idInput).getPhone());
        use.put("userStatus",  getUserInforByid(idInput).getUserStatus());
        return use;
    }

    public JSONObject UserDataAsString(int idInput) throws IOException {
        JSONObject requestPayload = new JSONObject(userDataAdded(idInput));
        return requestPayload;
    }
    public String UserDataAsArrayList(int idInput) throws IOException {
        JSONObject requestPayload = new JSONObject(userDataAdded(idInput));
        List<Map<String, Object>> jsonArrayPayload = new ArrayList<>();
        jsonArrayPayload.add(requestPayload);
        Gson gson = new Gson();
        String listOfUserPayload = gson.toJson(jsonArrayPayload);
        return listOfUserPayload;
    }
}
