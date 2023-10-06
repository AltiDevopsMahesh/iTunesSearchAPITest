package com.searchAPI;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ITunesSearchAPIStepDefinitions {

	private Response response;

	@Given("the base URL is {string}")
	public void setBaseURL(String baseUrl) {
		// Set the base URI for RestAssured
		RestAssured.baseURI = baseUrl;
	}

	@When("a GET request is made to {string} with the following parameters:")
	public void sendGetRequest(String endpoint, Map<String, String> params) {
		try {
			// Send a GET request with query parameters
			response = given().queryParams(params).when().get(endpoint);
		} catch (Exception e) {
			// Handle exceptions, log errors, or fail the test
			throw new RuntimeException("Failed to send GET request: " + e.getMessage());
		}
	}

	@Then("the response status code should be {int}")
	public void verifyStatusCode(int expectedStatusCode) {
		try {
			// Verify the response status code
			assertEquals(expectedStatusCode, response.getStatusCode());
		} catch (AssertionError e) {
			// Handle assertion failures, log errors, or fail the test
			throw new AssertionError(
					"Expected status code: " + expectedStatusCode + " but received: " + response.getStatusCode());
		}
	}

	@Then("the response should contain the text {string}")
	public void verifyResponseContains(String expectedContent) {
		try {
			// Verify that the response body contains the expected content
			String responseBody = response.getBody().asString();
			assertTrue(responseBody.contains(expectedContent));
		} catch (AssertionError e) {
			// Handle assertion failures, log errors, or fail the test
			throw new AssertionError("Expected response to contain: " + expectedContent);
		}
	}
}
