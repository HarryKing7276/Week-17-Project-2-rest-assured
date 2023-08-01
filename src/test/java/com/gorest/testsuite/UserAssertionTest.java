package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
//import static org.hamcrest.CoreMatchers.hasItem;
//import static org.hamcrest.CoreMatchers.hasItems;
//import static org.hamcrest.Matchers.equalTo;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2/";
        //RestAssured.port = ;
        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    /**
     * Assert the following:
     * <p>
     * 2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
     * 3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
     * 4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
     * Guha, Karthik Dubashi IV)
     * 5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
     * 6. Verify the status is “Active” of username is “Shanti Bhat V”
     * 7. Verify the Gender = male of username is “Niro Prajapat”
     */

// * 1. Verify the if the total record is 20
    @Test
    public void verifyTheTotalRecord() {
        response.body("size", equalTo(20));

    }

    //* 2. Verify the if the name of id = 5487 is equal to ”Sanya Kaur”
    @Test
    public void verifyIdNameSanyaKaur() {
        response.body("findAll{it.id == 4040719}.name", equalTo("Keerti Embranthiri"));
        //  response.body("[2].name",equalTo("Keerti Embranthiri"));

    }

    //    * 3. Check the single ‘Name’ in the Array list ("Buddhana Prajapat")
    @Test
    public void verifyNameFromArrayList() {
        response.body("name", hasItem("Mr. Preity Patel"));
    }

    // * 4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
//     * Guha, Karthik Dubashi IV)
    @Test
    public void verifyNamesFromArrayList() {
        response.body("name", hasItems("Mr. Preity Patel", "Ms. Vaishvi Shukla", "Malati Gandhi"));
    }

    //* 5. Verify the email of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void emailUserIdWithName() {
        response.body("find{it.id== 4040723 }.email", equalTo("amarnath_devar@crona.test"));
    }

    // * 6. Verify the status is “Active” of username is “Shanti Bhat V”
    @Test
    public void verifyStatusActive() {
        //   response.body("find{it.name=='Shreyashi Kakkar Sr.' }.status",equalTo("active"));
        response.body("find{it.name =='Ms. Vaishvi Shukla'}.status", equalTo("inactive"));

    }

    //* 7. Verify the Gender = male of username is “Niro Prajapat”
    @Test
    public void verifyGenderMaleWithUserName() {
        response.body("find{it.name== 'Amarnath Devar'}.gender", equalTo("male"));
    }

}
