package progkorny.bookpurchaseweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import progkorny.bookpurchaseweb.model.Customer;
import progkorny.bookpurchaseweb.model.Purchase;
import progkorny.bookpurchaseweb.service.PurchaseService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllPurchase() throws Exception {
        Customer customer1 = new Customer("Virág", "Nagy", "virag.nagy@example.com", "+3612345678");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");

        Purchase event1 = new Purchase();
        event1.setId(1);
        event1.setPurchaseCustomer(customer1);

        Customer customer2 = new Customer("Virág", "Nagy", "virag.nagy@example.com", "+3612345678");
        customer2.setFirstName("Jane");
        customer2.setLastName("Smith");

        Purchase event2 = new Purchase();
        event2.setId(2);
        event2.setPurchaseCustomer(customer2);

        List<Purchase> mockEvents = Arrays.asList(event1, event2);

        Mockito.when(purchaseService.getAllPurchase()).thenReturn(mockEvents);

        mockMvc.perform(get("/api/purchase"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockEvents)));
    }
}
