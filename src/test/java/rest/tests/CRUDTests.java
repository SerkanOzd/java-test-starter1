package rest.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import io.restassured.response.Response;
import rest.base.HTTPBaseTest;
import rest.service.PetService;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

/**
 * This class provides some samples of REST API automation tests with REST Assured library and Builder Pattern to verify
 * each endpoint works as expected.
 *
 * @author Serkan Özdemirci, MaibornWolff GmbH
 */
@DisplayName("API test automation w/RestAssured library - Post, Put, Delete and Get requests with Builder pattern")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CRUDTests extends HTTPBaseTest {

    PetService petService = new PetService();
    Response response;


    @Test
    @Order(1)
    void postDemoWithBuilder() {
        await().atMost(5, SECONDS).untilAsserted(() -> {
            response = petService.serializeToCreateAndStore(7227, "Something nice");
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(response.path("name").toString()).isEqualTo("Something nice");
        });
    }


    @Test
    @Order(2)
    void putDemoWithBuilder() {
        await().atMost(5, SECONDS).untilAsserted(() -> {
            response = petService.serializeToChangeAndStore(7227, "sold");
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(response.path("status").toString()).isEqualTo("sold");
        });

    }


    @Test
    @Order(3)
    void deleteDemoWithBuilder() {
        await().atMost(5, SECONDS).untilAsserted(() -> {
            response = petService.deleteAndStore(7227);
            assertThat(response.statusCode()).isEqualTo(200);
        });
    }


    @Test
    @Order(4)
    void getDemoWithBuilder() {
        await().atMost(5, SECONDS).untilAsserted(() -> {
            response = petService.getAndStore(7227);
            assertThat(response.statusCode()).isEqualTo(404);
        });
    }
}