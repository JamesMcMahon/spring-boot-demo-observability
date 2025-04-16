package sh.jfm.springbootdemos.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDataController {

    private final Logger log = LoggerFactory.getLogger(TestDataController.class);

    @PostMapping("/logs/info")
    public void generateTestInfoLogs() {
        log.info("Test info log");
    }
}
