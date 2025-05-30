package sh.jfm.springbootdemos.observability;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityMetricsFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(SecurityMetricsFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.info("doFilterInternal called for URI: {}", request.getRequestURI());
        try {
            filterChain.doFilter(request, response);
        } catch (InsufficientAuthenticationException e) {
            logger.warn("Unauthorized access attempt to URI: {}", request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
