package br.com.caseitau.moneytransfer.client.integration;

import br.com.caseitau.moneytransfer.client.dataTest.CreateClientDataTest;
import br.com.caseitau.moneytransfer.core.BaseIntegrationTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@BaseIntegrationTest
public class CreateClientIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper createClientMapper;

    @BeforeEach
    void setupEach() {
        createClientMapper = new ObjectMapper();
    }

    @Test
    void givenCreateClientValid_WhenCallCreateClientController_ThenReturnSuccess() throws Exception {

        mockMvc.perform(
                        post("/client")
                                .contentType(MediaType.APPLICATION_JSON)
                                .contentType(createClientMapper.writeValueAsString(CreateClientDataTest.validCreateClientDTO()))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("JOHN DOE"))
                .andExpect(jsonPath("$.name").value("123456"))
                .andExpect(jsonPath("$.name").value(BigDecimal.ZERO));
    }
}
