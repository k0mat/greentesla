package pl.k0mat.greentesla.transactions;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import pl.k0mat.greentesla.BaseITTest;
import pl.k0mat.greentesla.ResourceReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TransactionsControllerTest extends BaseITTest {
    @Test
    void generateReport() throws Exception {
        String input = ResourceReader.getResourceFileAsString("transactions/example_request.json");
        String expected = ResourceReader.getResourceFileAsString("transactions/example_response.json");

        mvc.perform(post("/transactions/report")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expected));
    }
}