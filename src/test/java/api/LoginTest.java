package api;

import reqest.config.LoginHttpRequest;
import reqest.dto.request.Login;
import reqest.dto.response.LoginResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class LoginTest {
    private static LoginResponseDto loginResponseDto;
    private static LoginHttpRequest loginHttpRequest;
    private static String testEmail;
    private static String testPassword;

    @BeforeAll
    public static void setUp() {
        loginResponseDto = new LoginResponseDto();
        loginHttpRequest = new LoginHttpRequest();
        testEmail = "eve.holt@reqres.in";
        testPassword =  "pistol";
    }

    @Test
    public void successfulLogin() {
        Login client = new Login(testEmail, testPassword);

        loginResponseDto = loginHttpRequest.login(client)
                .then()
                .statusCode(200)
                .extract().as(LoginResponseDto.class);
        String actualToken = loginResponseDto.getToken();
        Assertions.assertTrue(!actualToken.isEmpty());
    }

    @Test
    public void unsuccessfulLogin() {
        int statusCode = loginHttpRequest.login(new Login(testEmail, ""))
                .then()
                .statusCode(400)
                .extract().statusCode();
        Assertions.assertEquals(400, statusCode);
    }
}
