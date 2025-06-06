import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class SoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1600x900";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    
    void softAssertionsTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();

        //Убедиться, что в списке страниц (Pages) есть страница SoftAssertions
        $$("ul").findBy(text("Soft assertions")).shouldBe(visible);
        //кликнуть по ней
        $$("ul a").findBy(text("Soft assertions")).click();
        //$(".markdown-body").$(withText("JUnit5")).shouldBe(visible);
        //проверить что внутри есть пример кода для JUnit5
        String expectedCode = """
                 Using JUnit5 extend test class:
                 @ExtendWith({SoftAssertsExtension.class})
                 class Tests {
                   @Test
                   void test() {
                     Configuration.assertionMode = SOFT;
                     open("page.html");
                
                     $("#first").should(visible).click();
                     $("#second").should(visible).click();
                   }
                 }
                """;
        $("div.markdown-body").shouldHave(text(expectedCode));
    }

    //тест на закрытие попапа
        @Test
        void closeCookiePopup() {
        open("https://www.otpbank.ru/");
        //проверка что это окно изначально есть
        $(withText("Продолжая просмотр сайта, вы даете согласие на обработку")).shouldBe(visible);
        // по тегу и тексту для уверенности
        $(byTagAndText("strong", "Закрыть" )).click();
        //проверка что оно закрылось
        $(withText("Продолжая просмотр сайта, вы даете согласие на обработку")).shouldBe(hidden);

        }


    }