//package com.example.complainboard.configuration;
//
//import io.opentelemetry.api.OpenTelemetry;
//import io.opentelemetry.api.trace.Span;
//import io.opentelemetry.api.trace.SpanKind;
//import io.opentelemetry.api.trace.Tracer;
//import io.opentelemetry.context.Scope;
//import io.opentelemetry.context.propagation.TextMapPropagator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.IOException;
//
//@Component
//public class OpenTelemetryFilter implements Filter {
//
//    private final Tracer tracer;
//    private final TextMapPropagator textMapPropagator;
//
//    @Autowired
//    public OpenTelemetryFilter(OpenTelemetry openTelemetry) {
//        this.tracer = openTelemetry.getTracer("my-application");
//        this.textMapPropagator = openTelemetry.getPropagators().getTextMapPropagator();
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        Span span = tracer.spanBuilder("HTTP Request")
//                .setSpanKind(SpanKind.SERVER)
//                .startSpan();
//
//        try (Scope scope = span.makeCurrent()) {
//            // Propagate context
//            textMapPropagator.inject(span.getContext(), request, ServletRequest::setAttribute);
//
//            // Proceed with the chain
//            chain.doFilter(request, response);
//        } catch (Throwable t) {
//            span.recordException(t);
//            throw t;
//        } finally {
//            span.end();
//        }
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        // Initialization logic if needed
//    }
//
//    @Override
//    public void destroy() {
//        // Cleanup logic if needed
//    }
//}