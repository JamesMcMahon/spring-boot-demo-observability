package sh.jfm.springbootdemos.observability;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestSecurityController.class)
class TestSecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void securedEndpoint_WithAuthenticatedUser_ReturnsSuccess() throws Exception {
        mockMvc.perform(get("/secure"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, authenticated user!"));
    }

    @Test
    void securedEndpoint_WithUnauthenticatedUser_ReturnsUnauthorized() throws Exception {
        mockMvc.perform(get("/secure"))
                .andExpect(status().isUnauthorized());
    }
}