package kamalnath.OAuthAutomation;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import kamalnath.PojoPackage.*;

public class OAuth_GetCourse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Step 1 : Authorization Call and get access token
				RestAssured.baseURI="https://rahulshettyacademy.com";
				String AuthorizationTokenResponse = given()
				 .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com",
						 "client_secret","erZOWM9g3UtwNRj340YYaK_W",
						 "grant_type","client_credentials",
						 "scope","trust")
				 .when().post("/oauthapi/oauth2/resourceOwner/token")
				 .then().assertThat().statusCode(200)
				 .extract().response().asString();
				
				JsonPath js = new JsonPath(AuthorizationTokenResponse); //for parsing json
				String access_token = js.getString("access_token");
				System.out.println("Access token: "+access_token);
				
				//Step 2 : Use the generated token and get the details in GET API
				//Instead of String save as Java Object
				GetCourse GetCoursePrice = given().queryParam("access_token", access_token)
						.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
						.as(GetCourse.class);
				//System.out.println(GetCoursePrice);
				System.out.println("Linkedin url: "+GetCoursePrice.getLinkedIn());
				System.out.println("Trainer : "+GetCoursePrice.getInstructor());
				
				//Get no.of.Course title in api
				System.out.println(GetCoursePrice.getCourses().getApi().size());
				
				//print 1st and second course title with price
				System.out.println(GetCoursePrice.getCourses().getApi().get(0).getCourseTitle());
				System.out.println(GetCoursePrice.getCourses().getApi().get(0).getPrice());
				
				System.out.println(GetCoursePrice.getCourses().getApi().get(1).getCourseTitle());
				System.out.println(GetCoursePrice.getCourses().getApi().get(1).getPrice());
				
				//Dynamically fetch values
				System.out.println("API");
				List<Api> API_Courses = GetCoursePrice.getCourses().getApi();
				for(int i =0; i<API_Courses.size();i++) {
					System.out.println("Course: "+API_Courses.get(i).getCourseTitle()+"Price: "+API_Courses.get(i).getPrice());
				}
				System.out.println("WEB AUTOMATION");
				List<WebAutomation> WebAutomation_Courses = GetCoursePrice.getCourses().getWebAutomation();
				for(int i =0; i<WebAutomation_Courses.size();i++) {
					System.out.println("Course: "+WebAutomation_Courses.get(i).getCourseTitle()+"Price: "+WebAutomation_Courses.get(i).getPrice());
				}
				System.out.println("MOBILE");
				List<Mobile> API_Mobile = GetCoursePrice.getCourses().getMobile();
				for(int i =0; i<API_Mobile.size();i++) {
					System.out.println("Course: "+API_Mobile.get(i).getCourseTitle()+"Price: "+API_Mobile.get(i).getPrice());
				}
				
				//PRINT PRICE OF SoapUI Webservices testingPrice
				List<Api> API_Courses2 = GetCoursePrice.getCourses().getApi();
				for(int i =0; i<API_Courses2.size();i++) {
					if(API_Courses2.get(i).getCourseTitle().equals("SoapUI Webservices testing")) {
						System.out.println("Course: "+API_Courses2.get(i).getCourseTitle()+" and its Price: "+API_Courses2.get(i).getPrice());
						break;
					}
					
				}
				
				//GET COURSE NAME OF WEB AUTOMATION
				ArrayList<String> CourseNamesOfWebAutomation = new ArrayList<String>();
				for(int i =0; i<WebAutomation_Courses.size();i++) {
					CourseNamesOfWebAutomation.add(WebAutomation_Courses.get(i).getCourseTitle());
				}
				System.out.println("CourseNamesOfWebAutomation: "+CourseNamesOfWebAutomation.toString());
				List<String> expectedArrayList = new ArrayList<String>(Arrays.asList( "Selenium Webdriver Java", "Cypress", "Protractor"));
				Assert.assertEquals(CourseNamesOfWebAutomation, expectedArrayList);
				
	}

}
