package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
        //RestAssured.port = ;
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }
/**
 * Write the following test inside UserExtractionTest class.
 * Extraction Example









 * 11. Get gender of id = 5471
 */

// * 1. Extract the All Ids
    @Test
    public void extractAllIds(){
       List<Integer>allIds = response.extract().path("id");
        System.out.println(allIds);
    }
//* 2. Extract the all Names
@Test
    public void extractAllNames(){
        List<Integer>allNames = response.extract().path("name");
        System.out.println(allNames);
    }
// * 3. Extract the name of 5th object
    @Test
    public void extractFifthObject(){
        String name =response.extract().path("[4].name");
        System.out.println(name);
    }
// * 4. Extract the names of all object whose status = inactive
    @Test
    public  void extractNamesStatusInactive(){
       List<String> statusName =response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println(statusName);
    }
    //* 5. Extract the gender of all the object whose status = active
    @Test
    public void extractGenderStatusActive(){
       List<String>statusActiveName = response.extract().path("findAll{it.status== 'active'}.name");
        System.out.println(statusActiveName);
    }
  // * 6. Print the names of the object whose gender = female
    @Test
    public void nameObjectGenderFemale(){
       List<String> genderFemale= response.extract().path("findAll{it.gender== 'female'}.name");
        System.out.println(genderFemale);
    }
 // * 7. Get all the emails of the object where status = inactive
    @Test
    public void emailStatusInactive(){
       List<String> emailStatus=  response.extract().path("findAll{it.status== 'inactive'}.name");
        System.out.println(emailStatus);
    }
 //* 8. Get the ids of the object where gender = male
 @Test
    public void idGenderMale(){
       List<String> genderMale= response.extract().path("findAll{it.gender== 'male'}.id");
     System.out.println(genderMale);
 }
 //* 9. Get all the status
    @Test
    public void allStatus(){
     List<String> allStatusActiveInactive =   response.extract().path("status");
        System.out.println(allStatusActiveInactive);
    }
//* 10. Get email of the object where name = Karthik Dubashi IV
    @Test
 public void getEmailForName(){

     List<?> email = response.extract().path("findAll{it.name == 'Anish Reddy Sr.'}.email");
       System.out.println(email);
    }
 //* 11. Get gender of id = 5471
    @Test
    public void getGenderById(){
        List<String> gender= response.extract().path("findAll{it.id == 4040709}.gender");
        System.out.println(gender);
    }
}
