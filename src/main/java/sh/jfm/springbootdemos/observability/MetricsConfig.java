package sh.jfm.springbootdemos.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter widgetCounter(MeterRegistry registry) {
        return Counter.builder("widgets.created")
                .description("Number of widgets created")
                .register(registry);
    }

    @Bean
    public Timer widgetTimer(MeterRegistry registry) {
        return Timer.builder("widgets.creation.time")
                .description("Time taken to create a widget")
                .register(registry);
    }
}
