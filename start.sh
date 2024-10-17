#!/bin/sh
java -javaagent:/otel/opentelemetry-javaagent.jar \
-Dotel.exporter.otlp.endpoint=${OTEL_EXPORTER_OTLP_ENDPOINT} \
-Dotel.exporter.otlp.headers="Authorization=Bearer ${ELASTIC_APM_SECRET_TOKEN}" \
-Dotel.metrics.exporter=otlp \
-Dotel.logs.exporter=otlp \
-Dotel.resource.attributes=${OTEL_RESOURCE_ATTRIBUTES} \
-Dotel.service.name=${OTEL_SERVICE_NAME} \
-Dotel.javaagent.debug=true \
-jar --server.port=5000