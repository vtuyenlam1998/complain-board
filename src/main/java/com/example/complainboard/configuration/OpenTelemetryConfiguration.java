package com.example.complainboard.configuration;


import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.baggage.propagation.W3CBaggagePropagator;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator;
import io.opentelemetry.context.propagation.ContextPropagators;
import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.exporter.jaeger.JaegerGrpcSpanExporter;
import io.opentelemetry.exporter.logging.LoggingMetricExporter;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.opentelemetry.exporter.otlp.logs.OtlpGrpcLogRecordExporter;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import io.opentelemetry.sdk.trace.samplers.Sampler;
import io.opentelemetry.semconv.ResourceAttributes;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.opentelemetry.semconv.ResourceAttributes.SERVICE_NAME;


public class OpenTelemetryConfiguration {
    private String OTEL_EXPORTER_OTLP_ENDPOINT = "https://2f53f9b7ad934eebae3746ad6b54b247.apm.asia-southeast1.gcp.elastic-cloud.com:443";
    private String TOKEN = "fU5QmK1arwVDInzjWr";
    private String endpoint;
    private String serviceName;
    private SdkTracerProvider tracerProvider;
    private SdkMeterProvider meterProvider;
    private OpenTelemetry openTelemetry;
    //    private MeterRegistry registry;
    private static final OpenTelemetryConfiguration openTelemetryConfiguration = new OpenTelemetryConfiguration();

    private OpenTelemetryConfiguration() {
    }

    public static OpenTelemetryConfiguration getInstance() {
        return openTelemetryConfiguration;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @SuppressWarnings("deprecation")
    public OpenTelemetry createOpenTelemetry() {
        Resource resource = Resource.getDefault().toBuilder().put(SERVICE_NAME, serviceName)
                .put(ResourceAttributes.SERVICE_VERSION, "0.1.0").build();
        // Jaeger 설정
        JaegerGrpcSpanExporter jaegerExporter = JaegerGrpcSpanExporter.builder().setEndpoint(endpoint).setTimeout(30, TimeUnit.SECONDS)// Jaeger 주소
                .build();

        // Elastic APM (OTLP) 설정
        OtlpGrpcSpanExporter otlpExporter = OtlpGrpcSpanExporter.builder().setEndpoint(OTEL_EXPORTER_OTLP_ENDPOINT)
                .addHeader("Authorization", "Bearer " + TOKEN)// Elastic APM OTLP endpoint 주소
                .build();

        Resource serviceNameResource = Resource.create(Attributes.of(SERVICE_NAME, serviceName));

        tracerProvider = SdkTracerProvider.builder().setSampler(Sampler.alwaysOn())
                .addSpanProcessor(SimpleSpanProcessor.create(LoggingSpanExporter.create()))
                .addSpanProcessor(BatchSpanProcessor.builder(jaegerExporter).build())
                .addSpanProcessor(BatchSpanProcessor.builder(otlpExporter).build()).setResource(resource).build();
        OtlpGrpcMetricExporter metricExporter = OtlpGrpcMetricExporter.builder()
                .setEndpoint(OTEL_EXPORTER_OTLP_ENDPOINT).addHeader("Authorization", "Bearer " + TOKEN).build();

        meterProvider = SdkMeterProvider.builder()
                .registerMetricReader(PeriodicMetricReader.builder(LoggingMetricExporter.create()).build())
                .registerMetricReader(
                        PeriodicMetricReader.builder(metricExporter).setInterval(Duration.ofSeconds(10)).build())
                .setResource(resource).build();

        openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .setMeterProvider(meterProvider)
                .setPropagators(ContextPropagators.create(TextMapPropagator.composite(W3CTraceContextPropagator.getInstance(), W3CBaggagePropagator.getInstance())))
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread(tracerProvider::shutdown));
//        registry = meterRegistry(openTelemetry);
        return openTelemetry;
    }

    public SdkTracerProvider getTracerProvider() {
        return tracerProvider;
    }

    public SdkMeterProvider getMeterProvider() {
        return meterProvider;
    }

    public OpenTelemetry getOpenTelemetry() {
        return openTelemetry;
    }

//    public MeterRegistry getmeterRegistry() {
//        return registry;
//    }

//    public MeterRegistry meterRegistry(OpenTelemetry opentelemetry) {
//        MeterRegistry registry = OpenTelemetryMeterRegistry.builder(opentelemetry).build();
//
//        new ClassLoaderMetrics().bindTo(registry);
//        new JvmMemoryMetrics().bindTo(registry);
//        new JvmGcMetrics().bindTo(registry);
//        new ProcessorMetrics().bindTo(registry);
//        new JvmThreadMetrics().bindTo(registry);
//
//        return registry;
//    }
}
