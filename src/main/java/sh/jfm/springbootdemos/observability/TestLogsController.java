package sh.jfm.springbootdemos.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class TestLogsController {

    private final Logger log = LoggerFactory.getLogger(TestLogsController.class);

    @PostMapping("/logs/info")
    public void generateTestInfoLogs() {
        log.info("Test info log");
    }
    
    @PostMapping("/logs/random")
    public void generateRandomLogs(@RequestParam(defaultValue = "10") int lines) {
        IntStream.range(0, lines).forEach(i -> {
            String message = "Random log message " + (i + 1);
            switch ((int) (Math.random() * 4)) {
                case 0 -> log.info(message);
                case 1 -> log.debug(message);
                case 2 -> log.warn(message);
                case 3 -> log.error(message);
            }
        });
    }
}
