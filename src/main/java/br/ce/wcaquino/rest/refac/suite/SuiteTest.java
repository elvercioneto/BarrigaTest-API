package br.ce.wcaquino.rest.refac.suite;

import br.ce.wcaquino.rest.core.BaseTest;
import br.ce.wcaquino.rest.refac.AuthTest;
import br.ce.wcaquino.rest.refac.ContasTest;
import br.ce.wcaquino.rest.refac.MovimentacaoTest;
import br.ce.wcaquino.rest.refac.SaldoTest;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(org.junit.runners.Suite.class)
@Suite.SuiteClasses({
        ContasTest.class,
        MovimentacaoTest.class,
        SaldoTest.class,
        AuthTest.class
})
public class SuiteTest extends BaseTest {

    @BeforeClass
    public static void login() {
        Map<String, String> login = new HashMap<String, String>();
        login.put("email", "elvercio@mail.com");
        login.put("senha", "123456");

        String TOKEN = given()
                .body(login)
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract().path("token")
                ;

        RestAssured.requestSpecification.header("Authorization", "JWT " + TOKEN);
        RestAssured.get("/reset").then().statusCode(200);
    }

}
