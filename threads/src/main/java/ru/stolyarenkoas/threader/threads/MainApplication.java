package ru.stolyarenkoas.threader.threads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Main class to run application.
 *
 * @author Alexander Stolyarenko (28.05.2021)
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
