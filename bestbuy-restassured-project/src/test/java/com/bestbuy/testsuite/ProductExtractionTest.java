package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.PropertyReader;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductExtractionTest extends TestBase {
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

    //21. Extract the limit
    @Test
    public void test21() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //22. Extract the total
    @Test
    public void test22() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //23. Extract the name of 5th product
    @Test
    public void test23() {
        String fifthProducteName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of fiththProduct is : " + fifthProducteName);
        System.out.println("------------------End of Test---------------------------");
    }

    //24. Extract the names of all the products
    @Test
    public void test24() {
        List<String> allProductNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of AllProduct is : " + allProductNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //25. Extract the productId of all the products
    @Test
    public void test25() {
        List<Integer> allProductId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of allStoreId is : " + allProductId);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test26() {
        int dataSize = response.extract().path("data.size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Data Size is : " + dataSize);
        System.out.println("------------------End of Test---------------------------");

    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-
    //Pack)
    @Test
    public void test27() {
        List<String> productDetailsForEnergizer = response.extract().path("data.find { it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of product name are :" + productDetailsForEnergizer);
        System.out.println("------------------End of Test---------------------------");


    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-
    //Pack)
    @Test
    public void test28() {
        String modelForEnergizerNCell = response.extract().path("data.find { it.name == 'Energizer - N Cell E90 Batteries (2-Pack)' }.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of product name are :" + modelForEnergizerNCell);
        System.out.println("------------------End of Test---------------------------");

    }

    //29. Get all the categories of 8th products
    @Test
    public void test29() {
        List<String> categoriesOf8thProduct = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value Categories of 8th products  :" + categoriesOf8thProduct);
        System.out.println("------------------End of Test---------------------------");

    }

    //30. Get categories of the store where product id = 150115
    @Test
    public void test30() {
        List<String> categoriesOfProduct150115 = response.extract().path("data.find { it.id == 150115 }.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the categories of product 150115  :" + categoriesOfProduct150115);
        System.out.println("------------------End of Test---------------------------");

    }

    //31. Get all the descriptions of all the product
    @Test
    public void test31() {
        List<String> allProductDescriptions = response.extract().path("");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All products descriptions are  :" + allProductDescriptions);
        System.out.println("------------------End of Test---------------------------");
    }

    //32.Get id of all the all categories of all the products
    @Test
    public void test32() {
        List<String> idsOfAllCategories = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Categories Ids  :" + idsOfAllCategories);
        System.out.println("------------------End of Test---------------------------");
    }

    //33.Find the product names Where type = HardGood
    @Test
    public void test33() {
        List<String> productNamesWithTypeHardGood = response.extract().path("data.findAll { it.type == 'HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Product name with type HardGood are  :" + productNamesWithTypeHardGood);
        System.out.println("------------------End of Test---------------------------");

    }

    //34.Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test34() {
        List<String> allCategories = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("Number of categories for Duracell - AA 1.5V CopperTop Batteries (4-Pack) is: " + (allCategories.size()));
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Total Categories for Duracell - AA 1.5V CopperTop Batteries (4-Pack) :" + allCategories);
        System.out.println("------------------End of Test---------------------------");

    }

    //35. Find the createdAt for all products whose price < 5.49
    @Test
    public void test35() {
        List<String> createdAtForProductsUnder549 = response.extract().path("data.findAll{ it.price < 5.49 }.createdA");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" CreatedAt for Products with Price < 5.49 are :" + createdAtForProductsUnder549);
        System.out.println("------------------End of Test---------------------------");

    }

    @Test
    public void test36() {
        List<String> categoriesForEnergizerMAX = response.extract().path("data.find { it.name == 'Energizer - MAX Batteries AA (4-Pack)' }.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Categories for Energizer - MAX Batteries AA (4-Pack):" + categoriesForEnergizerMAX);
        System.out.println("------------------End of Test---------------------------");
    }

    // 37. Find the manufacturer of all the products
    @Test
    public void test37() {
        List<String> allManufacturers = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Manufacturers of All Products:" + allManufacturers);
        System.out.println("------------------End of Test---------------------------");

    }

    // 38. Find the image of products whose manufacturer is = Energizer
    @Test
    public void test38() {
        List<String> imagesForEnergizerProducts = response.extract().path("data.findAll { it.manufacturer == 'Energizer' }.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Images of Products with Manufacturer = Energizer:" + imagesForEnergizerProducts);
        System.out.println("------------------End of Test---------------------------");

    }

    // 39. Find the createdAt for all categories products whose price
    @Test
    public void test39() {
        List<String> createdAtForCategoriesProducts = response.extract().path("data.categories.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("CreatedAt for Categories of Products :" + createdAtForCategoriesProducts);
        System.out.println("------------------End of Test---------------------------");

    }

    //40. Find the uri of all the products
    @Test
    public void test40() {
        List<String> productUri = response.extract().path("data.url") ;
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("URIs of All Products :" + productUri);
        System.out.println("------------------End of Test---------------------------");

    }

}
