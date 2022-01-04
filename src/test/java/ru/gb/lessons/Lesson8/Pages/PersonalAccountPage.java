package ru.gb.lessons.Lesson8.Pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.gb.lessons.Lesson8.Pages.Blocks.HeaderBlock;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonalAccountPage extends BasicElementsPage {

    private SelenideElement personalDataBtn = $x("//a[@title='Information']");
    private SelenideElement contactUsRef = $x("//a[text()='Contact us']");
    private SelenideElement accountInfoText = $x("//*[@class = 'info-account']");

    @Step("Убедиться что авторизация прошла успешно, и открылся личный кабинет пользователя")
    public PersonalAccountPage checkLoginIsSucceed (){
//        assertThat($x("//*[@class = 'info-account']")
//                .getText()).as("Не удалось войти в личный кабинет")
//                .isEqualTo("Welcome to your account. Here you can manage all of your personal information and orders.");
        accountInfoText.shouldHave(exactText("Welcome to your account. Here you can manage all of your personal information and orders."));
        return this;
    }

    @Step("Нажать на кнопку Personal Data")
    public PersonalDataPage clickPersonalData() {
        personalDataBtn.click();
        return page(PersonalDataPage.class);
    }

    @Step("Нажать на кнопку Contact Us")
    public FeedbackPage clickContactUs(){
        contactUsRef.click();
        return page(FeedbackPage.class);
    }

    HeaderBlock headerBlock = new HeaderBlock();

    public HeaderBlock goToHeaderBlock() {
        return headerBlock;
    }
}
