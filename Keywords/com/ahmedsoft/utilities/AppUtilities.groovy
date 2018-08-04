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
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class AppUtilities {

	/*
	 * login with username and password
	 * @param username
	 * @param password
	 */
	@Keyword
	def login(String username,String password){

		WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Username'), "John Doe")
		WebUI.setText(findTestObject('Object Repository/LoginPage/txt_Password'), "ThisIsNotAPassword")
		WebUI.click(findTestObject('Object Repository/LoginPage/LoginButton'))
	}

	/*
	 * Checks if the user is logged in
	 * @return true if the user is logged in
	 */
	@Keyword
	def isLoggedIn(){

		return WebUI.verifyElementVisible(findTestObject('Object Repository/SiteMenu/LinkProfile'))	&& WebUI.verifyElementVisible(findTestObject('Object Repository/SiteMenu/LinkLogout'))
	}


	@Keyword
	def confirmationSuccess(){

		return WebUI.verifyElementVisible(findTestObject('Object Repository/Confirmation/btn_Go to Homepage')) &&
				findTestObject('Object Repository/Confirmation/h2_Appointment Confirmation') &&
				findTestObject('Object Repository/Confirmation/p_please_information')
	}

	
}