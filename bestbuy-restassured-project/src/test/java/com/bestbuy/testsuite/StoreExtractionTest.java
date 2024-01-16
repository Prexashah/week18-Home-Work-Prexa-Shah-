package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest extends TestBase {
    static ValidatableResponse response;
    String stores = PropertyReader.getInstance().getProperty("resourceStore");

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get(stores)
                .then().statusCode(200);
    }

    //1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void extractNameOf5ThStore() {
        String fifthStoreName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of fiththStore is : " + fifthStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void extractAllStoreNames() {
        List<String> allStoreNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of AllStoreName is : " + allStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void extractAllStoreId() {
        List<Integer> allStoreId = response.extract().path("data.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of allStoreId is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void extractSize() {
        int dataSize = response.extract().path("data.size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Data Size is : " + dataSize);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void extractAllStoreNameSt_Cloud() {
        List<HashMap<String, ?>> storeDetailOfStCloud = response.extract().path("data.findAll{ it.name == 'St Cloud' }");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of All Store Detail of St Cloud is : " + storeDetailOfStCloud);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void getAddressOfStoreNameRochester() {
        List<HashMap<String, ?>> storeDetailOfStCloud = response.extract().path("data.findAll{ it.name == 'Rochester' }");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of All the Store Name 'Rochester' is : " + storeDetailOfStCloud);
        System.out.println("------------------End of Test---------------------------");
    }
    //9. Get all the services of 8th store

    @Test
    public void getAllTheServicesOn8thStore() {
        List<HashMap<String, ?>> servicesOf8ThStore = response.extract().path("data[7].services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of All all the services in 8th store is : " + servicesOf8ThStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void getStoreServicesNameWindowsStore() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Store services name 'Windows Store' are : " + storeServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void getAllStoreId() {
        List<HashMap<String, ?>> allStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Store Services  are : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //12. Get id of all the store
    @Test
    public void GetIdOfAllTheStores() {
        List<HashMap<String, ?>> allIds = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Ids : " + allIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //13. Find the store names Where state = ND
    @Test
    public void GetStoreNameWhereStateND() {
        List<HashMap<String, ?>> allIds = response.extract().path("data.findAll{ it.state == 'ND' }");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value store names where state is 'ND' are  : " + allIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void getNumberOfServicesNameRochester() {
        int allServices = response.extract().path("data.findAll{ it.name == 'Rochester' }.services.size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all the services name as Rochester : " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void getCreateAtServicesNameWindowsStore() {
        List<HashMap<String, ?>> createdAtForWindowsStoreService = response.extract().path("data.services.findAll{ it.name == 'Windows Store' }.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all all services name “Windows Store” are : " + createdAtForWindowsStoreService);
        System.out.println("------------------End of Test---------------------------");
    }

    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void getListOFServicesNameFargo() {
        List<String> allServicesName = response.extract().path("data.findAll{it.name == 'Fargo'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all All services name“Fargo” are : " + allServicesName);
        System.out.println("------------------End of Test---------------------------");
    }

    // 17. Find the zip of all the store
    @Test
    public void getAllZipCodes() {
        List<String> allZipCodes = response.extract().path("data.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Zip Codes are : " + allZipCodes);
        System.out.println("------------------End of Test---------------------------");
    }

    // 18. Find the zip of store name = Roseville
    @Test
    public void findZipForStoreNameRoseville() {
        String roseVilleZip = response.extract().path("data.find { it.name == 'Roseville' }.zip");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of 'Roseville'zip store name is : " + roseVilleZip);
        System.out.println("------------------End of Test---------------------------");
    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void getDetailOfStoreServicesMagnoliaHomeTheater() {
        List<String> storeservicesForMagnoliaHomeTheater = response.extract().path("data.findAll { store -> store.services.any { service -> service.name == 'Magnolia Home Theater' } }.services.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of store service for 'Magnolia Home Theater' : " + storeservicesForMagnoliaHomeTheater);
        System.out.println("------------------End of Test---------------------------");
    }

    // 20. Find the lat of all the stores
    @Test
    public void getLatitudes() {
        List<String> allStoreLatitudes = response.extract().path("data.lat");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all store lat is' : " + allStoreLatitudes);
        System.out.println("------------------End of Test---------------------------");
    }


}













