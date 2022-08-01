import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;


public class IngredientClient extends RestAssuredClient {
    private static final String USER_PATH = "api/ingredients/";

    @Step("Получение данных об ингредиентах")
    public Ingredient getIngredient() {
        return given()
                .spec(getBaseSpec())
                .get(USER_PATH)
                .as(Ingredient.class);
    }
}
