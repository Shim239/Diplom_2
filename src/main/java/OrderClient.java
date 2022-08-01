import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class OrderClient extends RestAssuredClient {
    private static final String USER_PATH = "/api/orders/";

    @Step("Создать заказ без авторизации")
    public Response createOderWithoutAuthorization(Order oder) {
        return given()
                .spec(getBaseSpec())
                .when()
                .body(oder)
                .post(USER_PATH);
    }

    @Step("Создать заказ с авторизацией")
    public Response createOderWithAuthorization(String accessToken, Order oder) {
        return given()
                .header("Authorization", accessToken)
                .spec(getBaseSpec())
                .when()
                .body(oder)
                .post(USER_PATH);
    }

    @Step("Получение заказа конкретного пользователя c авторизацией")
    public Response getOderUserWithAuthorization(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .spec(getBaseSpec())
                .get(USER_PATH);
    }

    @Step("Получение заказа конкретного пользователя без авторизации")
    public Response getOderUserWithoutAuthorization() {
        return given()
                .spec(getBaseSpec())
                .get(USER_PATH);
    }
}
