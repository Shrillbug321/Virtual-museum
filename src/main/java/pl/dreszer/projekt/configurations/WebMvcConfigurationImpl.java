package pl.dreszer.projekt.configurations;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebMvcConfigurationImpl implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/403").setViewName("403");

	}

	@Override
	public void addFormatters(FormatterRegistry registry)
	{
		registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
	}
}
