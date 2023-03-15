package com.jsd.qa_guru_examples.junit_examples;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SimpleWebTest {

    @BeforeEach
    void setup() {
        Selenide.open("https://ya.ru");
    }

/*    static Stream<Arguments> searchResultsShouldBeGreaterThan10() {
        return Stream.of(
                Arguments.of("Selenide"),
                Arguments.of("Allure")
        );
    }

    @MethodSource*/
    @ValueSource(strings = {
            "Selenide", "Allure"
    })
    @ParameterizedTest(name = "В поисковой выдаче яндкса должно отображаться 10 результатов по запросу {0}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void searchResultsShouldBeGreaterThan10(String testData) {
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldHave(sizeGreaterThanOrEqual(10));
    }

    @CsvSource(value = {
            "Selenide,                     лаконичные и стабильные UI тесты на Java",
            "Allure framework,             Allure Framework · GitHub"
    })
//    @CsvFileSource(resources = "/testdata/firstSearchResultsShouldContainExpectedText.csv")
    @ParameterizedTest(name = "В первом результате выдачи для {0} должен отображаться текст {1}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void firstSearchResultsShouldContainExpectedText(String testData, String expectedText) {
        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").first().shouldHave(Condition.text(expectedText));
    }

    @Disabled
    @Test
    void photoSearchTest() {
        $(".search3_icon-camera svg").click();
        // etc
    }
}
