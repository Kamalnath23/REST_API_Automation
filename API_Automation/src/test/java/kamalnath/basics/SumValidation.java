package kamalnath.basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import kamalnath.files.Payload;
import kamalnath.files.ReusableMethod;

public class SumValidation {

	
		// //Verify if Sum of all Course prices matches with Purchase Amount
		
		@Test
		public void sumOfCourse() {
			JsonPath js= ReusableMethod.rawToJSON(Payload.coursePrice()); 
			int sum=0;
			int count= js.getInt("courses.size()");
			for(int i=0; i<count;i++) {
				String counrseName= js.get("courses["+i+"].title");
				int copies= js.getInt("courses["+i+"].copies");
				int coursePrice= js.getInt("courses["+i+"].price");
				int amount = coursePrice*copies;
				System.out.println(amount);
				sum+=amount;
			}
			System.out.println("total sum : "+sum);
			int resultantAmountInDashboard= js.getInt("dashboard.purchaseAmount");
			Assert.assertEquals(resultantAmountInDashboard, sum);
		}
	

}
