//package com.example.complainboard.configuration;
//
//import io.opentelemetry.api.trace.Span;
//import io.opentelemetry.api.trace.SpanKind;
//import io.opentelemetry.api.trace.Tracer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class OpenTelemetryInterceptor implements HandlerInterceptor {
//    @Autowired
//    private Tracer tracer;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Span span = tracer.spanBuilder(request.getRequestURI())
//                .setSpanKind(SpanKind.SERVER)
//                .startSpan();
//
//        // Thêm span vào context hiện tại
//        span.makeCurrent();
//
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        Span span = Span.current();
//        if (span != null) {
//            span.end();
//        }
//    }
//}
//
