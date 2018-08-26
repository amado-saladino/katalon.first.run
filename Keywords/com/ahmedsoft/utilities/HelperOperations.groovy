package com.ahmedsoft.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import javax.imageio.ImageIO

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By.ById
import org.openqa.selenium.remote.server.DriverFactory

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

import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
import MobileBuiltInKeywords as Mobile
import WebUiBuiltInKeywords as WebUI

public class HelperOperations {

	WebDriver driver

	public HelperOperations() {

		driver = com.kms.katalon.core.webui.driver.DriverFactory.getWebDriver()
	}


	public def takeSnapshot(TestObject object,String fileName)  {

		WebElement element = WebUiCommonHelper.findWebElement(object, 15)
		Screenshot screenShot = new AShot().takeScreenshot(driver, element)
		ImageIO.write(screenShot.getImage(), "PNG", new File("Screenshots/" + fileName + ".png"))
	}


	public def takeSnapshot() {

		Screenshot screenShot = new AShot().takeScreenshot(driver)
		ImageIO.write(screenShot.getImage(), "PNG", new File("Screenshots/" + getTime() + ".png"))
	}


	public String getTime() {

		return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	}
}
