package sh.jfm.springbootdemos.observability;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

@RestController
public class TestMetricsController {
    private final Logger log = LoggerFactory.getLogger(TestMetricsController.class);

    private final Counter widgetCounter;
    private final Timer widgetTimer;

    public TestMetricsController(Counter widgetCounter, Timer widgetTimer) {
        this.widgetCounter = widgetCounter;
        this.widgetTimer = widgetTimer;
    }

    @PostMapping("/metrics/widgets")
    public void generateTestWidgetMetrics() {
        log.debug("Creating a Widget (for metrics)");
        try {
            widgetTimer.record(TestMetricsController::simulateRandomDelay);
        } finally {
            widgetCounter.increment();
        }
    }

    @PostMapping("/metrics/macguffins")
    @Counted(value = "macguffins.created", description = "Number of MacGuffins created")
    @Timed(value = "macguffins.creation.time", description = "Time taken to create a MacGuffin")
    public void generateTestMacGuffinMetrics() {
        log.debug("Creating a MacGuffin (for metrics)");
        TestMetricsController.simulateRandomDelay();
    }

    private static void simulateRandomDelay() {
        simulateDelay(ThreadLocalRandom.current().nextLong(1, 2001));
    }

    private static void simulateDelay(long delayMS) {
        try {
            Thread.sleep(delayMS);
        } catch (InterruptedException e) {
            // https://stackoverflow.com/questions/4906799/why-invoke-thread-currentthread-interrupt-in-a-catch-interruptexception-block
            Thread.currentThread().interrupt();
        }
    }
}
