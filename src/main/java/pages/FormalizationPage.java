package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import steps.BaseSteps;

import static org.junit.Assert.*;

public class FormalizationPage {

    MainPage mainPage = new MainPage();

    @FindBy(name = "insured0_surname")
    WebElement insuredSurname;

    @FindBy(name = "insured0_name")
    WebElement insuredName;

    @FindBy(name = "insured0_birthDate")
    WebElement insuredBirthDate;

    @FindBy(name = "surname")
    WebElement surname;

    @FindBy(name = "name")
    WebElement name;

    @FindBy(name = "middlename")
    WebElement middlename;

    @FindBy(name = "birthDate")
    WebElement birthDate;

    @FindBy(xpath = "//fieldset[@class='b-form-fieldset-splash b-form-margtop-fieldset']")
    WebElement chSex;

    @FindBy(name = "passport_series")
    WebElement passportSeries;

    @FindBy(name = "passport_number")
    WebElement passportNumber;

    @FindBy(name = "issueDate")
    WebElement issueDate;

    @FindBy(name = "issuePlace")
    WebElement issuePlace;

    @FindBy(xpath = "//span[text()='Продолжить']")
    WebElement continueButton;

    @FindBy(xpath = "//div[text()='Заполнены не все обязательные поля']")
    WebElement errorMessage;

    public FormalizationPage() {
        PageFactory.initElements(BaseSteps.getDriver(), this);
    }

    public void fillField(String fieldName, String value) {

        switch (fieldName) {
            case "Фамилия_застрах":
                fillField(insuredSurname, value);
                break;
            case "Имя_застрах":
                fillField(insuredName, value);
                break;
            case "ДР_застрах":
                fillField(insuredBirthDate, value);
                break;
            case "Фамилия":
                fillField(surname, value);
                break;
            case "Имя":
                fillField(name, value);
                break;
            case "Отчетство":
                fillField(middlename, value);
                break;
            case "ДР":
                fillField(birthDate, value);
                break;
            case "Серия пасспорта":
                fillField(passportSeries, value);
                break;
            case "Номер пасспорта":
                fillField(passportNumber, value);
                break;
            case "Дата выдачи пасспорта":
                fillField(issueDate, value);
                break;
            case "Место выдачи пасспорта":
                fillField(issuePlace, value);
                break;
            default: throw new AssertionError("Поле '" + fieldName + "' на странице отсутствует");
        }
    }

    public String getFillField(String fieldName){
        switch (fieldName){
            case "Фамилия_застрах":
                return insuredSurname.getAttribute("value");
            case "Имя_застрах":
                return insuredName.getAttribute("value");
            case "ДР_застрах":
                return insuredBirthDate.getAttribute("value");
            case "Фамилия":
                return surname.getAttribute("value");
            case  "Имя":
                return name.getAttribute("value");
            case "Отчетство":
                return middlename.getAttribute("value");
            case "ДР":
                return birthDate.getAttribute("value");
            case "Серия пасспорта":
                return passportSeries.getAttribute("value");
            case "Номер пасспорта":
                return passportNumber.getAttribute("value");
            case "Дата выдачи пасспорта":
                return issueDate.getAttribute("value");
            case "Место выдачи пасспорта":
                return issuePlace.getAttribute("value");
        }

        throw new AssertionError("Данное поле ('" + fieldName + "') отсутствует на странице!");
    }

    public void chooseSex(String sex) {
        if(sex.contains("мужской"))
            chSex.findElement(By.xpath(".//input[@name='male']")).click();
        else
            chSex.findElement(By.xpath(".//input[@name='female']")).click();
    }

    public void pushContinueButton() {
        continueButton.click();
    }

    public void errorMessageVisible() {
        assertTrue("Не найдено сообщение об ошибке", mainPage.isElementPresent(BaseSteps.getDriver(), errorMessage));
    }

    void fillField(WebElement el, String value) {
        el.clear();
        el.sendKeys(value);
    }

    public void checkFieldErrorMessage(String errorMessage){
        String actualValue = BaseSteps.getDriver().findElement(By.xpath("//div[@ng-show='tryNext && myForm.$invalid']")).getText();
        assertTrue(String.format("Получено значение [%s]. Ожидалось [%s]", actualValue, errorMessage), actualValue.contains(errorMessage));
    }

    public String getFieldErrorMessage(String field) {
        String xpath = "//div[text()='" + field + "']";
        return BaseSteps.getDriver().findElement(By.xpath(xpath)).getText();
    }
}
