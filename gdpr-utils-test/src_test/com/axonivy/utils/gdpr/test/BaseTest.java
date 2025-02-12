package com.axonivy.utils.gdpr.test;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.By;

import com.axonivy.ivy.webtest.engine.EngineUrl;
import com.codeborne.selenide.SelenideElement;

public class BaseTest {

	protected static final String DELETE_FORM = "deletion-form:";
	protected static final String TEST_PROCESS_ID = "gdpr-utils-test/194EF1C2639450F0/";
	protected static final String MANUAL_TRIGGER_PROCESS = "gdpr-utils/1943EA22591E28D4/startDataDeletion.ivp";

	public void startDataDeletionProcess() {
		// Manual start Data deletion process
		open(EngineUrl.createProcessUrl(MANUAL_TRIGGER_PROCESS));
	}

	protected void startFirstTaskOfDataDeletionProcess() {
		// Find the first task of process and redirect user to task UI
		open(EngineUrl.createProcessUrl(TEST_PROCESS_ID + "start.ivp"));
	}

	protected SelenideElement verifyAndGetFinancialYearSelectionPanel() {
		var financialYearSelection = By.id(DELETE_FORM + "financial-year_panel");
		$(financialYearSelection).shouldBe(visible);
		var checkAllOption = $(financialYearSelection)
				.find(".ui-selectcheckboxmenu-header .ui-chkbox-box.ui-corner-all");
		return checkAllOption.shouldBe(visible);
	}

	protected SelenideElement verifyAndGetFirstFinOption() {
		var firstOption = $(".ui-selectcheckboxmenu-items-wrapper").findAll("li.ui-selectcheckboxmenu-item").first();
		firstOption.shouldBe(enabled);
		return firstOption;
	}

	protected SelenideElement verifyAndGetFinancialYearDropdown() {
		var financialYearDropdown = By.id(DELETE_FORM + "financial-year");
		return $(financialYearDropdown).shouldBe(visible);
	}

	protected SelenideElement verifyAndGetDeleteButton() {
		var confirmDelete = By.id("confirm-data-deletion-dialog");
		$(confirmDelete).shouldBe(visible);
		return $(confirmDelete).find(By.id("delete-btn"));
	}

	protected SelenideElement verifyAndGetSubmitButton() {
		return $(By.id(DELETE_FORM + "submit")).shouldBe(enabled);
	}

	protected SelenideElement verifyDeletingMessageGroup() {
		return $(By.id(DELETE_FORM + "data-deleting-message-group")).should(visible);
	}

	protected SelenideElement verifyAndGetFinishTaskButton() {
		return $(By.id(DELETE_FORM + "finish-task")).should(visible, enabled);
	}
}
