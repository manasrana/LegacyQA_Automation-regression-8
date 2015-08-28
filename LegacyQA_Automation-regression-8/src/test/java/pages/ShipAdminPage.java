package pages;

import java.awt.AWTException;
import java.io.IOException;
import java.util.List;

import framework.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class ShipAdminPage {
	public ShipAdminPage(WebDriver d) {
		PageFactory.initElements(d, this);
	}

	@FindBy(xpath = ControlLocators.ORDER_TEXTBOX)
	public WebElement orderTextbox;

	@FindBy(xpath = ControlLocators.MANUAL_REVIEW_STATUS)
	public WebElement manualReviewStatusOnShipAdmin;

	@FindBy(xpath = ControlLocators.CREDIT_CHECK_RESULT_SUMMARY_TABLE)
	public WebElement creditCheckResultSummaryTable;

	@FindBy(xpath = ControlLocators.UPDATE_CREDIT_CHECK)
	public WebElement updateCreditCheck;

	@FindBy(xpath = ControlLocators.CREDIT_CHECK_RESULT_UPDATE_DROPDOWN)
	public WebElement updateCreditCheckResult;

	@FindBy(xpath = ControlLocators.CREDIT_CHECK_RESULT_RESOLUTION_UPDATE_DROPDOWN)
	public WebElement updateCreditCheckResultReason;

	@FindBy(xpath = ControlLocators.NUMBER_OF_LINES_TO_UPDATE)
	public WebElement numberOfLinesToUpdate;

	@FindBy(xpath = ControlLocators.ADD_UPDATE_CREDITCHECK_BUTTON)
	public WebElement addUpdateCreditCheck;

	@FindBy(xpath = ControlLocators.PARTNER_TEXT)
	public WebElement partnerText;

	@FindBy(xpath = ControlLocators.ORDER_LINE_CREDIT_DROPDOWN)
	public WebElement orderLineCredit;

	@FindBy(xpath = ControlLocators.MOVE_QUEUES_DROPDOWN)
	public WebElement moveQueuesdropdown;

	@FindBy(xpath = ControlLocators.MOVE_QUEUES)
	public WebElement moveQueues;

	@FindBy(xpath = ControlLocators.MOVE_QUEUES_BUTTON)
	public WebElement moveQueuesButton;

	@FindBy(xpath = ControlLocators.ORDER_SUB_STATUS)
	public WebElement orderSubStatus;

	@FindBy(xpath = ControlLocators.DEACTIVATE_BUTTON)
	public WebElement deactivateButton;

	@FindBy(xpath = ControlLocators.BRAND_NAME)
	public WebElement brandName;

	@FindBy(xpath = ControlLocators.ORDERS)
	public WebElement orders;

	@FindBy(xpath = ControlLocators.SEARCHORDER_BUTTON)
	public WebElement searchOrderButton;

	@FindBy(xpath = ControlLocators.EVENTTRACKER_LINK)
	public WebElement eventTrackerLink;

	@FindBy(xpath = ControlLocators.EVENTTRACKER_ORDER_TEXTBOX)
	public WebElement eventTrackerOrderTextbox;

	@FindBy(xpath = ControlLocators.EVENTTRACKER_ORDERID_LINK)
	public WebElement eventTrackerOrderIdLink;

	@FindBy(xpath = ControlLocators.ORDERID_LINK)
	public WebElement orderIdLink;

	@FindBy(id = ControlLocators.LASTWEEK_RADIOBUTTON)
	public WebElement lastWeekRadiobutton;

	@FindBy(xpath = ControlLocators.EVENTS_LIST)
	public List<WebElement> eventList;

	public void deactivateLines(List<String> phoneNums) {
		Utilities.implicitWaitSleep(5000);
		int size = phoneNums.size();
		for (int i = 0; i < size; i++) {
			String Phone = String
					.format(ControlLocators.DEACTIVATE_PHONE_CHECKBOX,
							phoneNums.get(i));
			BrowserSettings.driver.findElement(By.xpath(Phone)).click();
		}
		deactivateButton.click();
	}

	public void deactivateLines(String OrderID, int size) {
		Utilities.implicitWaitSleep(5000);
		int j=1;
		for (int i = 0; i < size; i++)
		{
			Utilities.implicitWaitSleep(1000);
			String order = String.format(ControlLocators.DEACTIVATE_PHONE_CHECKBOX,OrderID,j);
			System.out.print(order);
			BrowserSettings.driver.findElement(By.xpath(order)).click();
			j=j+1;
		}
		deactivateButton.click();
	}

	public void ShipAdminEventLog(WebDriver driver, String orderId) throws IOException {
		ShipAdminBaseClass.launchShipAdminInNewTab();
		PageBase.OrderSummaryPage().goToOrderSummaryPage(orderId);

		int i = 1;

		Reporter.log("<caption><b>Event Logs in Shipadmin</b></caption>");
		Reporter.log("<table border='1' style='width:100%'>");
		for (WebElement singleEvent : eventList) {
			if(i < eventList.size()) {
				Reporter.log("<tr>");
				if (singleEvent.getAttribute("class").toString().contains("h1")) {
					Reporter.log("<td>"+singleEvent.findElement(By.xpath("./child::th[4]")).getText()+"</td>");
					Reporter.log("<td>"+singleEvent.findElement(By.xpath("./child::th[5]")).getText()+"</td>");
				} else if (singleEvent.getAttribute("class").toString().contains("")) {
					Reporter.log("<td>"+singleEvent.findElement(By.xpath("./child::td[4]/font")).getText()+"</td>");
					Reporter.log("<td>"+singleEvent.findElement(By.xpath("./child::td[5]/font")).getText()+"</td>");
				} else
					continue;
			}
			i++;
		}
		Reporter.log("</table>");
	}

}