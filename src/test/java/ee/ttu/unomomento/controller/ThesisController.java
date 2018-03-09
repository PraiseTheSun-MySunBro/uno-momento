package ee.ttu.unomomento.controller;

import ee.ttu.unomomento.service.ThesisService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ThesisController.class)
public class ThesisController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ThesisService mockThesisService;
}
