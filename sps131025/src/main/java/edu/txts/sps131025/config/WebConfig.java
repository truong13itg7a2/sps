package edu.txts.sps131025.config;

import edu.txts.sps131025.exception.ExceptionMetrics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(0, new DefaultHandlerExceptionResolver());
	}

	@Bean
	public ExceptionMetrics exceptionMetrics(MeterRegistry meterRegistry) {
		return new ExceptionMetrics(meterRegistry);
	}
}
