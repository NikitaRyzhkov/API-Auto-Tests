package general;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specification {

    public static final RequestSpecification taskSpec = new RequestSpecBuilder()
            .setBaseUri(Endpoints.baseUri)
            .addHeader("Authorization", "Bearer " + Endpoints.token)
            .build();

}
