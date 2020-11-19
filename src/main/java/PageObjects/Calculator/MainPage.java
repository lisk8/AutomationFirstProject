package PageObjects.Calculator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage
{
    @FindBy(name = "Zero")
    private WebElement btn_zero;

    @FindBy(name = "One")
    private WebElement btn_one;

    @FindBy(name = "Two")
    private WebElement btn_two;

    @FindBy(name = "Three")
    private WebElement btn_three;

    @FindBy(name = "Four")
    private WebElement btn_four;

    @FindBy(name = "Five")
    private WebElement btn_five;

    @FindBy(name = "Six")
    private WebElement btn_six;

    @FindBy(name = "Seven")
    private WebElement btn_seven;

    @FindBy(name = "Eight")
    private WebElement btn_eight;

    @FindBy(name = "Nine")
    private WebElement btn_nine;

    @FindBy(xpath = "//*[@Name='Clear entry' or @Name='Clear']")
    public WebElement btn_clear;

    @FindBy(name = "Plus")
    public WebElement btn_plus;

    @FindBy(name = "Equals")
    public WebElement btn_equals;

    @FindBy(xpath = "//*[@AutomationId='CalculatorResults']")
    public WebElement txt_result;

    /**
     * @return Gets an array of all the numbers buttons
     */
    public WebElement[] GetNumButtons()
    {
        return new WebElement[]{btn_zero,btn_one,btn_two,btn_three,btn_four,btn_five,btn_six,btn_seven,btn_eight,btn_nine};
    }
}
