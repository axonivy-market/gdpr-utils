package com.axonivy.utils.gdpr.test;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.IvyWebTest;
import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.SelenideElement;

@IvyWebTest(headless = false)
public class DataDeletionUITest {
	private static final String DELETE_FORM = "deletion-form:";
	private static final String TEST_PROCESS_ID = "gdpr-utils-test/194EF1C2639450F0/";
	private static final String CREATE_DUMMY_DATA = "gdpr-utils-demo/1948C6200884AE99/startCreateDummyData.ivp";
	private static final String MANUAL_TRIGGER_PROCESS = "gdpr-utils/1943EA22591E28D4/startDataDeletion.ivp";

	public void createTestData() {
		// Create dummy task
		open(EngineUrl.createProcessUrl(CREATE_DUMMY_DATA));
		// Manual start Data deletion process
		open(EngineUrl.createProcessUrl(MANUAL_TRIGGER_PROCESS));
	}

	@Test
	public void canChooseAllYearsAndDeleteDataTest() {
		cleanUpdata();
		createTestData();

		startFirstTaskOfDataDeletionProcess();

		verifyAndClickOnFinancialYearDropdown();

		verifyFinancialYearSelectionPanel();
		List<String> clickedIds = new ArrayList<>();
		var firstOption = verifyAndGetFirstFinOption();
		firstOption.click();
		clickedIds.add(firstOption.getText());

		// Verify that the submit button is enabled, before clicking it.
		$(By.id(DELETE_FORM + "submit")).shouldBe(enabled).click();

		// Verify that the confirmation popup was successful.
		var confirmDelete = By.id("confirm-data-deletion-dialog");
		$(confirmDelete).shouldBe(visible);
		$(confirmDelete).find(By.id("delete-btn")).click();

		$(By.id(DELETE_FORM + "data-deleting-message-group")).should(visible);
		$(By.id(DELETE_FORM + "finish-task")).should(visible, enabled).click();

		// Test no data available
		createTestData();
		startFirstTaskOfDataDeletionProcess();

		verifyAndClickOnFinancialYearDropdown();

		verifyFinancialYearSelectionPanel();
		firstOption = verifyAndGetFirstFinOption();
		assertFalse(clickedIds.contains(firstOption.getText()), "The deleted FIN still show on");
	}

	private SelenideElement verifyAndGetFirstFinOption() {
		var firstOption = $(".ui-selectcheckboxmenu-items-wrapper").findAll("li.ui-selectcheckboxmenu-item").first();
		firstOption.shouldBe(enabled);
		return firstOption;
	}

	private void verifyFinancialYearSelectionPanel() {
		var financialYearSelection = By.id(DELETE_FORM + "financial-year_panel");
		$(financialYearSelection).shouldBe(visible);
		$(financialYearSelection).find(".ui-selectcheckboxmenu-header .ui-chkbox-box.ui-corner-all").shouldBe(visible);
	}

	private void verifyAndClickOnFinancialYearDropdown() {
		var financialYearDropdown = By.id(DELETE_FORM + "financial-year");
		$(financialYearDropdown).shouldBe(visible);
		$(financialYearDropdown).click();
	}

	private void startFirstTaskOfDataDeletionProcess() {
		// Find the first task of process and redirect user to task UI
		open(EngineUrl.createProcessUrl(TEST_PROCESS_ID + "start.ivp"));
	}

	private void cleanUpdata() {
		open(EngineUrl.createProcessUrl(TEST_PROCESS_ID + "cleanData.ivp"));
	}
}