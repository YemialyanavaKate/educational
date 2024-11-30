package by.ita.je;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class DataBaseApp {

    public static void main(String[] args) {
        SpringApplication.run(DataBaseApp.class, args);
    }
}