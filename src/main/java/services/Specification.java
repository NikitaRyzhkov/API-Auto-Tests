package services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Specification {

    public static final RequestSpecification SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(Endpoints.BASE_URI)
            .addHeader("Authorization", "Bearer " + Endpoints.TOKEN)
            .build();

}
