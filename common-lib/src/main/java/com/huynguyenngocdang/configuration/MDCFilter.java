package com.huynguyenngocdang.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.huynguyenngocdang.utils.DateUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class MDCFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String requestId = request.getHeader("X-Request-Id");
            if (requestId == null) {
                requestId = java.util.UUID.randomUUID().toString();
            }
            MDC.put("requestId", requestId);
            String requestDateTime = DateUtils.getCurrentDateTime();
            MDC.put("requestDateTime", requestDateTime);
            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
