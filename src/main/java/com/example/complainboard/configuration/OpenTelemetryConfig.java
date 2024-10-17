package com.example.complainboard.configuration;

import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class OpenTelemetryConfig {
//    @Bean
//    public OpenTelemetry openTelemetry() {
//        return AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
//    }

//    @Bean
//    public OpenTelemetry openTelemetry() {
////        if (GlobalOpenTelemetry.get() != null) {
////            return GlobalOpenTelemetry.get();
////        }
////        JaegerGrpcSpanExporter jaegerExporter = JaegerGrpcSpanExporter.builder()
////                .setEndpoint("http://localhost:14250")
////                .build();
//        // Create Logging exporter
//        LoggingSpanExporter loggingExporter = new LoggingSpanExporter();
//// Create a sampler with the sampling percentage
//        Sampler sampler = new Sampler() {
//            private final Sampler parentBasedSampler = Sampler.parentBased(Sampler.alwaysOn());
//
//            @Override
//            public SamplingResult shouldSample(Context parentContext, String traceId, String name, SpanKind spanKind, Attributes attributes, List<LinkData> parentLinks) {
//                if ("POST".equals(attributes.get(AttributeKey.stringKey("http.method")))) {
//                    return parentBasedSampler.shouldSample(parentContext, traceId, name, spanKind, attributes, parentLinks);
//                }
//                return SamplingResult.drop();
//            }
//
//            @Override
//            public String getDescription() {
//                return "ConditionalSampler{20% on POST method}";
//            }
//        };
//
//        // Create BatchSpanProcessor and add exporters
////        BatchSpanProcessor batchProcessor = BatchSpanProcessor.builder(jaegerExporter).build();
//        BatchSpanProcessor loggingProcessor = BatchSpanProcessor.builder(loggingExporter).build();
//
//        // Create SdkTracerProvider with processors and sampler
//        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
////                .addSpanProcessor(batchProcessor)
//                .addSpanProcessor(loggingProcessor)
//                .setSampler(sampler)
//                .build();
//
//        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
//                .setTracerProvider(tracerProvider)
//                .build();
//
//        return openTelemetrySdk;
////        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
////                .addSpanProcessor(BatchSpanProcessor
////                        .builder(jaegerExporter)
////                        .build())
////                .setSampler(Sampler.parentBased(Sampler.alwaysOn()))
////                .build();
////
////        OpenTelemetry openTelemetry = OpenTelemetrySdk.builder()
////                .setTracerProvider(tracerProvider)
////                .buildAndRegisterGlobal();
////        return openTelemetry;
//    }

//    @Bean
//    public Tracer tracer(OpenTelemetry openTelemetry) {
//        return openTelemetry.getTracer("spring-boot-application");
//    }
//
}