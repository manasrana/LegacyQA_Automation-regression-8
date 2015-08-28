package att.tests;

import framework.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CarrierCreditCheckDetails;
import pages.PaymentRequiredPage;
import pages.ServiceProviderVerificationPage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;

public class AddNewLineTests extends RetailBaseClass {

    //region QA 595
    @Test(groups = {"att"})
    @Parameters("test-type")
    public void QA_595_ATT_NewActivationWith18MonthContractAppleCareNoDepositNoNumPort(@Optional String testType) throws IOException, AWTException, InterruptedException {
        String orderId = "";
        IMEIDetails imeiDetails = PageBase.CSVOperations().GetIMEIAndProductName(CSVOperations.FileName.ATT_IPhoneDevice);

        // Adding Devices to Inventory.
//        PageBase.InventoryManagementPage().launchInventoryInNewTab(BrowserSettings.readConfig("inventoryStoreIdDetail"));
//        PageBase.InventoryManagementPage().addDeviceToInventory(imeiDetails.IMEI, imeiDetails.ProductName);
//        PageBase.InventoryManagementPage().closeInventoryTab();
//        Utilities.switchPreviousTab();

        Log.startTestCase("QA_595_ATT_NewActivationWith18MonthContractNoDepositNoNumPort");
        testType = BrowserSettings.readConfig("test-type");

        // Verify whether which enviorement to use internal or external.
        //selectingCarrierEnviornment_595(testType);

        // Switching to previous tab.
       // Utilities.switchPreviousTab();

        //Calling DBError utility to  find initial count or error in log files.
//            DBError.navigateDBErrorPage();
//            int initialCount = PageBase.AdminPage().totalErrorCount();
//
//            // Switching to previous tab.
//            Utilities.switchPreviousTab();

        orderId = poaCompleteFlow_595(testType, imeiDetails);

        //Inventory Management Page verification.
        if (readConfig("Activation").contains("true")) {
            // inventoryManagementVerification_595();

            //Ship Admin Verification -orderId= ""
            shipAdminVerification_595(orderId);
        }

        //DBError Verification.
//            DBError.navigateDBErrorPage();
        //Assert.assertTrue(PageBase.AdminPage().isDBErrorFound(initialCount));

        Reporter.log("<h3>QA_595_ATT_NewActivationWith18MonthContractNoDepositNoNumPort - Test Case Completes<h3>");
        Log.endTestCase("QA_595_ATT_NewActivationWith18MonthContractNoDepositNoNumPort");
    }
    //endregion QA 595

    // region QA 748
    @Test(groups = {"att"})
    @Parameters("test-type")
    public void QA_748_ATT_NewActivationWithReserveContractIndentiferFailure(@Optional String testType) throws IOException, AWTException, InterruptedException {

        String orderId = "";
        IMEIDetails imeiDetails = PageBase.CSVOperations().GetIMEIAndProductName(CSVOperations.FileName.ATT_IPhoneDevice);

        // Adding Devices to Inventory.
        PageBase.InventoryManagementPage().launchInventoryInNewTab(BrowserSettings.readConfig("inventoryStoreIdDetail"));
        PageBase.InventoryManagementPage().addDeviceToInventory(imeiDetails.IMEI, imeiDetails.ProductName);

        PageBase.InventoryManagementPage().closeInventoryTab();
        Utilities.switchPreviousTab();

        Log.startTestCase("QA_748_ATT_NewActivationWithReserveContractIndentiferFailure");
        testType = BrowserSettings.readConfig("test-type");

        // Verify whether which environment to use internal or external.
        selectingCarrierEnviornment_748(testType);

        // Switching to previous tab.
        Utilities.switchPreviousTab();

        //Calling DBError utility to  find initial count or error in log files.
        DBError.navigateDBErrorPage();
        int initialCount = PageBase.AdminPage().totalErrorCount();

        // Switching to previous tab.
        Utilities.switchPreviousTab();

        orderId = poaCompleteFlow_748(testType, imeiDetails);

        //DBError Verification.
        DBError.navigateDBErrorPage();
        Assert.assertTrue(PageBase.AdminPage().isDBErrorFound(initialCount));

        Reporter.log("<h3>QA_748_ATT_NewActivationWithReserveContractIndentiferFailure - Test Case Completes<h3>");
        Log.endTestCase("QA_748_ATT_NewActivationWithReserveContractIndentiferFailure");
    }
    //endregion QA 748

    // region QA 948
    @Test(groups = {"att"})
    @Parameters("test-type")
    public void QA_948_ATT_AddLineFlowWithNext(@Optional String testType) throws IOException, AWTException, InterruptedException {

        String orderId = "";
        IMEIDetails imeiDetails = PageBase.CSVOperations().GetIMEIAndProductName(CSVOperations.FileName.ATT_IPhoneDevice);
        CustomerDetails customerDetails = CSVOperations.ReadCustomerDetailsFromCSV(ServiceProviderVerificationPage.IdType.DRIVERLICENCE);

        // Adding Devices to Inventory.
        PageBase.InventoryManagementPage().launchInventoryInNewTab(BrowserSettings.readConfig("inventoryStoreIdDetail"));
        PageBase.InventoryManagementPage().addDeviceToInventory(imeiDetails.IMEI, imeiDetails.ProductName);

        PageBase.InventoryManagementPage().closeInventoryTab();
        Utilities.switchPreviousTab();
        try {
            Log.startTestCase("QA_948_ATT_AddLineFlowWithNext");
            testType = BrowserSettings.readConfig("test-type");

            //Verify whether which environment to use internal or external.
            selectingCarrierEnviornment_948(testType);

            //Switching to previous tab.
            Utilities.switchPreviousTab();

            //Calling DBError utility to  find initial count or error in log files.
            DBError.navigateDBErrorPage();
            int initialCount = PageBase.AdminPage().totalErrorCount();

            //  Switching to previous tab.
            Utilities.switchPreviousTab();


            orderId = poaCompleteFlow_948(testType, imeiDetails, customerDetails);

            //DBError Verification.
            DBError.navigateDBErrorPage();
            Assert.assertTrue(PageBase.AdminPage().isDBErrorFound(initialCount));

            Reporter.log("<h3>QA_948_ATT_AddLineFlowWithNext - Test Case Completes<h3>");
            Log.endTestCase("QA_948_ATT_AddLineFlowWithNext");
        } catch (Exception ex) {
            Log.error(ex.getMessage());
            Utilities.driverTakesScreenshot("QA_948_ATT_AddLineFlowWithNext");
            Assert.fail();
        }
    }
//endregion QA 948

    //region private methods

    //region QA 595 refactored methods

    private void selectingCarrierEnviornment_595(String testType) throws InterruptedException, AWTException, IOException {
        if (testType.equals("internal")) {
            // Need to set "Backend Simulator or Carrier Responder depend on test case  requirement.
            AdminBaseClass adminBaseClass = new AdminBaseClass();
            adminBaseClass.launchAdminInNewTab();

            PageBase.AdminPage().navigateToSimulator();

            //Selecting Carrier Responder
            carrierResponderSettingsQA_595();
        } else   //External
        {
            // Need to set External server from Admin page.
            AdminBaseClass adminBaseClass = new AdminBaseClass();
            adminBaseClass.launchAdminInNewTab();

            PageBase.AdminPage().navigateToSimulator();
            PageBase.AdminPage().selectWebAPIResponse("Verizon", "External");
        }
    }

    private void carrierResponderSettingsQA_595() {
        PageBase.AdminPage().selectWebAPIResponse("ATT", "CarrierResponder");

        //Selecting Carrier config file.
        PageBase.AdminPage().selectAPIConfig("ATT");
        // Selecting ATT and response xml.
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().attCarrierTab);
        PageBase.CarrierResponseXMLPage().attCarrierTab.click();

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "addaccount",
                "multiple_approved_with_no_deposit.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "activatesubscriber",
                "single_activated.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "executecreditcheck",
                "single_line_no_deposit.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiremarketserviceareas",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquirepriceplans",
                "individual_plans.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiresubscriberbillingagreement",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "reservecontractidentifier",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "releasesubscribernumber",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "reservesubscribernumber",
                "single_line_success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);


      /*  PageBase.CarrierResponseXMLPage().selectOptions("current",
                "activatesubscriber",
                "multiple_activated.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);*/
    }

    private void inventoryManagementVerification_595() {


    }

    private void shipAdminVerification_595(String orderId) throws IOException {
        ShipAdminBaseClass.launchShipAdminInNewTab();
        PageBase.OrderSummaryPage().goToOrderSummaryPage(orderId);
        String eventLogTableContent = PageBase.OrderSummaryPage().checkForErrorAndLog(orderId);
        String status = PageBase.OrderSummaryPage().getOrderStatus();
        Assert.assertEquals(status, Constants.SHIPPED);
        Reporter.log("<h3>Ship Admin Verification:" + Constants.SHIPPED);
    }

    private String poaCompleteFlow_595(String testType, IMEIDetails imeiDetails) throws IOException, AWTException, InterruptedException {
        String cartDevice1price = "";
        String cartDevice2price = "";
        String orderId = "";//Login to retail page.
        String spvDetails = PageBase.CSVOperations().GetSpvDetails();
        String[] spvCollections = spvDetails.split(",");
        lStartTime = new Date().getTime();
        pageName = readPageName("PoaLogin");
        PageBase.LoginPageRetail().poaLogin(Utilities.getCredentials("tuserUN"),
                Utilities.getCredentials("tuserPwd"), Utilities.getCredentials("storeId0003"));
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Click on Sales & Activations page.
        lStartTime = new Date().getTime();
        pageName = readPageName("SaleAndActivation");
        Utilities.waitForElementVisible(PageBase.HomePageRetail().salesAndActivationsLink);
        PageBase.HomePageRetail().salesAndActivationsLink.click();
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Click on New Activation link.
        lStartTime = new Date().getTime();
        pageName = readPageName("DeviceScan");
        Utilities.waitForElementVisible(PageBase.ChoosePathPage().newActivation);
        PageBase.ChoosePathPage().newActivation.click();
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Scanning smart phone.
        Utilities.waitForElementVisible(PageBase.DeviceScanPage().iMEIESNTextbox);
        PageBase.DeviceScanPage().enterDeviceScanDetails(imeiDetails.IMEI);

        //Sprint Easy Pay Page
        Utilities.waitForElementVisible(PageBase.SprintEasyPayPage().yesButton);
        PageBase.SprintEasyPayPage().yesButton.click();

        //Filling information in Carrier Credit Check Page.
        Utilities.waitForElementVisible(PageBase.CarrierCreditCheckPage().guestAgreeToRunCCCheckBox);
        String ssn = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.ATTSSNWithoutDeposit);
        CarrierCreditCheckDetails cccDetails = getCarrierCreditCheckDetails(ssn);
        PageBase.CarrierCreditCheckPage().populatingCarrierCreditCheckPage(cccDetails);

        PageBase.CarrierCreditCheckPage().guestAgreeToRunCCCheckBox.click();

        lStartTime = new Date().getTime();
        pageName = readPageName("CarrierCreditCheck");
        PageBase.CommonControls().continueButton.click();
        Utilities.implicitWaitSleep(1000);
        try {
            if (PageBase.CommonControls().continueButton.isEnabled())
                PageBase.CommonControls().continueButton.click();
        } catch (Exception e) {

        }
        Utilities.webPageLoadTime(lStartTime, pageName);

        //ATT Next 18 months Eligibility Result
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        PageBase.ATTEligibiltyResultPage().attNext18MonthReadioButton.click();
        PageBase.CommonControls().continueCommonButton.click();

        //ATT Next 18 months Eligibility Result
//        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
//        // PageBase.SprintEasyPayEligibilityResultPage().sprintEasyPayInstallmentDetailsTab.click();
//        Utilities.waitForElementVisible(PageBase.ATTEligibiltyResultPage().elegibleForNext, 120);
//        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().elegibleForNext.isDisplayed());
//        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().downPaymentLabel.isDisplayed());
//        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().installmentContractLengthLabel.isDisplayed());
//        PageBase.ATTFinanceProgramPage().attNext18.click();
//        PageBase.CommonControls().continueCommonButton.click();
//        Utilities.implicitWaitSleep(6000);

        // Selecting Plan.
        PageBase.VerizonShopPlansPage().selectAnyATTPlan();

        //Verifying device with plan and continue.
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        //Select Plan - Storing the Device and plan prices for further verification.
        cartDevice1price = PageBase.CartPage().device1Price.getText();
        cartDevice1price = cartDevice1price.replace("*", "");
        cartDevice2price = PageBase.CartPage().device2Price.getText();
        String monthlyRecurringFee = driver.findElement(By.xpath("//td[contains(text(),'Monthly Recurring Fee:')]/parent::tr/td[2]")).getText();
        String totalDueToday = PageBase.CartPage().totalDueToday.getText();
        PageBase.CommonControls().continueCommonButton.click();
        Reporter.log("<br> Device Price in Cart Page:"+cartDevice1price);
        Utilities.implicitWaitSleep(6000);

        //Selecting Message plan feature.
        Utilities.ClickElement(PageBase.SelectPlanFeaturesPage().Messaging);
        PageBase.SelectPlanFeaturesPage().selectMessagePlan();

        // Selecting FamilyMap
        Utilities.implicitWaitSleep(2000);
        PageBase.SelectPlanFeaturesPage().selectFamilyMapPlan(0);

        //Selecting iphone data
        Utilities.implicitWaitSleep(2000);
        PageBase.SelectPlanFeaturesPage().selectIphoneData(0);

        //Selection Other plan
        Utilities.implicitWaitSleep(2000);
        PageBase.SelectPlanFeaturesPage().selectOtherPlan(0);

        //Selecting Nagivation Plan
        Utilities.implicitWaitSleep(2000);
        PageBase.SelectPlanFeaturesPage().selectNavigationPlan();

        Utilities.waitForElementVisible(PageBase.SelectPlanFeaturesPage().continueSPFButton);
        PageBase.SelectPlanFeaturesPage().continueSPFButton.click();

        //For Service not available page
        try {
            Utilities.implicitWaitSleep(5000);
            driver.navigate().back();
            Utilities.waitForElementVisible(PageBase.SelectPlanFeaturesPage().continueSPFButton);
            PageBase.SelectPlanFeaturesPage().continueSPFButton.click();
            Utilities.implicitWaitSleep(6000);
        }catch (Exception ex)
        {}

        // Selecting Insurance.
        try {
            Utilities.waitForElementVisible(PageBase.CommonControls().continueButton);
            PageBase.SelectProtectionPlanInsurancePage().selectAnInsurance();
        } catch (Exception ex) {
        }

        // Selecting No Number Porting.
        Utilities.waitForElementVisible(PageBase.NumberPortPage().noNumberPortRadiobutton);
        PageBase.NumberPortPage().noNumberPortRadiobutton.click();
        PageBase.CommonControls().continueButton.click();

        //Service Provider Verification Page
        //PageBase.ServiceProviderVerificationPage().populatingSprintSPV(spvCollections[0], spvCollections[1], spvCollections[2]);

        // Order Review and Confirm Page.
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        // Assert.assertEquals(PageBase.OrderReviewAndConfirmPage().device1PriceMonthly.getText(), cartDevice1price);
       // Assert.assertEquals("$27.09", cartDevice1price);

        double device1Price = Double.parseDouble(cartDevice1price.substring(1));

//        String totaldueToday = driver.findElement(By.xpath("(//h3[contains(text(),'Total Due Today:')]/parent::th/following-sibling::th)[2]/child::h3")).getText();
//        totaldueToday = totalDueToday.replace("inc. tax", "");
//
//        String device1Activation = driver.findElement(By.xpath("((//span[contains(text(),'One Time Activation Fee:')])[1]/following-sibling::span)[1]")).getText();
//        Assert.assertEquals("$0.00", device1Activation);

        String estimatedMonthlyInstallment = driver.findElement(By.xpath("//div[@class='ordcSummary']/table/tfoot/tr[3]/th[3]/h3")).getText();
        Assert.assertEquals(device1Price, Double.parseDouble(estimatedMonthlyInstallment.replace("$", "").replace(",", "")));

//        Reporter.log("<br> Total due" +
//                " Total Device Fee: " + totaldueToday);
        Reporter.log("<br> Device Price in Order Review and Confirmation Page Matches with Cart Page.");

        PageBase.CommonControls().continueCommonButton.click();

        if (readConfig("Activation").contains("true")) {
            //Terms and Condition Page.
            Utilities.waitForElementVisible(PageBase.TermsAndConditionsPage().emailTCChkBox);
            PageBase.TermsAndConditionsPage().emailTCChkBox.click();
            PageBase.TermsAndConditionsPage().carrierTermsCheckBox.click();
            PageBase.TermsAndConditionsPage().acceptsTargetTCCheckbox.click();
            PageBase.WirelessCustomerAgreementPage().signingWCA(driver);
            //PageBase.TermsAndConditionsPage().attTermAndCondititionContinueButton.click();
            PageBase.TermsAndConditionsPage().continueTCButton.click();
            Utilities.implicitWaitSleep(8000);

            // Credit Card Verification Result
            try {
                Assert.assertTrue(PageBase.CreditCheckVerificationResultsPage().creditCheckPassChkBox.isDisplayed());
                PageBase.CreditCheckVerificationResultsPage().creditCheckPassChkBox.click();
                PageBase.CommonControls().continueCommonButton.click();
            }catch (Exception ex)
            {}

            // Credit card payment  page is coming.
            boolean visaexists = driver.findElements(By.id("radio-1a")).size() != 0;
            if (visaexists) {
                Utilities.waitForElementVisible(PageBase.PaymentRequiredPage().visaTab);
                PageBase.PaymentRequiredPage().populatingCardDetailsPaymentRequired(PaymentRequiredPage.CardType.VISA);
                PageBase.PaymentRequiredPage().continuePRButton.click();
            }

            //Print Mobile Scan Sheet.
            Utilities.waitForElementVisible(PageBase.PrintMobileScanSheetPage().continueFirstMSSButton);
            orderId = PageBase.PrintMobileScanSheetPage().orderNumberValuePMSSText.getText();
            Assert.assertTrue(PageBase.PrintMobileScanSheetPage().firstDeviceBarCode.isDisplayed());

            PageBase.PrintMobileScanSheetPage().continueFirstMSSButton.click();

            // Payment Verification page. Scan Reciept id.
            Utilities.waitForElementVisible(PageBase.PaymentVerificationPage().textboxTargetReceiptID);
            String recieptID = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.ReceiptId);
            PageBase.PaymentVerificationPage().textboxTargetReceiptID.sendKeys(recieptID);
            PageBase.PaymentVerificationPage().submitButton.click();

            //Device Verification and Activation page. Scan Device IEMI and enter SIM number.
            Utilities.waitForElementVisible(PageBase.DeviceVerificationaandActivation().deviceIMEITextbox);
            PageBase.DeviceVerificationaandActivation().deviceVerificationActiavtionFor1Device(imeiDetails.IMEI, imeiDetails.Sim);
            PageBase.CommonControls().continueActivation.click();

            //Updating device in csv files.
            PageBase.CSVOperations().UpdateIMEIStatusToUsed(imeiDetails.IMEI, CSVOperations.FileName.ATT_IPhoneDevice);
            PageBase.CSVOperations().UpdateIMEIStatusToUsed(recieptID, CSVOperations.FileName.ReceiptId);

            //Device Financing Installment Contract.
            Utilities.waitForElementVisible(PageBase.DeviceFinancingInstallmentContractPage().installmentContractError);
            String installContractError = PageBase.DeviceFinancingInstallmentContractPage().installmentContractError.getText();
            Assert.assertEquals(installContractError, Constants.INTALLLMENT_CONTRACT_ERROR);
        } else {
            Reporter.log("<h3><font color='red'> Activation is stopped purposefully. Change the key in Test Settings to Activate </h3></font>");
        }
        return orderId;
    }

    //endregion QA 595

    //region QA 748 refactored methods

    private void selectingCarrierEnviornment_748(String testType) throws InterruptedException, AWTException, IOException {
        if (testType.equals("internal")) {
            // Need to set "Backend Simulator or Carrier Responder depend on test case  requirement.
            AdminBaseClass adminBaseClass = new AdminBaseClass();
            adminBaseClass.launchAdminInNewTab();

            PageBase.AdminPage().navigateToSimulator();

            //Selecting Carrier Responder
            carrierResponderSettingsQA_748();
        } else   //External
        {
            // Need to set External server from Admin page.
            AdminBaseClass adminBaseClass = new AdminBaseClass();
            adminBaseClass.launchAdminInNewTab();

            PageBase.AdminPage().navigateToSimulator();
            PageBase.AdminPage().selectWebAPIResponse("ATT", "External");
        }
    }

    private void carrierResponderSettingsQA_748() {
        PageBase.AdminPage().selectWebAPIResponse("ATT", "CarrierResponder");

        //Selecting Carrier config file.
        PageBase.AdminPage().selectAPIConfig("ATT");
        // Selecting ATT and response xml.
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().attCarrierTab);
        PageBase.CarrierResponseXMLPage().attCarrierTab.click();

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "addaccount",
                "single_approved_with_deposit.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "executecreditcheck",
                "single_line_with_deposit.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiremarketserviceareas",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquirepriceplans",
                "individual_plans.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiresubscriberbillingagreement",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiresubscriptioneligibility",
                "eligible_5_lines.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "reservecontractidentifier",
                "fault.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

      /*  PageBase.CarrierResponseXMLPage().selectOptions("current",
                "activatesubscriber",
                "multiple_activated.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);*/
    }

    private String poaCompleteFlow_748(String testType, IMEIDetails imeiDetails) throws IOException, AWTException, InterruptedException {
        String cartDevice1price = "";
        String cartDevice2price = "";
        String orderId = "";//Login to retail page.
        String spvDetails = PageBase.CSVOperations().GetSpvDetails();
        String[] spvCollections = spvDetails.split(",");
        lStartTime = new Date().getTime();
        pageName = readPageName("PoaLogin");
        PageBase.LoginPageRetail().poaLogin(Utilities.getCredentials("tuserUN"),
                Utilities.getCredentials("tuserPwd"), Utilities.getCredentials("storeId0003"));
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Click on Sales & Activations page.
        lStartTime = new Date().getTime();
        pageName = readPageName("SaleAndActivation");
        Utilities.waitForElementVisible(PageBase.HomePageRetail().salesAndActivationsLink);
        PageBase.HomePageRetail().salesAndActivationsLink.click();
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Click on New Activation link.
        lStartTime = new Date().getTime();
        pageName = readPageName("DeviceScan");
        Utilities.waitForElementVisible(PageBase.ChoosePathPage().newActivation);
        PageBase.ChoosePathPage().newActivation.click();
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Scanning smart phone.
        Utilities.waitForElementVisible(PageBase.DeviceScanPage().iMEIESNTextbox);
        PageBase.DeviceScanPage().enterDeviceScanDetails(imeiDetails.IMEI);

        //AT&T Easy Pay Page
        Utilities.waitForElementVisible(PageBase.SprintEasyPayPage().yesButton);
        PageBase.SprintEasyPayPage().yesButton.click();

        //Filling information in Carrier Credit Check Page.
        Utilities.waitForElementVisible(PageBase.CarrierCreditCheckPage().guestAgreeToRunCCCheckBox);
        String ssn = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.SSNWithoutDeposit);
        CarrierCreditCheckDetails cccDetails = getCarrierCreditCheckDetails(ssn);
        PageBase.CarrierCreditCheckPage().populatingCarrierCreditCheckPage(cccDetails);

        PageBase.CarrierCreditCheckPage().guestAgreeToRunCCCheckBox.click();

        Utilities.implicitWaitSleep(1000);
        lStartTime = new Date().getTime();
        pageName = readPageName("CarrierCreditCheck");
        PageBase.CommonControls().continueButton.click();
        Utilities.implicitWaitSleep(10000);
        try {
            if (PageBase.CommonControls().continueButton.isEnabled())
                PageBase.CommonControls().continueButton.click();
        } catch (Exception e) {

        }
        Utilities.webPageLoadTime(lStartTime, pageName);

        //Credit check verification result
        Assert.assertTrue(PageBase.CreditCheckVerificationResultsPage().depositCheckBox.isDisplayed());
        PageBase.CreditCheckVerificationResultsPage().depositCheckBox.click();
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        PageBase.CommonControls().continueCommonButton.click();

        //ATT Next 18 months Eligibility Result
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        // PageBase.SprintEasyPayEligibilityResultPage().sprintEasyPayInstallmentDetailsTab.click();
        Utilities.waitForElementVisible(PageBase.ATTEligibiltyResultPage().elegibleForNext, 120);
        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().elegibleForNext.isDisplayed());
        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().downPaymentLabel.isDisplayed());
        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().installmentContractLengthLabel.isDisplayed());
        PageBase.ATTFinanceProgramPage().attNext18.click();
        PageBase.CommonControls().continueCommonButton.click();
        Utilities.implicitWaitSleep(6000);

        // Selecting Plan.
        Utilities.waitForElementVisible(PageBase.VerizonShopPlansPage().attUnlimitedPlan);
        PageBase.VerizonShopPlansPage().attUnlimitedPlan.click();
        PageBase.VerizonShopPlansPage().addPlan();

        //Verifying device with plan and continue.
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        //Select Plan - Storing the Device and plan prices for further verification.
        cartDevice1price = PageBase.CartPage().device1Price.getText();
        cartDevice1price = cartDevice1price.replace("*", "");
        cartDevice2price = PageBase.CartPage().device2Price.getText();
        String monthlyRecurringFee = driver.findElement(By.xpath("//td[contains(text(),'Monthly Recurring Fee:')]/parent::tr/td[2]")).getText();
        String totalDueToday = PageBase.CartPage().totalDueToday.getText();
        PageBase.CommonControls().continueCommonButton.click();
//        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
//        String cartDeviceprice = PageBase.CartPage().device1Price.getText();
//        PageBase.CommonControls().continueCommonButton.click();
        Utilities.implicitWaitSleep(6000);

        //Selecting Message plan feature.
        Utilities.ClickElement(PageBase.SelectPlanFeaturesPage().Messaging);
        PageBase.SelectPlanFeaturesPage().selectMessagePlan();

        // Selecting FamilyMap
        PageBase.SelectPlanFeaturesPage().selectFamilyMapPlan(0);

        //Selecting iphone data
        PageBase.SelectPlanFeaturesPage().selectIphoneData(0);

        //Selection Other plan
        PageBase.SelectPlanFeaturesPage().selectOtherPlan(0);

        //Selecting Nagivation Plan
        PageBase.SelectPlanFeaturesPage().selectNavigationPlan();

        Utilities.waitForElementVisible(PageBase.SelectPlanFeaturesPage().continueSPFButton);
        PageBase.SelectPlanFeaturesPage().continueSPFButton.click();

        //For Service not available page
        driver.navigate().back();
        PageBase.SelectPlanFeaturesPage().continueSPFButton.click();

        Utilities.implicitWaitSleep(6000);
        // Selecting Insurance.
//        try {
//            Utilities.waitForElementVisible(PageBase.CommonControls().continueButton);
//            PageBase.SelectProtectionPlanInsurancePage().selectNoInsurance();
//        } catch (Exception ex) {
//        }

        // Selecting No Number Porting.
        Utilities.waitForElementVisible(PageBase.NumberPortPage().noNumberPortRadiobutton);
        PageBase.NumberPortPage().noNumberPortRadiobutton.click();
        PageBase.CommonControls().continueButton.click();

        //Service Provider Verification Page
        //PageBase.ServiceProviderVerificationPage().populatingSprintSPV(spvCollections[0], spvCollections[1], spvCollections[2]);

        // Order Review and Confirm Page.
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        // Assert.assertEquals(PageBase.OrderReviewAndConfirmPage().device1PriceMonthly.getText(), cartDevice1price);
        Assert.assertEquals("$27.09", cartDevice1price);

        double device1Price = Double.parseDouble(cartDevice1price.substring(1));

//        String totaldueToday = driver.findElement(By.xpath("(//h3[contains(text(),'Total Due Today:')]/parent::th/following-sibling::th)[2]/child::h3")).getText();
//        totaldueToday = totalDueToday.replace("inc. tax", "");
//
//        String device1Activation = driver.findElement(By.xpath("((//span[contains(text(),'One Time Activation Fee:')])[1]/following-sibling::span)[1]")).getText();
//        Assert.assertEquals("$0.00", device1Activation);

        String estimatedMonthlyInstallment = driver.findElement(By.xpath("//div[@class='ordcSummary']/table/tfoot/tr[3]/th[3]/h3")).getText();
        Assert.assertEquals(device1Price, Double.parseDouble(estimatedMonthlyInstallment.replace("$", "").replace(",", "")));

//        Reporter.log("<br> Total due" +
//                " Total Device Fee: " + totaldueToday);
        Reporter.log("<br> Device Price in Order Review and Confirmation Page Matches with Cart Page.");

        PageBase.CommonControls().continueCommonButton.click();

        if (readConfig("Activation").contains("true")) {
            //Terms and Condition Page.
            Utilities.waitForElementVisible(PageBase.TermsAndConditionsPage().emailTCChkBox);
            PageBase.TermsAndConditionsPage().emailTCChkBox.click();
            PageBase.TermsAndConditionsPage().carrierTermsCheckBox.click();
            PageBase.TermsAndConditionsPage().acceptsTargetTCCheckbox.click();
            PageBase.WirelessCustomerAgreementPage().signingWCA(driver);
            PageBase.TermsAndConditionsPage().continueTCButton.click();
            Utilities.implicitWaitSleep(10000);

            // Credit Card Verification Result
            Assert.assertTrue(PageBase.CreditCheckVerificationResultsPage().creditCheckPassChkBox.isDisplayed());
            PageBase.CreditCheckVerificationResultsPage().creditCheckPassChkBox.click();
            PageBase.CommonControls().continueCommonButton.click();

            // Credit card payment  page is coming.
            boolean visaexists = driver.findElements(By.id("radio-1a")).size() != 0;
            if (visaexists) {
                Utilities.waitForElementVisible(PageBase.PaymentRequiredPage().visaTab);
                PageBase.PaymentRequiredPage().populatingCardDetailsPaymentRequired(PaymentRequiredPage.CardType.VISA);
                PageBase.PaymentRequiredPage().continuePRButton.click();
            }

            //Print Mobile Scan Sheet.
            Utilities.waitForElementVisible(PageBase.PrintMobileScanSheetPage().continueFirstMSSButton);
            orderId = PageBase.PrintMobileScanSheetPage().orderNumberValuePMSSText.getText();
            Assert.assertTrue(PageBase.PrintMobileScanSheetPage().firstDeviceBarCode.isDisplayed());

            PageBase.PrintMobileScanSheetPage().continueFirstMSSButton.click();

            // Payment Verification page. Scan Reciept id.
            Utilities.waitForElementVisible(PageBase.PaymentVerificationPage().textboxTargetReceiptID);
            String recieptID = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.ReceiptId);
            PageBase.PaymentVerificationPage().textboxTargetReceiptID.sendKeys(recieptID);
            PageBase.PaymentVerificationPage().submitButton.click();

            //Device Verification and Activation page. Scan Device IEMI and enter SIM number.
            Utilities.waitForElementVisible(PageBase.DeviceVerificationaandActivation().deviceIMEITextbox);
            PageBase.DeviceVerificationaandActivation().deviceVerificationActiavtionFor1Device(imeiDetails.IMEI, imeiDetails.Sim);
            PageBase.CommonControls().continueActivation.click();

            //Updating device in csv files.
            PageBase.CSVOperations().UpdateIMEIStatusToUsed(imeiDetails.IMEI, CSVOperations.FileName.ATT_IPhoneDevice);
            PageBase.CSVOperations().UpdateIMEIStatusToUsed(recieptID, CSVOperations.FileName.ReceiptId);

            //Device Financing Installment Contract.
            Utilities.waitForElementVisible(PageBase.DeviceFinancingInstallmentContractPage().installmentContractError);
            String installContractError = PageBase.DeviceFinancingInstallmentContractPage().installmentContractError.getText();
            Assert.assertEquals(installContractError, Constants.INTALLLMENT_CONTRACT_ERROR);
            QA_748ShipAdminVerification(orderId);

        } else {
            Reporter.log("<h3><font color='red'> Activation is stopped purposefully. Change the key in Test Settings to Activate </h3></font>");
        }
        return orderId;
    }

    //endregion QA 748
    private void QA_748ShipAdminVerification(String orderId) throws IOException {
        //Ship Admin
        ShipAdminBaseClass.launchShipAdminInNewTab();
        PageBase.OrderSummaryPage().goToOrderSummaryPage(orderId);
        String eventLogTableContent = PageBase.OrderSummaryPage().checkForErrorAndLog(orderId);
        String status = PageBase.OrderSummaryPage().getOrderStatus();
        Assert.assertEquals(status, Constants.ORDER_CANCELLED_BY_USER);
        Assert.assertTrue(eventLogTableContent.contains(Constants.RESERVE_CONTRACT_INDENTIFIER_FAILED));
    }

    //region QA 948 refactored methods
    private void selectingCarrierEnviornment_948(String testType) throws InterruptedException, AWTException, IOException {
        if (testType.equals("internal")) {
            // Need to set "Backend Simulator or Carrier Responder depend on test case  requirement.
            AdminBaseClass adminBaseClass = new AdminBaseClass();
            adminBaseClass.launchAdminInNewTab();

            PageBase.AdminPage().navigateToSimulator();

            //Selecting Carrier Responder
            carrierResponderSettingsQA_948();

        } else   //External
        {
            // Need to set External server from Admin page.
            AdminBaseClass adminBaseClass = new AdminBaseClass();
            adminBaseClass.launchAdminInNewTab();

            PageBase.AdminPage().navigateToSimulator();
            PageBase.AdminPage().selectWebAPIResponse("ATT", "External");
        }
    }

    private void setFlagsQA_948(String testType) throws InterruptedException, AWTException, IOException {
        // Need to set "Backend Simulator or Carrier Responder depend on test case  requirement.
        AdminBaseClass adminBaseClass = new AdminBaseClass();
        adminBaseClass.launchAdminInNewTab();

        driver.findElement(By.linkText("QA Tools")).click();
        driver.findElement(By.linkText("Partner Communications Outage Simulator")).click();
        driver.findElement(By.id("down[1003]")).click();
        driver.findElement(By.xpath("//input[@value='Set Flags']")).click();
        Utilities.switchPreviousTab();

    }

    private void carrierResponderSettingsQA_948() {
        PageBase.AdminPage().selectWebAPIResponse("ATT", "CarrierResponder");

        //Selecting Carrier config file.
        PageBase.AdminPage().selectAPIConfig("ATT");
        // Selecting ATT and response xml.
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().attCarrierTab);
        PageBase.CarrierResponseXMLPage().attCarrierTab.click();

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "addaccount",
                "single_approved_with_no_deposit.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(8000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "executecreditcheck",
                "single_line_no_deposit.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiremarketserviceareas",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquirepriceplans",
                "individual_plans.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiresubscriberbillingagreement",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "inquiresubscriptioneligibility",
                "eligible_5_lines.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "reservecontractidentifier",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(7000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "activatesubscriber",
                "fault.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "releasesubscribernumber",
                "success.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);

        PageBase.CarrierResponseXMLPage().selectOptions("current",
                "reservesubscribernumber",
                "fault.xml");
        Utilities.implicitWaitSleep(1000);
        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
        Utilities.implicitWaitSleep(4000);
        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
        Utilities.implicitWaitSleep(10000);

//        PageBase.CarrierResponseXMLPage().selectOptions("current",
//                "validatecreditcard", "default.xml");
//        Utilities.implicitWaitSleep(1000);
//        Utilities.waitForElementVisible(PageBase.CarrierResponseXMLPage().saveResponseButton);
//        Utilities.WaitUntilElementIsClickable(PageBase.CarrierResponseXMLPage().saveResponseButton);
//        PageBase.CarrierResponseXMLPage().saveResponseButton.click();
//        Utilities.implicitWaitSleep(10000);


    }

    private String poaCompleteFlow_948(String testType, IMEIDetails imeiDetails, CustomerDetails customerDetails) throws IOException, AWTException, InterruptedException {
        String cartDevice1price = "";
        String cartDevice2price = "";
        String orderId = "";//Login to retail page.
        String spvDetails = PageBase.CSVOperations().GetSpvDetails();
        String[] spvCollections = spvDetails.split(",");
//        AccountDetails accountDetails = PageBase.CSVOperations().GetDetails(CSVOperations.FileName.ATTCarrierInfo);
//        String MTNNumber = accountDetails.MTN;
//        String accountPassword = accountDetails.Password;
        String SSN = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.ATTSSNWithoutDeposit);
        SSN = SSN.substring(5, 9);
        String esnNo = imeiDetails.IMEI;
        String simNumber = imeiDetails.Sim;

        lStartTime = new Date().getTime();
        pageName = readPageName("PoaLogin");
        PageBase.LoginPageRetail().poaLogin(Utilities.getCredentials("tuserUN"),
                Utilities.getCredentials("tuserPwd"), Utilities.getCredentials("storeId0003"));
        Utilities.webPageLoadTime(lStartTime, pageName);

        // Click on Sales & Activations page.
        lStartTime = new Date().getTime();
        pageName = readPageName("SaleAndActivation");
        Utilities.waitForElementVisible(PageBase.HomePageRetail().salesAndActivationsLink);
        PageBase.HomePageRetail().salesAndActivationsLink.click();
        Utilities.webPageLoadTime(lStartTime, pageName);

        //Click on Existing Carrier Link
        lStartTime = new Date().getTime();
        Utilities.waitForElementVisible(PageBase.ChoosePathPage().existingCarrier);
        PageBase.ChoosePathPage().existingCarrier.click();
        Utilities.webPageLoadTime(lStartTime, pageName);

        //Click on AAL to existing Plan
        Utilities.waitForElementVisible(PageBase.PickYourPathPage().AALExistingAccount);
        PageBase.PickYourPathPage().AALExistingAccount.click();
        PageBase.CommonControls().continueButtonDVA.click();

        //Fill AT&T Details
        PageBase.UECVerificationPage().fillAATDetails(customerDetails.PhNum, SSN, customerDetails.Zip, "");
        PageBase.UECVerificationPage().continueATTButton.click();

        //Select AAL to existing Family Plan
        PageBase.SelectAnOptionPage().AALExistingFamilyPlan.click();
        PageBase.CommonControls().continueButtonDVA.click();

        //Scann a Device
        Utilities.waitForElementVisible(PageBase.DeviceScanPage().iMEIESNTextbox);
        PageBase.DeviceScanPage().iMEIESNTextbox.sendKeys(esnNo);//"889988990007"
        PageBase.DeviceScanPage().submitDeviceButton.click();
        Utilities.implicitWaitSleep(10000);

        //AT&T Easy Pay Page
//        Utilities.waitForElementVisible(PageBase.SprintEasyPayPage().yesButton);
//        PageBase.SprintEasyPayPage().yesButton.click();
        Utilities.waitForElementVisible(PageBase.CommonControls().continueButtonDVA);
        PageBase.CommonControls().continueButtonDVA.click();
        Utilities.implicitWaitSleep(10000);
        PageBase.CommonControls().continueButtonDVA.click();
        Utilities.implicitWaitSleep(10000);

        //Filling information in Carrier Credit Check Page.
        Utilities.waitForElementVisible(PageBase.CarrierCreditCheckPage().guestAgreeToRunCCCheckBox);
        String ssn = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.ATTSSNWithoutDeposit);
        CarrierCreditCheckDetails cccDetails = getCarrierCreditCheckDetails(ssn);
        PageBase.CarrierCreditCheckPage().populatingCarrierCreditCheckPage(cccDetails);

        PageBase.CarrierCreditCheckPage().guestAgreeToRunCCCheckBox.click();

        Utilities.implicitWaitSleep(1000);
        lStartTime = new Date().getTime();
        pageName = readPageName("CarrierCreditCheck");
        PageBase.CommonControls().continueButton.click();
        Utilities.implicitWaitSleep(10000);
        try {
            if (PageBase.CommonControls().continueButton.isEnabled())
                PageBase.CommonControls().continueButton.click();
        } catch (Exception e) {

        }
        Utilities.webPageLoadTime(lStartTime, pageName);

        //Credit check verification result
        Assert.assertTrue(PageBase.CreditCheckVerificationResultsPage().creditCheckPassChkBox.isDisplayed());
        PageBase.CreditCheckVerificationResultsPage().creditCheckPassChkBox.click();
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        PageBase.CommonControls().continueCommonButton.click();

      /*  //Add Cart Page
        PageBase.CommonControls().continueCommonButton.click();


        if(PageBase.ATTEligibiltyResultPage().elegibleForNext.isDisplayed())
        {
         //ATT Next 18 months Eligibility Result
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        // PageBase.SprintEasyPayEligibilityResultPage().sprintEasyPayInstallmentDetailsTab.click();
        Utilities.waitForElementVisible(PageBase.ATTEligibiltyResultPage().elegibleForNext, 120);
        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().elegibleForNext.isDisplayed());
        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().downPaymentLabel.isDisplayed());
        Assert.assertTrue(PageBase.ATTEligibiltyResultPage().installmentContractLengthLabel.isDisplayed());
        PageBase.ATTFinanceProgramPage().attNext12.click();
        PageBase.CommonControls().continueCommonButton.click();
        Utilities.implicitWaitSleep(6000);
        }

//        // Selecting Plan.
//        Utilities.waitForElementVisible(PageBase.VerizonShopPlansPage().attUnlimitedPlan);
//        PageBase.VerizonShopPlansPage().attUnlimitedPlan.click();
//        PageBase.VerizonShopPlansPage().addPlan();*/

        //Verifying device with plan and continue.
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        //Select Plan - Storing the Device and plan prices for further verification.
        cartDevice1price = PageBase.CartPage().device1Price.getText();
        cartDevice1price = cartDevice1price.replace("*", "");
        String totalDueToday = PageBase.CartPage().totalDueToday.getText();
        PageBase.CommonControls().continueCommonButton.click();
//        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
//        String cartDeviceprice = PageBase.CartPage().device1Price.getText();
//        PageBase.CommonControls().continueCommonButton.click();
        Utilities.implicitWaitSleep(6000);

        //Selecting Message plan feature.
        Utilities.ClickElement(PageBase.SelectPlanFeaturesPage().Messaging);
        PageBase.SelectPlanFeaturesPage().selectMessagePlan();

        // Selecting FamilyMap
        PageBase.SelectPlanFeaturesPage().selectFamilyMapPlan(0);

        //Selecting iphone data
        PageBase.SelectPlanFeaturesPage().selectIphoneData(0);

        // Selection Other plan
        PageBase.SelectPlanFeaturesPage().selectOtherPlan(0);

        //Selecting Nagivation Plan
        PageBase.SelectPlanFeaturesPage().selectNavigationPlan();

        Utilities.waitForElementVisible(PageBase.SelectPlanFeaturesPage().continueSPFButton);
        PageBase.SelectPlanFeaturesPage().continueSPFButton.click();

        //If we get Service Unavailable page
        Utilities.implicitWaitSleep(6000);
        driver.navigate().back();
        Utilities.implicitWaitSleep(6000);
        PageBase.SelectPlanFeaturesPage().continueSPFButton.click();
        Utilities.implicitWaitSleep(6000);

        // Selecting No Number Porting.
        Utilities.waitForElementVisible(PageBase.NumberPortPage().noNumberPortRadiobutton);
        PageBase.NumberPortPage().noNumberPortRadiobutton.click();
        PageBase.CommonControls().continueButton.click();

        // Order Review and Confirm Page.
        Utilities.waitForElementVisible(PageBase.CommonControls().continueCommonButton);
        // Assert.assertEquals(PageBase.OrderReviewAndConfirmPage().device1PriceMonthly.getText(), cartDevice1price)
        Assert.assertEquals("$199.99", cartDevice1price);

        double device1Price = Double.parseDouble(cartDevice1price.substring(1));

        String totaldueToday = driver.findElement(By.xpath("(//h3[contains(text(),'Total Due Today:')]/parent::th/following-sibling::th)[2]/child::h3")).getText();
        totaldueToday = totalDueToday.replace("inc. tax", "");

        Assert.assertEquals(device1Price, Double.parseDouble(totaldueToday.replace("$", "").replace(",", "")));

        Reporter.log("<br> Total due" +
                " Total Device Fee: " + totaldueToday);
        Reporter.log("<br> Device Price in Order Review and Confirmation Page Matches with Cart Page.");

        PageBase.CommonControls().continueCommonButton.click();

        if (readConfig("Activation").contains("true")) {
            //Terms and Condition Page.

            Utilities.waitForElementVisible(PageBase.TermsAndConditionsPage().emailTCChkBox);
            PageBase.TermsAndConditionsPage().emailTCChkBox.click();
            PageBase.TermsAndConditionsPage().carrierTermsCheckBox.click();
            PageBase.TermsAndConditionsPage().acceptsTargetTCCheckbox.click();
            PageBase.WirelessCustomerAgreementPage().signingWCA(driver);
            PageBase.TermsAndConditionsPage().continueTCButton.click();
            Utilities.implicitWaitSleep(10000);

            //Print Mobile Scan Sheet.
            Utilities.waitForElementVisible(PageBase.PrintMobileScanSheetPage().continueFirstMSSButton);
            orderId = PageBase.PrintMobileScanSheetPage().orderNumberValuePMSSText.getText();
            Assert.assertTrue(PageBase.PrintMobileScanSheetPage().firstDeviceBarCode.isDisplayed());

            PageBase.PrintMobileScanSheetPage().continueFirstMSSButton.click();

            // Payment Verification page. Scan Reciept id.
            Utilities.waitForElementVisible(PageBase.PaymentVerificationPage().textboxTargetReceiptID);
            String recieptID = PageBase.CSVOperations().GetIMEIOrSimNumberOrReceiptId(CSVOperations.FileName.ReceiptId);
            PageBase.PaymentVerificationPage().textboxTargetReceiptID.sendKeys(recieptID);
            PageBase.PaymentVerificationPage().submitButton.click();


            //Device Verification and Activation page. Scan Device IEMI and enter SIM number.
            Utilities.waitForElementVisible(PageBase.DeviceVerificationaandActivation().deviceIMEITextbox);
            PageBase.DeviceVerificationaandActivation().deviceVerificationActiavtionFor1Device(imeiDetails.IMEI, imeiDetails.Sim);

            if (testType == "External") {
                setFlagsQA_948(testType);
            }

            PageBase.CommonControls().continueActivation.click();
            do {
                Utilities.implicitWaitSleep(1000);
            } while (!driver.getCurrentUrl().contains("retail/support.htm"));
            Utilities.waitForElementVisible(PageBase.CommonControls().supportCenterPageMessage);
            QA_948ShipAdminVerification(orderId);

        } else {
            Reporter.log("<h3><font color='red'> Activation is stopped purposefully. Change the key in Test Settings to Activate </h3></font>");
        }
        return orderId;
    }

    private void QA_948ShipAdminVerification(String orderId) throws IOException {
        //Ship Admin
        ShipAdminBaseClass.launchShipAdminInNewTab();
        PageBase.OrderSummaryPage().goToOrderSummaryPage(orderId);
        String eventLogTableContent = PageBase.OrderSummaryPage().checkForErrorAndLog(orderId);
        String status = PageBase.OrderSummaryPage().getOrderStatus();
        //Assert.assertEquals(status, Constants.ORDER_CANCELLED_BY_USER);
        Assert.assertTrue(eventLogTableContent.contains(Constants.RESERVE_SUBSCRIBER_NUMBER_FAILED));
        Assert.assertTrue(eventLogTableContent.contains(Constants.EXCEPTION_CODE_004));
        Assert.assertTrue(eventLogTableContent.contains(Constants.RESERVE_SUBSCCRIBER_NUMBER_EXCEPTION_MESSASE));
    }


    //endregion QA 948 refactored methods

    //region Common methods
    private CarrierCreditCheckDetails getCarrierCreditCheckDetails(String ssn) throws IOException {
        CarrierCreditCheckDetails cccDetails = new CarrierCreditCheckDetails();
        PageBase.CSVOperations();
        CustomerDetails customerDetails = CSVOperations.ReadCustomerDetailsFromCSV(ServiceProviderVerificationPage.IdType.DRIVERLICENCE);
        cccDetails.setFirstName(customerDetails.FirstName);
        cccDetails.setLastName(customerDetails.LastName);
        cccDetails.setAddress1(customerDetails.Address1);
        cccDetails.setCity(customerDetails.City);
        cccDetails.setState(customerDetails.State);
        cccDetails.setZip(customerDetails.Zip);
        cccDetails.setHomePhone(customerDetails.PhNum);
        cccDetails.setEmail(customerDetails.EMail);
        cccDetails.setBirthMonth(customerDetails.BirthdayMonth);
        cccDetails.setBirthDate(customerDetails.BirthdayDay);
        cccDetails.setBirthYear(customerDetails.BirthdayYear);
        cccDetails.setSSN(ssn);
        cccDetails.setIDType(ServiceProviderVerificationPage.IdType.DRIVERLICENCE);
        cccDetails.setIdTypeState(customerDetails.IDState);
        cccDetails.setIdNumber(customerDetails.IDNumber);
        cccDetails.setMonth(customerDetails.IDExpirationMonth);
        cccDetails.setYear(customerDetails.IDExpirationYear);
        return cccDetails;
    }
    //endregion
//endregion private methods
}