package Kamal_And_RSA.EcommerceApp;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import pojo_Login.LoginRequest;
import pojo_Login.LoginResponse;
import pojo_Orders.OrderDetails;
import pojo_Orders.Orders;
import pojo_Orders.OrdersResponse;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

public class ECommerceAPITest {
	public static void main(String[] args) {
		//Define SpecBuilder object using RequestSpecBuilder class for all API requests(Calls)
		RequestSpecification BasicRequestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").setContentType(ContentType.JSON).build();
		
		//Login API Call
		LoginRequest KamalloginRequest = new LoginRequest();
		KamalloginRequest.setUserEmail("kamalnath121998@gmail.com");
		KamalloginRequest.setUserPassword("Kamal98*");
		
		RequestSpecification RequestSpecLogin= given().spec(BasicRequestSpecification).body(KamalloginRequest);
		//String LoginResponse = RequestSpecLogin.when().post("api/ecom/auth/login").then().log().all().extract().response().asString();
		LoginResponse loginResponse = RequestSpecLogin.when().post("api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
		System.out.println("Token is: "+loginResponse.getToken());
		System.out.println("UserID is: "+loginResponse.getUserId());
		System.out.println("Message is: "+loginResponse.getMessage());
		
		//Add Product API Call
		RequestSpecification AddProductBasicRequestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", loginResponse.getToken()).build();
		RequestSpecification RequestSpecAddProduct = given().spec(AddProductBasicRequestSpecification)
		.params("productName", "Laptop", 
				"productAddedBy", loginResponse.getUserId(), 
				"productCategory", "fashion", 
				"productSubCategory", "shirts", 
				"productPrice", "11500", 
				"productDescription", "Lenovo Yoga 3 laptop with 8th Gen Intel Core i5 processor",
				"productFor","men")
		.multiPart("productImage",new java.io.File("D:\\API Postman Testing\\Codes\\PostmanWorkSpace1\\EcommerceApp\\qwerty.jpg"));
		
		String addProductResponse = RequestSpecAddProduct.when().post("api/ecom/product/add-product").
				then().extract().response().asString();
		JsonPath js= utility.ReusableMethod.rawToJSON(addProductResponse); 
		String ProdctId = js.getString("productId");
		System.out.println("Product ID is: "+ProdctId);
		
		//Create Order API Call
		RequestSpecification CreateOrderBasicRequestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", loginResponse.getToken()).setContentType(ContentType.JSON).build();
		
		//2. first class object
		OrderDetails orderDetail1 = new OrderDetails();
		orderDetail1.setCountry("India");
		orderDetail1.setProductOrderedId(ProdctId);
		//1. create a list of first class object
		List<OrderDetails> orderDetailsList = new ArrayList<OrderDetails>();
		//3. add first class object to list
		orderDetailsList.add(orderDetail1);
		//4. second class object
		Orders o1= new Orders();
		o1.setOrders(orderDetailsList);
		
		RequestSpecification RequestSpecOrders =given().spec(CreateOrderBasicRequestSpecification).body(o1);
		OrdersResponse order1response =RequestSpecOrders.when().post("api/ecom/order/create-order")
		.then().log().all().extract().response().as(OrdersResponse.class);
		System.out.println("Order Response Message: "+order1response.getMessage());
		System.out.println("Ordered Product ID: "+order1response.getProductOrderId().get(0));
		System.out.println("Ordered order Name: "+order1response.getOrders().get(0));
		
		//Delete Product API Call
		RequestSpecification DeleteProductBasicRequestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addHeader("Authorization", loginResponse.getToken()).setContentType(ContentType.JSON).build();
		
		RequestSpecification RequestSpecDelete = given().spec(DeleteProductBasicRequestSpecification).pathParam("productID", ProdctId);
		String DeletedMessage = RequestSpecDelete.when().delete("api/ecom/product/delete-product/{productID}").then().extract().response().asString();
		JsonPath js1= utility.ReusableMethod.rawToJSON(DeletedMessage);
		System.out.println("Deleted Product Message: "+js1.getString("message"));
		System.out.println("End of ECommerce API Test");
	}
}
