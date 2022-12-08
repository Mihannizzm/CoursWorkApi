package api;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class AccessControlApi {
    private static final String baseUri = "http://localhost:8080";

    public static Response checkAccess(String entrance, Integer keyId, Integer roomId) {
        switch (entrance) {
            case ("Вход"):
                entrance = "ENTRANCE";
                break;
            case ("Выход"):
                entrance = "EXIT";
                break;
        }
        String path = "/check";
        Map<String, String> params = new HashMap<>();
        params.put("entrance", entrance);
        params.put("keyId", keyId.toString());
        params.put("roomId", roomId.toString());

        return RestRequest.getRequest(baseUri, path, params);
    }

    public static Response roomInfo() {
        String path = "/info/rooms";
        Map<String, String> params = new HashMap<>();
        return RestRequest.getRequest(baseUri, path, params);
    }

    public static Response usersInfo(Integer start, Integer end) {
        String path = "/info/users";
        Map<String, String> params = new HashMap<>();
        params.put("end", end.toString());
        params.put("start", start.toString());
        return RestRequest.getRequest(baseUri, path, params);
    }
}