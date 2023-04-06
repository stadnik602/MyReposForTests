package reqest;

import io.restassured.response.Response;

public class LoginHttpRequest extends BaseHttpRequest {
    private static final String LOGIN_URL = "/api/login";

    public Response login(Login client) {
        return getRequestSpecification().body(client).when().post(LOGIN_URL);
    }
}
