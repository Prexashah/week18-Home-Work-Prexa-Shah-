package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class  StoreAssertionTest extends TestBase {
    static ValidatableResponse response;
 String stores = PropertyReader.getInstance().getProperty("resourceStore");
    @BeforeClass
    public  void getStores(){
        response = given()
                .when()
                .get(stores)
                .then().statusCode(200);
    }
    //Verify  if the total is equal to 1561
@Test
public void verifyTheTotal(){
        response.body("total",equalTo(1561));

}
//Verify the if the stores of limit is equal to 10
@Test
public void verifyStoreLimit(){
        response.body("limit", equalTo(10));
}
//Check the single ‘Name’ in the Array list (Inver Grove Heights)
@Test
public void verifyName(){
        response.body("data.name",hasItem("Inver Grove Heights"));
}
//Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
@Test
public void verifyMultipleName(){
response.body("data.name",hasItems("Roseville","Burnsville","Maplewood"));
}
//Verify the storied=7 inside storeservices of the third store of second services
@Test
public void verifyStoreServices(){
response.body("data[2].services[1].storeservices.storeId", equalTo(7));
    }

    //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
    @Test
    public void verifyHashMapValueForStore(){
        response.body("data.find { it.name == 'Roseville' }.services[0].storeservices.map.createdAt",nullValue());
    }

//7. Verify the state = MN of forth store
    @Test
    public void verifyStateOfStore(){
        response.body("data[0].state",equalTo("MN"));

    }
    //8. Verify the store name = Rochester of 9th store
    @Test
    public void verifyStoreNameIsRochester(){
        response.body("data[8].name",equalTo("Rochester"));
    }
    //9. Verify the storeId = 11 for the 6th store
    @Test
    public void verifyStoreID11(){
        response.body("data[5].id",equalTo(11));
    }
    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyServiceID4(){
        response.body("data[6].services[3].storeservices.serviceId",equalTo(4));
    }

}
