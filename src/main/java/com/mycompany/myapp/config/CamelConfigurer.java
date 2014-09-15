package com.mycompany.myapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("camel-ctx.xml")
public class CamelConfigurer {
}
