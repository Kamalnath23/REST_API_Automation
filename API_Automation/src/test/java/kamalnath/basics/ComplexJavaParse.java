package kamalnath.basics;

import io.restassured.path.json.JsonPath;
import kamalnath.files.Payload;
import kamalnath.files.ReusableMethod;

public class ComplexJavaParse {
	public static void main(String []args) {
		JsonPath js= ReusableMethod.rawToJSON(Payload.coursePrice()); 
		//JsonPath js1 = new JsonPath(Payload.coursePrice());
		
		// Print No of courses returned by API
		int count= js.getInt("courses.size()");
		System.out.println(count);
		
		//Print purchase amount
		int totlaAmt= js.getInt("dashboard.purchaseAmount");
		System.out.println(totlaAmt);
		
		//Print Title of the first course
		String titleFirstCourse = js.get("courses[0].title"); //get method by def return getString
		System.out.println(titleFirstCourse);
		
		// Print All course titles and their respective Prices
		for(int i=0; i<count;i++) {
			String counrseName= js.get("courses["+i+"].title");
			int coursePrice= js.getInt("courses["+i+"].price");
			System.out.println("counrseName: "+counrseName+" - Price: "+coursePrice);
		}
		
		// Print no of copies sold by RPA Course
		System.out.println("Print no of copies sold by RPA Course");
		
		// Verify if Sum of all Course prices matches with Purchase Amount
		for(int i=0; i<count;i++) {
			String counrseName= js.get("courses["+i+"].title");
			if(counrseName.equalsIgnoreCase("RPA")) {
				int copies= js.getInt("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		
		
	}
}



