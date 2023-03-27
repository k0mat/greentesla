package pl.k0mat.greentesla.game;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import pl.k0mat.greentesla.BaseITTest;
import pl.k0mat.greentesla.ResourceReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameControllerTest extends BaseITTest {

    @Test
    void calculateOrder() throws Exception {
        String input = ResourceReader.getResourceFileAsString("game/example_request.json");
        String expected = ResourceReader.getResourceFileAsString("game/example_response.json");

        mvc.perform(post("/onlinegame/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(expected, true));
    }
}