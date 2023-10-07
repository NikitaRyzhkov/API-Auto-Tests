import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class Specification {

    public static final RequestSpecification taskSpec = new RequestSpecBuilder()
            .setBaseUri(Endpoints.baseUri)
            .addHeader("Authorization", "Bearer " + Endpoints.token)
            .build();

}
