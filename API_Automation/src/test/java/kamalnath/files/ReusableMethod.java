package kamalnath.files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {
	public static JsonPath rawToJSON(String response) {
		JsonPath js= new JsonPath(response);
		return js;
	}
}
