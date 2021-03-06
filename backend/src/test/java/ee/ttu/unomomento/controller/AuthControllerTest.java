package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.model.Account;
import ee.ttu.unomomento.model.template.UserRegistration;
import ee.ttu.unomomento.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static ee.ttu.unomomento.security.TokenAuthenticationService.HEADER_STRING;
import static ee.ttu.unomomento.security.TokenAuthenticationService.TOKEN_PREFIX;
import static ee.ttu.unomomento.security.TokenAuthenticationService.createToken;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldNotRegisterNotValidatedForm() throws Exception {
        mockMvc
                .perform(post("/auth/register", new UserRegistration("", "", "", "", "", "", null, null, null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestFromUserDataWithNoToken() throws Exception {
        mockMvc
                .perform(get("/auth/user"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldReturnBadRequestFromNotExistUserData() throws Exception {
        String token = createToken("Test");

        assertNotNull(token);
        mockMvc
                .perform(get("/auth/user").header(HEADER_STRING, TOKEN_PREFIX + token))
                .andExpect(status().isBadRequest());
    }
}
