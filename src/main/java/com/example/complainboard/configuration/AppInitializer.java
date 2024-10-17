package com.example.complainboard.configuration;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppInitializer implements ServletContextListener {

    private SdkTracerProvider tracerProvider;
    private SdkMeterProvider meterProvider;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            Properties config = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }

            // Load the properties file
            config.load(input);

            // Load values from configuration file
            String endpoint = config.getProperty("otel.exporter.jaeger.endpoint");
            String serviceName = config.getProperty("otel.resource.attributes.service.name");
            OpenTelemetryConfiguration otelConfig = OpenTelemetryConfiguration.getInstance();
            otelConfig.setEndpoint(endpoint);
            otelConfig.setServiceName(serviceName);

            // OpenTelemetry 초기화
            OpenTelemetry openTelemetry = otelConfig.createOpenTelemetry();

            // Store tracerProvider and meterProvider for shutdown
            this.tracerProvider = otelConfig.getTracerProvider();
            this.meterProvider = otelConfig.getMeterProvider();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (tracerProvider != null) {
            tracerProvider.shutdown().join(1000, TimeUnit.NANOSECONDS);
        }
        if (meterProvider != null) {
            meterProvider.shutdown().join(1000, TimeUnit.NANOSECONDS);
        }
    }
}