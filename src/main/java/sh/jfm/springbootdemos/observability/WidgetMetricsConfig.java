package sh.jfm.springbootdemos.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class to define metrics for monitoring widget creation.
 * <p>
 * This is the manual approach, the other approach uses the built-in Micrometer annotations
 */
@Configuration
public class WidgetMetricsConfig {

    /**
     * Defines a Counter metric to track the number of widgets created.
     *
     * @param registry the MeterRegistry to register the metric with.
     * @return a Counter for tracking widget creation count.
     */
    @Bean
    public Counter widgetCounter(MeterRegistry registry) {
        return Counter.builder("widgets.created")
                .description("Number of widgets created")
                .register(registry);
    }

    /**
     * Defines a Timer metric to measure the time taken to create a widget.
     *
     * @param registry the MeterRegistry to register the metric with.
     * @return a Timer for tracking widget creation time.
     */
    @Bean
    public Timer widgetTimer(MeterRegistry registry) {
        return Timer.builder("widgets.creation.time")
                .description("Time taken to create a widget")
                .register(registry);
    }
}
