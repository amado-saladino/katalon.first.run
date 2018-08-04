import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.click(findTestObject('Object Repository/SiteMenu/ToggleMenu'))

WebUI.verifyEqual(CustomKeywords.'com.ahmedsoft.utilities.AppUtilities.isLoggedIn'(), true)

WebUI.click(findTestObject('Object Repository/SiteMenu/ToggleMenu'))

for (def row = 1; row <= findTestData('AppointmentsData').getRowNumbers(); row++) {
    WebUI.verifyElementVisible(findTestObject('Object Repository/AppointmentPage/Header_Make Appointment'))

    //Select Dropdown 'Facility'
    CustomKeywords.'com.ahmedsoft.utilities.CommonUtilities.selectDropDownItemByText'(findTestObject('Object Repository/AppointmentPage/select_Facility'), 
       findTestData("AppointmentsData").getValue("Facility", row))

    //Checkbox 'Apply for hospital readmission'
    WebUI.click(CustomKeywords.'com.ahmedsoft.utilities.CommonUtilities.getCheckboxByLabel'('Apply for'))

    //Select Radiobutton 'Program'
    WebUI.click(CustomKeywords.'com.ahmedsoft.utilities.CommonUtilities.getRadiobuttonByLabel'(findTestData("AppointmentsData").getValue("Program", row)))

	//Date
    WebUI.setText(findTestObject('Object Repository/AppointmentPage/txt_visit_date'), findTestData("AppointmentsData").getValue("Date", row))

    WebUI.setText(findTestObject('Object Repository/AppointmentPage/textarea_comment'),findTestData("AppointmentsData").getValue("Comment", row))

    //'Click' book
    WebUI.click(findTestObject('Object Repository/AppointmentPage/btn_Book Appointment'))

    //verify confirmation
    CustomKeywords.'com.ahmedsoft.utilities.AppUtilities.confirmationSuccess'()

    //Verify infos
    WebUI.verifyElementVisible(CustomKeywords.'com.ahmedsoft.utilities.CommonUtilities.getConfirmationItem'(findTestData("AppointmentsData").getValue("Facility", row)))

    WebUI.click(findTestObject('Object Repository/AppointmentPage/a_Make Appointment'))

    WebUI.delay(5)
}