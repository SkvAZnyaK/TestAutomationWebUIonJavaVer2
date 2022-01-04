package ru.gb.lessons.lesson6.Listener;

import io.qameta.allure.Allure;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static io.qameta.allure.Allure.step;

public class ActionEventListener extends AbstractWebDriverEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ActionEventListener.class);

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        String currentStep = "Нажать на элемент с текстом " + element.getText();
        step(currentStep);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        String currentStep = "Нажатие на элемент прошло успешно!";
        step(currentStep);
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String currentStep = "Ввести текст " + Arrays.toString(keysToSend) + " в поле с ID=" + element.getAttribute("id");
        step(currentStep);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String currentStep = "Введение текста прошло успешно!";
        step(currentStep);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }
}
