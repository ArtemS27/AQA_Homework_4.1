package ru.netology.carddelivery;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.carddelivery.data.DataGenerator;

import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup(){
        open("http://localhost:9999");
    }

    @Test
    public void shouldSendForm() {
        int daysToAdd = 4;
        int daysToAddToChange = 8;
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue(DataGenerator.Registration.generateUser("ru").getCity());
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(DataGenerator.generateDate(daysToAdd));
        form.$("[data-test-id=name] input").setValue(DataGenerator.Registration.generateUser("ru").getName());
        form.$("[data-test-id=phone] input").setValue(DataGenerator.Registration.generateUser("ru").getPhone());
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=success-notification]").shouldBe(Condition.visible);
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + DataGenerator.generateDate(daysToAdd)));
        $("[data-test-id=success-notification] .icon-button").click();
        form.$("[data-test-id=date] input").doubleClick();
        form.$("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(DataGenerator.generateDate(daysToAddToChange));
        form.$(".button").click();
        $("[data-test-id=replan-notification] .button").click();
        $("[data-test-id=success-notification] .notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + DataGenerator.generateDate(daysToAddToChange)));
    }
}
