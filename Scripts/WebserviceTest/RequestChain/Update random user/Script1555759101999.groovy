import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.ahmedsoft.utilities.DataFactory as DataFactory
import com.ahmedsoft.utilities.User as User

DataFactory faker = new DataFactory()
def response = WS.sendRequest(findTestObject('Object Repository/API/ChainRequests/SelectAllUsers'))

User[] users = faker.jsonifyUsers(response.getResponseText(), User[].class)
int randomIndex = faker.generateRandomNumberBetween(0, users.length)
User selectedUser = users[randomIndex]

String id = selectedUser.getId()
User updatedUser = faker.modifyUser(selectedUser)

GlobalVariable.G_Name = updatedUser.getName()
GlobalVariable.G_Age = updatedUser.getAge()
GlobalVariable.G_City = updatedUser.getCity()

userAfterUpdate = WS.sendRequestAndVerify(findTestObject('API/ChainRequests/UpdateUser', [('url') : GlobalVariable.API_URL
            , ('id') : updatedUser.getId(), ('name') : updatedUser.getName(), ('age') : updatedUser.getAge(), ('city') : updatedUser.getCity()]))
