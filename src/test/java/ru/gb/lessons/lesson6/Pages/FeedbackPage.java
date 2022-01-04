package ru.gb.lessons.lesson6.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class FeedbackPage extends BasicElementsPage{

    @FindBy(xpath = "//h1[@class='page-heading bottom-indent']")
    private WebElement pageHeading;

    @FindBy(id = "id_contact")
    private WebElement contactId;

    @FindBy(id = "uniform-id_contact")
    private WebElement contactId2;

    @FindBy(xpath = "//select[@id='id_contact']/option[@value='1']")
    private WebElement contactIdValue1;

    @FindBy(xpath = "//textarea[@id='message']")
    private WebElement feedbackInput;

    @FindBy(id = "submitMessage")
    private WebElement submitBtn;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement accertion;

    public FeedbackPage(WebDriver webDriver, Actions actions) {
        super(webDriver, actions);
    }

    public FeedbackPage(WebDriver webDriver) {
        super(webDriver);
    }

    public FeedbackPage() {
    }

    @Step("Убедиться что открылась страница с формой обратной связи")
    public FeedbackPage checkProceedSuccess(){
        assertThat(pageHeading.getText()).as("Не удалось перейти к форме обратной связи").contains("CUSTOMER SERVICE - CONTACT US");
        return this;
    }

    @Step("Выбрать адрессата в поле Subject Heading")
    public FeedbackPage setIdContact(){
        contactId.click();
        contactIdValue1.click();
        return this;
    }

    @Step("Убедиться что в поле Subject Heading адрессат выбран верно")
    public FeedbackPage checkContactIdValue(){
        assertThat(contactId2.getText()).as("Не удалось выбрать адрессата").contains("Webmaster");
        return this;
    }

    @Step("В поле Message написать текст: {0}")
    public FeedbackPage writeFeedbackText(String feedbackText) {
        feedbackInput.sendKeys(feedbackText);
        return this;
    }

    @Step("Убедиться что в поле Message введен текст: {0}")
    public FeedbackPage checkFeedbackText(String feedbackText){
        assertThat(feedbackInput.getAttribute("value")).as("Текст введен не верно").isEqualTo(feedbackText);
        return this;
    }

    @Step("Нажать кнопку Send")
    public FeedbackPage sendFeedback() {
        submitBtn.click();
        return this;
    }

    @Step("Убедиться что появилось сообщение с подтверждением успешного отправления обратной связи")
    public FeedbackPage checkIfFeedbackSent() {
        assertThat(accertion.getText()).as("Не удалось отправить сообщение").isEqualTo("Your message has been successfully sent to our team.");
        return this;
    }
}
