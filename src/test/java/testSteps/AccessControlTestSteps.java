package testSteps;

import api.AccessControlApi;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class AccessControlTestSteps {
    private static Response response;

    @Given("^Отправляю запрос. Действие: '(.+)', номер ключа: (\\d+), номер комнаты (\\d+)$")
    public void getAccess(String entrance, int keyId, int roomId) {
        response = AccessControlApi.checkAccess(entrance, keyId, roomId);
        System.out.println("Отправил запрос проверки возможности: " + entrance + ", для пользователя: "
                + keyId + ", номер комнаты: " + roomId);
    }

    @Then("^Проверяю, что статус код (\\d+)$")
    public void checkStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
        System.out.println("Статус код: " + statusCode);
    }

    @Then("^Проверяю, что статус код: (\\d+), ответ: '(.+)'$")
    public void checkResponseBody(int statusCode, String text) {
        checkStatusCode(statusCode);
        Assert.assertEquals(text, response.asString());
        System.out.println("Ответ на запрос соотвествует ожидаемому результату");
    }

    @When("^Отправляю запрос на получение информации по всем комнатам$")
    public void roomsInfo() {
        response = AccessControlApi.roomInfo();
        System.out.println("Отправил запрос на получение информации по всем комнатам");
    }

    @When("^Отправляю запрос на получение информации о пользователях, начало отсчета: (\\d+), конец отсчета: (\\d+)$")
    public void usersInfo(int start, int end) {
        response = AccessControlApi.usersInfo(start, end);
        System.out.println("Отправляю запрос на получение информации о пользователях, начало отсчета: " + start
                + ", конец отсчета: " + end);
    }

    @Then("^Проверяю колличество объектов в ответе: (\\d+)$")
    public void checkObjectCount(int objectCount) {
        Assert.assertEquals(objectCount, response
                .jsonPath()
                .getList("")
                .size());
        System.out.println("Колличество объектов в ответе: " + objectCount);
    }

    @Then("^Проверяю, что статус код: (\\d+), в ответе присутствует '(.+)'$")
    public void checkTextForResponse(int statusCode, String text) {
        checkStatusCode(statusCode);
        Assert.assertTrue(response.asString().contains(text));
        System.out.println("В ответе присутствует текст: " + text);
    }
}