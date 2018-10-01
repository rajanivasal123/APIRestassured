import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static org.hamcrest.Matchers.lessThan;

public class GetData123 {
	
	@Test
	public void testResponsebody()
	{
		Response res= get("https://api.github.com/users/octocat");
		get("https://api.github.com/users/octocat").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        time(lessThan(3000L));
		
		System.out.println("Response data is :"+res.asString());
		System.out.println("Response Time in ms :"+ res.getTime());
		
		ResponseBody body = res.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.toLowerCase().contains("site_admin"), true);	
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = res.jsonPath();
		int id = jsonPathEvaluator.get("id");	 		
		System.out.println("User ID received from Response " + id);	 
		Assert.assertEquals(id, 583231);		
	}
	
}
