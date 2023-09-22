package com.example.complainboard.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//This annotation marks the class as a configuration class, indicating that it will provide configuration to the Spring application context.
@Configuration

//This annotation is used to enable Spring's web MVC framework. It is typically used when you want to set up a Spring MVC application. It activates the necessary configurations for handling web requests and responses.
@EnableWebMvc

//This annotation is used to specify the location of a properties file that contains application-specific configuration properties. In this case, it's looking for a file named "app.properties" in the classpath.
@PropertySource("classpath:app.properties")

//This class is declared as AppConfiguration and implements the WebMvcConfigurer interface. This means it will provide custom configurations for Spring's Web MVC framework.
public class AppConfiguration implements WebMvcConfigurer {

    //This annotation is used to inject a value from a property source (in this case, "app.properties") into the upload field. The ${file-upload} expression indicates that the value of upload should be retrieved from the "file-upload" property in the "app.properties" file.
    @Value("${file-upload}")
    private String upload;

    //This annotation indicates that the following method is intended to override a method from an interface (in this case, addResourceHandlers from the WebMvcConfigurer interface).
    @Override

    //This method is part of the WebMvcConfigurer interface and is used to configure resource handling. It allows you to define how resources (e.g., images, CSS, JavaScript) are served to the client.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    //This line configures a resource handler for URLs that match the pattern "/image/**". This means that any URL starting with "/image/" will be handled by this resource handler.
        registry.addResourceHandler("/image/**")

    // This line specifies the location from which resources matching the "/image/**" pattern should be served. It uses the value of the upload field, which was injected from the "app.properties" file. The "file:" prefix indicates that these resources are stored on the file system, and upload should contain the file path.
                .addResourceLocations("file:" + upload);
    }

    //This annotation is used to define a Spring bean named "multipartResolver." This bean is typically used for handling file uploads in a web application.
    @Bean(name = "multipartResolver")

    //This method defines and configures the CommonsMultipartResolver bean.
    public CommonsMultipartResolver getResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

    //This line sets the maximum upload size for each file to 52,428,800 bytes (approximately 50 megabytes). This configuration restricts the size of individual files that can be uploaded in the application.
        resolver.setMaxUploadSizePerFile(52428800);
        return resolver;
    }
}
