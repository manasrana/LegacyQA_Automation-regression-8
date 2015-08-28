package pages;

import framework.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import framework.ControlLocators;
import framework.PageBase;
import framework.Utilities;

import java.util.ArrayList;

public class VerizonShopPlansPage {

    public VerizonShopPlansPage(WebDriver d) {
        PageFactory.initElements(d, this);
    }

    // region- Verizon Shop Plans Elements.
    @FindBy(xpath = ControlLocators.KEEP_MY_EXISTING_VERIZON_WIRELESS_LEGACY_ADD_BUTTON)
    public WebElement KeepMyExistingVerizonWirelessLegacyAddButton;

    @FindBy(xpath = ControlLocators.VERIZON_MORE_EVERYTHING_UNLIMITED_MINUTES_AND_MESSAGING)
    public WebElement Verizon_More_Everything_Unlimited_Minutes_And_Messaging;

    @FindBy(xpath = ControlLocators.VERIZON_MORE_EVERYTHING_UNLIMITED_MINUTES_AND_MESSAGING_500MB_DATA_ADD_BUTTON)
    public WebElement verizonMoreEverythingUnlimitedMinutesAndMessaging500MBDataAddButton;

    @FindBy(xpath = ControlLocators.VERIZON_MORE_EVERYTHING)
    public WebElement VerizonMoreEverything;

    @FindBy(xpath = ControlLocators.VERIZON_MORE_PLAN_ONLY)
    public WebElement verizonMorePlanOnly;

    @FindBy(xpath = ControlLocators.SPRINT_FAMILY_SHARE_PLAN)
    public WebElement sprintFamilySharePlan;

    @FindBy(xpath = ControlLocators.ATT_UNLIMITED_PLAN)
    public WebElement attUnlimitedPlan;

    @FindBy(xpath = ControlLocators.ADD_PLAN)
    public WebElement addPlan;

    @FindBy(xpath = ControlLocators.KEEP_MY_EXISTING_SPRINT_PLAN)
    public WebElement KeepMyExistingSprintPlan;

    @FindBy(xpath = ControlLocators.SPRINT_MORE_EVERYTHING)
    public WebElement sprintMoreEveryThing;

    @FindBy(xpath = ControlLocators.SPRINT_FIRST_PLAN)
    public WebElement sprintFirstPlan;

    @FindBy(xpath = ControlLocators.FIRST_PLAN_ADD_BUTTON)
    public WebElement firstPlanAddButton;

    @FindBy(xpath = ControlLocators.VERIZON_ANY_PLAN)
    public WebElement verizonAnyPlan;

    @FindBy(xpath = ControlLocators.VERIZON_PLAN_NAME)
    public WebElement planName;


    // endregion

    // region -Methods
    public String selectPlanWithMore() {
        String orderDescription = PageBase.OrderReviewAndConfirmPage().planDescriptionText
                .getText();
        PageBase.VerizonShopPlansPage().verizonMorePlanOnly.click();
        return orderDescription;
    }

    public void addPlan() {
        Utilities
                .waitForElementVisible(PageBase.VerizonShopPlansPage().addPlan);
        PageBase.VerizonShopPlansPage().addPlan.click();
    }

    public void selectAnyPlan() {
        PageBase.VerizonShopPlansPage().verizonAnyPlan.click();
    }

    public void selectAnyATTPlan() {
        Utilities.implicitWaitSleep(9000);
        ArrayList<WebElement> list = (ArrayList<WebElement>) BrowserSettings.driver.findElements(By.xpath("//h1[contains(text(),'AT&T')]"));
        if (list.size() > 0) {
            Utilities.waitForElementVisible(list.get(0));
            list.get(0).click();
            PageBase.VerizonShopPlansPage().addPlan();
        }
        // endregion
    }

    public void selectingVerizonPlan()
    {
        Utilities.waitForElementVisible(BrowserSettings.driver.findElement(By.xpath("//a[contains(text(),'The Verizon Plan')]")));
        BrowserSettings.driver.findElements(By.xpath("//div[contains(text(),'select plan')]")).get(0).click();
    }

    public void selectingVerizonKeepMyExistingPlan()
    {
        Utilities.waitForElementVisible(BrowserSettings.driver.findElement(By.xpath("//a[contains(text(),'The Verizon Plan')]")));
        BrowserSettings.driver.findElements(By.xpath("//div[contains(text(),'select plan')]")).get(0).click();
    }
}