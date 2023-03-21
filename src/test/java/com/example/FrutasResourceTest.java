package com.example;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.smallrye.common.constraint.Assert.assertFalse;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(FrutasResource.class)
public class FrutasResourceTest {

    @Inject
    FrutasService frutasService;

    @Test
    public void testListFrutas() {
        given()
            .when().get()
            .then()
            .statusCode(200)
            .body(is("[{\"id\":1,\"nome\":\"Maça\",\"qtd\":5},{\"id\":2,\"nome\":\"Pera\",\"qtd\":3},{\"id\":3,\"nome\":\"Laranja\",\"qtd\":1}]"));
    }

    @Test
    public void testListFrutas2() {
        List<Fruta> list = frutasService.list();
        assertFalse(list.isEmpty());
    }
}