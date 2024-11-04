package pl.luxmed.testme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestmeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TestmeApplication.class, args);
	}

}
