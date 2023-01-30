package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.dataTest.ClientDataTest;
import br.com.caseitau.moneytransfer.client.dto.CreateClientDTO;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest2;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@BaseIntegrationTest2
public class CreateClientIntegrationTest2 {

    private static final String URL_PATH = "/v1/client";

    private ObjectMapper createClientMapper;

    @BeforeEach
    void setupEach() {
        createClientMapper = new ObjectMapper();
    }
    @BeforeAll
    void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = 8082;
    }

    @Test
    @DisplayName("Given a valid client, when calling the created client controller, then it returns status code 201.")
    void createClientControllerStatusCreated() throws Exception {

        CreateClientDTO createClientDTO = ClientDataTest.basicCreateClientDTO();

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(createClientMapper.writeValueAsString(createClientDTO))
                .when()
                .post("/v1/client")
                .then()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertNotNull(response.jsonPath().getString("id"));
        Assertions.assertEquals(createClientDTO.getName(), response.jsonPath().getString("name"));
        Assertions.assertEquals(createClientDTO.getAccountNumber(), response.jsonPath().getString("accountNumber"));
        Assertions.assertEquals(createClientDTO.getAccountBalance(), response.jsonPath().getString("accountBalance"));
        Assertions.assertNotNull(response.jsonPath().getString("createdAt"));

//        mockMvc.perform(
//                        asyncDispatch(resultURL)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").isNotEmpty())
//                .andExpect(jsonPath("$.name").value("JOHN DOE"))
//                .andExpect(jsonPath("$.accountNumber").value("123456"))
//                .andExpect(jsonPath("$.accountBalance").value(BigDecimal.TEN))
//                .andExpect(jsonPath("$.createdAt").isNotEmpty());
    }
}
