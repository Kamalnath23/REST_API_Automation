package kamalnath.SerialisationTest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class SerialisationTest {

	public static void main(String[] args) {
		AddPlace P1 = new AddPlace();
		P1.setAccuracy(50);
		P1.setAddress("29, side layout, cohen 09");
		P1.setLanguage("French-IN");
		P1.setName("Frontline house");
		P1.setPhone_number("(+91) 983 893 3937");
		P1.setWebsite("http://google.com");
		
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		P1.setTypes(types);
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		P1.setLocation(loc);
		
		
		
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().queryParam("key","qaclick123")
		//.body(GoogleMapPayload.GoogleMapsAddPlacePayload())
		.body(P1)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("Response is: "+response);
		

	}

}
