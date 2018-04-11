package ee.ttu.unomomento.component;

import ee.ttu.unomomento.security.TokenAuthenticationService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static ee.ttu.unomomento.security.TokenAuthenticationService.*;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldNotAllowAccessForUnauthorizedUsers() throws Exception {
        mockMvc
            .perform(get("/api/curators"))
            .andExpect(status().isForbidden());
    }

    //@Ignore
    @Test
    public void shouldGenerateToken() throws Exception {
        String token = createToken("eriktest");

        assertNotNull(token);
        mockMvc
            .perform(get("/api/curators").header(HEADER_STRING, TOKEN_PREFIX + token))
            .andExpect(status().isOk());
    }
}
