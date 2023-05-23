package br.com.aqueteron.jds.integration.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.aqueteron.jds"})
public class DefaultApplication {

    public static void main(final String[] args) {
        SpringApplication.run(DefaultApplication.class, args);
    }

}
