package kamalnath.files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJSON {
	
	@Test(dataProvider="BooksData")
	public void addBook(String ISBN, String Aisle) {
		RestAssured.baseURI= "http://216.10.245.166";
		String response = given().header("Content-Type","application/json")
		.body(Payload.AddBook(ISBN,Aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		JsonPath js= ReusableMethod.rawToJSON(response);
		String Id= js.get("ID");
		System.out.println(Id);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		return new Object[][] {
			{"ofjjw", "134"}, {"xzc","8765"}, {"oplk","2341"}
		};
	}
}
