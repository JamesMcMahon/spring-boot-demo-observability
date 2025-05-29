package sh.jfm.springbootdemos.observability;

import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSecurityController {

    private static final Logger logger = LoggerFactory.getLogger(TestSecurityController.class);

    @Observed(name = "secure", contextualName = "secure-endpoint")
    @GetMapping("/secure")
    @PreAuthorize("isAuthenticated()")
    public String securedEndpoint() {
        logger.info("Secured endpoint accessed");
        return "Hello, authenticated user!";
    }
}
