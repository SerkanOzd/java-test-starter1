package rest.service;

import java.util.List;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import rest.entity.Category;
import rest.entity.Pet;
import rest.entity.Tags;

/**
 * This class is used as helper for test classes. Provides a JSON request payload generator in Builder pattern, sending,
 * receiving and then storing it in the response body.
 *
 * @author Serkan Özdemirci, MaibornWolff GmbH
 */
public class PetService {


    @Step("Create new pet with id {0}")
    public Response serializeToCreateAndStore(int petID, String petName) {
        return RestAssured.
                given().contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .and()
                .body(createPet(petID, petName))
                .when()
                .post();
    }


    @Step("Update existing pet with id {0}")
    public Response serializeToChangeAndStore(int petID, String status) {
        return RestAssured.
                given().contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .and()
                .body(changePet(petID, status))
                .when()
                .put();
    }


    @Step("Delete pet with id {0}")
    public Response deleteAndStore(int petID) {
        return RestAssured.
                given().contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .header("api_key", "special-key")
                .pathParam("id", petID)
                .when()
                .delete("/{id}");
    }


    @Step("Get existing pet with id {0}")
    public Response getAndStore(int petID) {
        return RestAssured.
                given().contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .pathParam("id", petID)
                .when()
                .get("/{id}");
    }


    private Pet createPet(int petId, String petName) {
        return Pet.builder()
                .id(petId)
                .category(Category.builder().id(111).name(petName).build())
                .name("Something nice")
                .photoUrls(List.of("someLinkHere"))
                .tags(List.of(Tags.builder().id(222).name("My name").build()))
                .status("available")
                .build();
    }


    private Pet changePet(int petID, String status) {
        return Pet.builder()
                .id(petID)
                .category(Category.builder().id(11123).name("Pretty").build())
                .name("Something nicer")
                .photoUrls(List.of("anotherLinkHere"))
                .tags(List.of(Tags.builder().id(2345).name("Yourname").build()))
                .status(status)
                .build();
    }
}
