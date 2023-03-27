package pl.k0mat.greentesla.atm;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import pl.k0mat.greentesla.BaseITTest;
import pl.k0mat.greentesla.ResourceReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AtmControllerTest extends BaseITTest {

    @Test
    void calculateOrder() throws Exception {
        String input = ResourceReader.getResourceFileAsString("atm/example_1_request.json");
        String expected = ResourceReader.getResourceFileAsString("atm/example_1_response.json");

        mvc.perform(post("/atms/calculateOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(expected, true));
    }

    @Test
    void calculateOrder2() throws Exception {
        String input = ResourceReader.getResourceFileAsString("atm/example_2_request.json");
        String expected = ResourceReader.getResourceFileAsString("atm/example_2_response.json");

        mvc.perform(post("/atms/calculateOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(expected, true));
    }
}