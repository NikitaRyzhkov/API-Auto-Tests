package comments;
import general.Specification;
import general.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentService {

    public Response getAllComments(String taskID) {
        return given().
                spec(Specification.taskSpec)
                .pathParam("id",taskID)
                .when()
                .get(Endpoints.comments);
    }
    public Response createComment (CommentReqBody reqBody) {
        return given()
                .spec(Specification.taskSpec)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post(Endpoints.singleComment);

    }
    public Response getComment (String commentID) {
        return  given()
                .spec(Specification.taskSpec)
                .pathParam("id",commentID)
                .when()
                .get(Endpoints.particularComment);
    }
    public Response updateComment (String commentID, CommentReqBody reqBody) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id",commentID)
                .contentType(ContentType.JSON)
                .body(reqBody)
                .when()
                .post(Endpoints.particularComment);
    }
    public Response deleteComment (String commentID) {
        return given()
                .spec(Specification.taskSpec)
                .pathParam("id",commentID)
                .when()
                .delete(Endpoints.particularComment);
    }


}
/* TODO: абстрактный класс

 */