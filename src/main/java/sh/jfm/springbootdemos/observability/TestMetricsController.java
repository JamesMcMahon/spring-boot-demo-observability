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

/**
 * Demonstrates Prometheus integration using Micrometer.
 * <p>
 * This class contains examples of:<br>
 * - Manual instrumentation: using {@link Counter} and {@link Timer}.<br>
 * - Declarative annotations: {@link Counted} and {@link Timed}.<br>
 * These mechanisms monitor application performance and behavior.
 */
@RestController
public class TestMetricsController {
    private final Logger log = LoggerFactory.getLogger(TestMetricsController.class);

    private final Counter widgetCounter;
    private final Timer widgetTimer;

    public TestMetricsController(Counter widgetCounter, Timer widgetTimer) {
        this.widgetCounter = widgetCounter;
        this.widgetTimer = widgetTimer;
    }

    /**
     * Captures performance metrics while simulating widget creation.
     * <p>
     * Uses manual instrumentation with:<br>
     * - {@link Timer}: Tracks the duration of the task.<br>
     * - {@link Counter}: Counts the number of created widgets.<br>
     * This method provides fine-grained control over metric recording.
     */
    @PostMapping("/metrics/widgets")
    public void generateTestWidgetMetrics() {
        log.debug("Creating a Widget (for metrics)");
        try {
            widgetTimer.record(TestMetricsController::simulateRandomDelay);
        } finally {
            widgetCounter.increment();
        }
    }

    /**
     * Simulates MacGuffin creation with declarative metric annotations.
     * <p>
     * - {@link Counted}: Automatically increments the counter for MacGuffins created.<br>
     * - {@link Timed}: Records the time taken for the creation process.<br>
     * Declarative metrics simplify instrumentation by replacing manual handling.
     */
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
