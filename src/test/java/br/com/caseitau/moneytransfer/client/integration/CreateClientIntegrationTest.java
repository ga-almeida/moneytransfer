package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.useCases.CreateClientUseCase;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@BaseIntegrationTest
public class CreateClientIntegrationTest {

    private static final String URL_PATH = "/v1/client";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateClientUseCase createClientUseCase;

    private ObjectMapper createClientMapper;

    @BeforeEach
    void setupEach() {
        createClientMapper = new ObjectMapper();
    }

//    @Test
//    @DisplayName("Given a valid client, when calling the created client controller, then it returns status code 201.")
//    void createClientControllerStatusCreated() throws Exception {
//
//        var resultURL = mockMvc.perform(post("/v1/client")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(createClientMapper.writeValueAsString(ClientDataTest.basicCreateClientJohnDoe())))
//                .andExpect(request().asyncStarted())
//                .andExpect(request().asyncResult(instanceOf(ResponseEntity.class)))
//                .andReturn();
//
//        mockMvc.perform(
//                        asyncDispatch(resultURL)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").isNotEmpty())
//                .andExpect(jsonPath("$.name").value("JOHN DOE"))
//                .andExpect(jsonPath("$.accountNumber").value("123456"))
//                .andExpect(jsonPath("$.accountBalance").value(BigDecimal.TEN))
//                .andExpect(jsonPath("$.createdAt").isNotEmpty());
//    }

//    @Test
//    @DisplayName("Given a client with a account number already exists, when calling the created client controller, then it returns status code 409.")
//    void createClientControllerStatusConflict() throws Exception {
//        var resultURL = mockMvc.perform(post(URL_PATH)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(createClientMapper.writeValueAsString(CreateClientDataTest.basicCreateClientDTO())))
//                .andReturn();
//
//        mockMvc.perform(
//                        asyncDispatch(resultURL)
//                )
//                .andExpect(status().isConflict())
//                .andExpect(jsonPath("$.id").isNotEmpty())
//                .andExpect(jsonPath("$.name").value("JOHN DOE"))
//                .andExpect(jsonPath("$.accountNumber").value("123456"))
//                .andExpect(jsonPath("$.accountBalance").value(BigDecimal.TEN))
//                .andExpect(jsonPath("$.createdAt").isNotEmpty());
//    }
}
