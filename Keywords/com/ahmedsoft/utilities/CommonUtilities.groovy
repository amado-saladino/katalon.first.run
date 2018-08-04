package com.ahmedsoft.utilities
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class CommonUtilities {
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}


	/*
	 * Select Dropdown item that contains a text
	 * @param Dropdown menu object
	 * @param sub text contained within the menu items
	 */
	@Keyword
	def selectDropDownItemByText(TestObject testObject,String subText) {

		WebElement element = WebUiCommonHelper.findWebElement(testObject, 30)
		Select dropdownList=new Select(element)

		for (WebElement option : dropdownList.getOptions()){
			if (option.getText().toLowerCase().contains(subText.toLowerCase())) {
				dropdownList.selectByVisibleText(option.getText())
				break
			}
		}
	}

	/*
	 * select radio button by value
	 * @param value represented by the radio button
	 * @return Radio button object
	 */
	@Keyword
	def gettRadioButtonByValue(String value){

		TestObject radioButton= new TestObject("RadioButtonObject")
		radioButton.addProperty("xpath",ConditionType.EQUALS,"//input[@name = 'programs' and @type = 'radio' and @value='" + value + "']")
		return radioButton
	}


	/*
	 * @param label for check box
	 * @return check box containing the label 
	 */
	@Keyword
	def getCheckboxByLabel(String label){

		TestObject checkbox = new TestObject("LabelForCheckboxObject")
		checkbox.addProperty("xpath",ConditionType.EQUALS,"//label[text()[contains(.,'" + label + "')]]//input[@type='checkbox']")
		return checkbox
	}


	/*
	 * Select radio button by label
	 * @param label or part of label for the radio button
	 * @return Radio button containing the label
	 */
	@Keyword
	def getRadiobuttonByLabel(String label){

		TestObject radioButton = new TestObject("LabelForRadiobuttonObject")
		radioButton.addProperty("xpath",ConditionType.EQUALS,"//label[text()[contains(.,'" + label + "')]]//input[@type='radio']")
		return radioButton
	}


	/*
	 * Get appointment info at a specific row
	 * @param appointment item
	 * @return Object representing the row containing the piece of appointment info
	 */
	@Keyword
	def getConfirmationItem(String item){

		List<TestObjectProperty> properties=new ArrayList<TestObjectProperty>()
		properties.add(new TestObjectProperty("tag",ConditionType.EQUALS,"p"))
		properties.add(new TestObjectProperty("xpath",ConditionType.CONTAINS,"//div[@class='col-xs-offset-2 col-xs-8' and contains(.,'" + item +  "')]"))

		TestObject info=new TestObject("info")
		info.setProperties(properties)

		return info
	}
}