package sh.jfm.springbootdemos.observability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

/**
 * Generates data to test Loki integration
 */
@RestController
public class TestLogsController {

    private final Logger log = LoggerFactory.getLogger(TestLogsController.class);

    @PostMapping("/logs/info")
    public void generateTestInfoLogs() {
        log.info("Test info log");
    }

    @PostMapping("/logs/random")
    public void generateRandomLogs(@RequestParam(defaultValue = "10") int lines) {
        IntStream.range(0, lines).forEach(generateRandomLogLine());
    }

    private IntConsumer generateRandomLogLine() {
        return i -> {
            switch ((int) (Math.random() * 4)) {
                case 0 -> log.info(getMessage(i + 1));
                case 1 -> log.debug(getMessage(i + 1));
                case 2 -> log.warn(getMessage(i + 1));
                case 3 -> log.error(getMessage(i + 1));
            }
        };
    }

    private static String getMessage(int number) {
        return "Random log message " + number;
    }
}
