package services;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.CommentReqBody;

import static io.restassured.RestAssured.given;

public class CommentService {

    public Response getAllComments(String taskID) {
        return given().
                spec(Specification.SPECIFICATION)
                .pathParam("id",taskID)
                .when()
                .get(Endpoints.COMMENTS);
    }
    public Response createComment (CommentReqBody reqBody) {
        return given()
                .spec(Specification.SPECIFICATION)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post(Endpoints.SINGLE_COMMENT);

    }
    public Response getComment (String commentID) {
        return  given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id",commentID)
                .when()
                .get(Endpoints.PARTICULAR_COMMENT);
    }
    public Response updateComment (String commentID, CommentReqBody reqBody) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id",commentID)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post(Endpoints.PARTICULAR_COMMENT);
    }
    public Response deleteComment (String commentID) {
        return given()
                .spec(Specification.SPECIFICATION)
                .pathParam("id",commentID)
                .when()
                .delete(Endpoints.PARTICULAR_COMMENT);
    }


}
/* TODO: абстрактный класс

 */