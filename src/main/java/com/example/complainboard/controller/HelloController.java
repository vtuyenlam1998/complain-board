package com.example.complainboard.controller;

import com.example.complainboard.configuration.OpenTelemetryConfiguration;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    private Tracer tracer = OpenTelemetryConfiguration.getInstance().getOpenTelemetry().getTracer(HelloController.class.getName(), "0.1.0");;
    private Tracer tracer = GlobalOpenTelemetry.getTracer(HelloController.class.getName());
    @GetMapping("/hello")
    public String hello() {
        Span span = tracer.spanBuilder("hello").startSpan();
        try {
            span.addEvent("Hello World");
            span.setAttribute("method", "GET");
            return "Hello World";
        } finally {
            span.end();
        }
    }
}
