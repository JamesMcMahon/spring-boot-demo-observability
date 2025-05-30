package sh.jfm.springbootdemos.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventListener {
    private static final Logger logger = LoggerFactory.getLogger(SecurityEventListener.class);

    private final Counter authenticationSuccessCounter;
    private final Counter authenticationFailureCounter;

    public SecurityEventListener(MeterRegistry meterRegistry) {
        authenticationSuccessCounter = Counter.builder("authentications.success")
                .description("Number of successful authentications")
                .register(meterRegistry);

        authenticationFailureCounter = Counter.builder("authentications.failure")
                .description("Number of failed authentications")
                .register(meterRegistry);
    }

    @EventListener
    public void onSuccess(AuthenticationSuccessEvent success) {
        logger.info("Authentication succeeded for user: {}", success.getAuthentication().getName());
        authenticationSuccessCounter.increment();
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failures) {
        logger.warn("Authentication failed for user: {}, reason: {}",
                failures.getAuthentication().getName(),
                failures.getException().getMessage());
        authenticationFailureCounter.increment();
    }
}
