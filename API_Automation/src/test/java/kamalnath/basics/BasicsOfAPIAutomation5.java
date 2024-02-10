package kamalnath.basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import kamalnath.files.Payload;
import kamalnath.files.ReusableMethod;

public class BasicsOfAPIAutomation5 {

	public static void main(String[] args) throws IOException {
		//Validate POST API
		//given -- all input details, 
		//when -- submit the API, 
		//then -- Validate the reponses
		//convert content of the file to String. Content of file-> bytes --> byte data to String.
		//given method
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//ADD PLACE --> UPDATE PLACE WITH NEW ADDRESS --> Get place to validate if new address is present in the response.
		
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String (Files.readAllBytes(Paths.get("D:\\API Postman Testing\\Codes\\PostmanWorkSpace1\\API_Automation\\staticJSONFile.json"))))
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response); //for parsing json
		String placeID = js.getString("place_id");
		System.out.println(placeID);
		
		//update place
		String newAddress= "20 London, England";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//GET
		String getResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js2= ReusableMethod.rawToJSON(getResponse); //new JsonPath(getResponse);
		String getAddress = js2.getString("address");
		System.out.println(getAddress);
		
		Assert.assertEquals(newAddress, getAddress);

	}

}
