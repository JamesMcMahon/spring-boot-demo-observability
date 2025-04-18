package sh.jfm.springbootdemos.observability;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for enabling Micrometer metrics annotations in the application.
 * <p>
 * Provides support for {@code @Counted} and {@code @Timed} annotations by registering
 * the corresponding aspects as Spring beans.
 */
@Configuration
public class MetricsAnnotationConfig {

    @Bean
    public CountedAspect countedAspect(MeterRegistry registry) {
        return new CountedAspect(registry);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }
}
