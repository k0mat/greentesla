package pl.k0mat.greentesla.game;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import pl.k0mat.greentesla.BaseITTest;
import pl.k0mat.greentesla.ResourceReader;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
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

    @Test
    void testRandomQueue() throws Exception {
        String randomQueueJson = mvc.perform(get("/onlinegame/randomQueue")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .param("dataSize", "20000")
        ).andReturn().getResponse().getContentAsString();

        mvc.perform(post("/onlinegame/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(randomQueueJson)
        ).andExpect(status().isOk());
    }
}