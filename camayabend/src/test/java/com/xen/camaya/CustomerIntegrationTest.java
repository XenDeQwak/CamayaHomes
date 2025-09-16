package com.xen.camaya;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xen.camaya.model.CustomerModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndFetchCustomer() throws Exception {
        CustomerModel customer = new CustomerModel();
        customer.setId(1);
        customer.setCustomerName("John Doe");
        customer.setCustomerEmail("john@example.com");

        // Create
        mockMvc.perform(put("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerName\":\"John Doe\",\"customerEmail\":\"john@example.com\",\"houseName\":\"Camaya Unit\",\"customerContactNo\":1234567890}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.customerEmail").value("john@example.com"));

        // Fetch
        mockMvc.perform(get("/api/customer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.customerEmail").value("john@example.com"));
    }
}
