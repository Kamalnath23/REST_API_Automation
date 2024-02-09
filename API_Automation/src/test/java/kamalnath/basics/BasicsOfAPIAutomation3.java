package kamalnath.basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import kamalnath.files.Payload;

public class BasicsOfAPIAutomation3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Validate POST API
		//given -- all input details, 
		//when -- submit the API, 
		//then -- Validate the reponses
		
		//given method
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//ADD PLACE --> UPDATE PLACE WITH NEW ADDRESS --> Get place to validate if new address is present in the response.
		
		String response = given().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response); //for parsing json
		String placeID = js.getString("place_id");
		System.out.println(placeID);
		
		//update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeID+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//two ways
	}

}
