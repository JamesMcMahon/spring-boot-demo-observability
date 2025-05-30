package sh.jfm.springbootdemos.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class SecurityEventListener {
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
    public void onSuccess(@SuppressWarnings("unused") AuthenticationSuccessEvent success) {
        authenticationSuccessCounter.increment();
    }

    @EventListener
    public void onFailure(@SuppressWarnings("unused") AbstractAuthenticationFailureEvent failures) {
        authenticationFailureCounter.increment();
    }
}
