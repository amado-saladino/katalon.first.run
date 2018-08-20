package com.ahmedsoft.utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.github.javafaker.Faker
import com.google.gson.Gson
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
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

public class DataFactory {

	Faker faker
	Gson gson

	public DataFactory(){

		faker = new Faker()
		gson = new Gson()
	}

	def randomNumber() {

		return faker.number.numberBetween(1, 10000).toString()
	}


	def randomName(){

		return faker.name.firstName()
	}


	def randomCity(){

		return faker.address.city()
	}

	def randomAge(){

		return faker.number.numberBetween(12, 60).toString()
	}


	def generateUser(){

		String body = '{"id": "' + randomNumber() + '","name": "' + randomName() + '","Age": "' + randomAge() + '","city": "' + randomCity() +'"}'
		return body
	}


	def public User createUser() {
		return new User(randomNumber(), randomName(), randomAge(), randomCity())
	}


	def public String createStringifiedUser(){

		return gson.toJson(createUser())
	}


	def public String createStringifiedUser(User user) {

		return gson.toJson(user)
	}


	def public User modifyUser(User user) {

		return new User(user.getId(), randomName(), randomAge(), randomCity())
	}


	def public User jsonifyUser(String stringJson,Class type)  {

		return gson.fromJson(stringJson, type)
	}



	def public User[] jsonifyUsers(String stringJson,Class type) {

		return gson.fromJson(stringJson, type)
	}
	
	
	def public int getUserCount() {
		
		def request = (RequestObject) findTestObject('Object Repository/API/GetUsers')
		return jsonifyUsers(WS.sendRequest(request).getResponseText(), User[].class).length 
	}


	def public User getLastUser(){

		def usersRequest = (RequestObject) findTestObject('Object Repository/API/GetUsers')
		def usersResponse = WS.sendRequest(usersRequest)
		User[] users = jsonifyUsers(usersResponse.getResponseText(), User[].class)
		println users.length
		return users[users.length -1]
	}
}
