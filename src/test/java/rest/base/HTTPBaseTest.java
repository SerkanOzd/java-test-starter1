package rest.base;

import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.baseURI;

/**
 * This class has the functions inherited by many other classes
 *
 * @author Serkan Özdemirci, MaibornWolff GmbH
 */
@SuppressWarnings("squid:S2187")
public class HTTPBaseTest {
    @BeforeEach
    void setBaseURI() {
        baseURI = "https://petstore.swagger.io/v2/pet";
    }
}
