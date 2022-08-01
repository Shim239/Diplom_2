import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserClient extends RestAssuredClient {

    private static final String USER_PATH = "/api/auth/";
    public String accessToken = "";

    @Step("Создать пользователя и зарегистрироваться")
    public Response create(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "register");
    }

    @Step("Авторизация пользователя в системе")
    public Response login(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_PATH + "login");
    }


    @Step("Получение информации о пользователе")
    public ValidatableResponse getDataUser(String accessToken) {

        return given()
                .header("Authorization", accessToken)
                .spec(getBaseSpec())
                .when()
                .get(USER_PATH + "user")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true));
    }

    @Step("Обновление информации о пользователе с токеном")
    public Response setDataUserWithToken(String accessToken, User user) {

        return given()
                .header("Authorization", accessToken)
                .spec(getBaseSpec())
                .when()
                .body(user)
                .patch(USER_PATH + "user");
    }

    @Step("Обновление информации о пользователе без токена")
    public Response setDataUserWithoutToken(User user) {

        return given()
                .spec(getBaseSpec())
                .when()
                .body(user)
                .patch(USER_PATH + "user");
    }

    @Step("delete user")
    public Response deleteUser() {
        if (this.accessToken.equals("")) {
            return given()
                    .spec(getBaseSpec())
                    .auth().oauth2(accessToken)
                    .delete("/api/auth/user");
        }
        return null;

    }
}
