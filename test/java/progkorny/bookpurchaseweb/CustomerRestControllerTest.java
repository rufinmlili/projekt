package progkorny.bookpurchaseweb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import progkorny.bookpurchaseweb.model.Customer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // JSON konvertáláshoz

    @Test
    public void testCreateGetAndDeleteCustomer() throws Exception {
        // 1. Létrehozunk egy Customer objektumot
        Customer customer = new Customer(
                "Virág", "Nagy", "virag.nagy@example.com",
                "+3612345678"
        );

        // 2. POST: létrehozás
        String json = objectMapper.writeValueAsString(customer);

        String response = mockMvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()) // ID generálva van
                .andExpect(jsonPath("$.email").value("virag.nagy@example.com"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // A válaszból visszakapjuk a mentett customer-t (id-vel)
        Customer savedCustomer = objectMapper.readValue(response, Customer.class);
        Integer savedId = savedCustomer.getId();

        // 3. GET: lekérdezés az ID alapján
        mockMvc.perform(get("/api/customers/" + savedId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("virag.nagy@example.com"))
                .andExpect(jsonPath("$.firstName").value("Virág"));

        // 4. DELETE: törlés
        mockMvc.perform(delete("/api/customers/" + savedId))
                .andExpect(status().isOk())
                .andExpect(content().string("customer with id " + savedId + " is deleted"));

        // 5. GET törölt customer-re: NOT FOUND vagy 404 elvárt
        mockMvc.perform(get("/api/customers/" + savedId))
                .andExpect(status().isNotFound());
    }
}
