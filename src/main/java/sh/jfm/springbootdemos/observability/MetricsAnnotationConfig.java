package sh.jfm.springbootdemos.observability;

import io.micrometer.core.aop.CountedAspect;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration class for enabling Micrometer metrics annotations in the application.
 * <p>
 * Provides support for {@code @Counted}, {@code @Timed}, and {@code @Observed} annotations
 * by registering the corresponding aspects as Spring beans.
 * Requires the dependency spring-boot-starter-aop to work.
 */
@Configuration
public class MetricsAnnotationConfig {

    @Bean
    public CountedAspect countedAspect(MeterRegistry meterRegistry) {
        return new CountedAspect(meterRegistry);
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry meterRegistry) {
        return new TimedAspect(meterRegistry);
    }
}
