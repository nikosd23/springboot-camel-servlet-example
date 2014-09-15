package com.mycompany.myapp.config;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.ContextLoaderListener;

import javax.inject.Inject;
import javax.servlet.*;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfiguration implements ServletContextInitializer {

	private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

	@Inject
	private Environment env;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
		initCamelServlet(servletContext);
		log.info("Web application fully configured");
	}



	/**
	 * Initializes Metrics.
	 */
	private void initCamelServlet(ServletContext servletContext) {
		ServletRegistration.Dynamic metricsAdminServlet =
				servletContext.addServlet("CamelExampleServlet", new CamelHttpTransportServlet());

		metricsAdminServlet.addMapping("/example/*");
		metricsAdminServlet.setAsyncSupported(true);
		metricsAdminServlet.setLoadOnStartup(1);
	}


}
