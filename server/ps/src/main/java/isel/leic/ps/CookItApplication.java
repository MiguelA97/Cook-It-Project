package isel.leic.ps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@SpringBootApplication
public class CookItApplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(CookItApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("locales/messages");
		return messageSource;
	}
}