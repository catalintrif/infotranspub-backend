package ro.gov.ithub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.DispatcherServlet;
import ro.gov.ithub.base.BaseRepositoryImpl;

@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "ro.gov.ithub", repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class Main {
    public static void main( String[] args ) throws Exception {
		SpringApplication.run(Main.class, args);
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public ServletRegistrationBean dispatchServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/api/*");
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }
}

