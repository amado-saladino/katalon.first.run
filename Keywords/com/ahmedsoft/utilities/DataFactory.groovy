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
	private Random random

	public DataFactory(){
		faker = new Faker()
		gson = new Gson()
		random = new Random()
	}

	def randomNumber() {
		faker.number.numberBetween(1, 10000).toString()
	}

	int generateRandomNumberBetween(min, max) {
		random.nextInt(max) + min
	}

	def randomName(){
		faker.name.firstName()
	}

	def randomCity(){
		faker.address.city()
	}

	def randomAge(){
		faker.number.numberBetween(12, 60).toString()
	}

	def generateUser(){
		'{"id": "' + randomNumber() + '","name": "' + randomName() + '","Age": "' + randomAge() + '","city": "' + randomCity() +'"}'
	}

	def User createUser() {
		new User(randomNumber(), randomName(), randomAge(), randomCity())
	}

	def String createStringifiedUser(){
		gson.toJson(createUser())
	}

	def String createStringifiedUser(User user) {
		gson.toJson(user)
	}

	def User modifyUser(User user) {
		new User(user.getId(), randomName(), randomAge(), randomCity())
	}

	def User jsonifyUser(String stringJson,Class type)  {
		gson.fromJson(stringJson, type)
	}

	def User[] jsonifyUsers(String stringJson,Class type) {
		gson.fromJson(stringJson, type)
	}

	def int getUserCount() {
		def request = (RequestObject) findTestObject('Object Repository/API/GetUsers')
		jsonifyUsers(WS.sendRequest(request).getResponseText(), User[].class).length
	}

	def User getLastUser(){
		def usersRequest = (RequestObject) findTestObject('Object Repository/API/GetUsers')
		def usersResponse = WS.sendRequest(usersRequest)
		User[] users = jsonifyUsers(usersResponse.getResponseText(), User[].class)
		println users.length
		users[users.length -1]
	}
}
