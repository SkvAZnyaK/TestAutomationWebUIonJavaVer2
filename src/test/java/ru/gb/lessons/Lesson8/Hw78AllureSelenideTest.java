package ru.gb.lessons.Lesson8;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.gb.lessons.Lesson8.MyExtention.ConfigUiExtention;
import ru.gb.lessons.Lesson8.Pages.BasicElementsPage;
import ru.gb.lessons.Lesson8.Pages.UiAuthorizationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static ru.gb.lessons.Lesson8.Pages.Blocks.HeaderFirstLevelMenu.WOMEN;
import static ru.gb.lessons.Lesson8.Pages.Blocks.HeaderSecondLevelMenu.CASUALDRESSES;
import static ru.gb.lessons.Lesson8.Pages.Blocks.HeaderSecondLevelMenu.SUMMERDRESSES;

@ExtendWith(ConfigUiExtention.class)
@DisplayName("Тестирование интернет-магазина женской одежды")
public class Hw78AllureSelenideTest extends BasicElementsPage {

    private final String email = "zaraz4@yandex.ru";
    private final String pswd = "Cthutq62!";
    private final String name = "Sergey";
    private final String sureName = "Semizarov";
    private final String someCommentsToProceed = "Very important blah-blah...";
    private final String feedbackText = "Thank you very much! for such a beautiful source to practice!";


    @Test
    @Description("Этот тест проходит на страницу авторизации, вводит логин и пароль в соответствующие поля, и нажимает кнопку авторизоваться")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тестирование авторизации через логин и пароль" )
    void UIAuthorizationTest() throws InterruptedException {
        open("http://automationpractice.com/index.php");
        page(UiAuthorizationPage.class)
                .enterLogin(email)
                .checkLoginIsCorrect()
                .enterPassword(pswd)
                .checkPasswordIsCorrect()
                .clickLoginButton()
                .checkLoginIsSucceed();
    }

    @Test
    @Description("Этот тест заходит в раздел персональных данных личного кабинета, изменяет поля с личными данными, вводит актуальный пароль и нажимает кнопку сохранить")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тестирование изменения личных данных")
    void PersonalDataChangeTest() {
        open("http://automationpractice.com/index.php");
        page(UiAuthorizationPage.class).enterLogin(email).enterPassword(pswd).clickLoginButton()
                .clickPersonalData()
                .checkIfPersonalDataPageIsAvailable()
                .setGender()
                .setFirstname(name)
                .checkFirstname(name)
                .setLastname(sureName)
                .checkLastname(sureName)
                .setBirthdayDate()
                .checkBirthdayDate()
                .enterOldPassword(pswd)
                .checkOldPassword(pswd)
                .clickSaveButton();

    }

    @Test
    @Description("Этот тест проходит на страницу с товарами, поочередно нажимает кнопку сравнить у двух первых товаров из списка, проходит на страницу сравнения, удаляет выбранные товары из сравнения и закрывает страницу сравнения")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Тестирование сравнения товаров")
    void CompareListTest() throws InterruptedException {
        open("http://automationpractice.com/index.php");
        page(UiAuthorizationPage.class).enterLogin(email).enterPassword(pswd).clickLoginButton()
                .goToHeaderBlock()
                .goToProductsPage (WOMEN, SUMMERDRESSES)
                .addToCompareList(0,1)
                .clickCompareButton()
                .checkProceedToCompareListSuccess()
                .cleanAndCloseCompareList()
                .checkIfCompareListClosed();
    }

    @Test
    @Description("Этот тест проходит на страницу с товарами, нажимает на кнопку добавить в корзину у первого товара из списка, закрывает уведомление, открывает корзину и заказывает товар")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тестирование добавления товара в корзину, оформления и оплаты заказа")
    void AddToCartTest() throws InterruptedException {
        open("http://automationpractice.com/index.php");
        page(UiAuthorizationPage.class).enterLogin(email).enterPassword(pswd).clickLoginButton()
                .goToHeaderBlock()
                .goToProductsPage (WOMEN, CASUALDRESSES)
                .addToCart(0)
                .closeProductWindow()
                .checkIfProductWindowIsClosed()
                .checkIfProductIsInCart()
                .goToShoppingCart()
                .checkProceedToCart()
                .clickProceedToCheckout()
                .checkProceedToAddress()
                .clickMyAddress()
                .typeSomeComments(someCommentsToProceed)
                .clickProceed()
                .checkProceedToShipping()
                .clickAgree()
                .clickProceed()
                .checkProceedToPaymentMethod()
                .clickPayByBankWire()
                .checkProceedToOrderSummary()
                .clickConfirm()
                .checkConfirmation();
    }

    @Test
    @Description("Этот тест проходит на страницу обратной связи, выбирает адрессата, пишет текст в соответствующем поле и нажимает кнопку отправить")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Тестирование обратной связи")
    void SendFeedbackTest() {
        open("http://automationpractice.com/index.php");
        page(UiAuthorizationPage.class).enterLogin(email).enterPassword(pswd).clickLoginButton()
                .clickContactUs()
                .checkProceedSuccess()
                .setIdContact()
                .checkContactIdValue()
                .writeFeedbackText(feedbackText)
                .checkFeedbackText(feedbackText)
                .sendFeedback()
                .checkIfFeedbackSent();
    }
}