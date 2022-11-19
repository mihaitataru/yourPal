package com.fourengineering.yourpal2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("Entities")
public class YourPal2Application {

    public static void main(String[] args) {
        SpringApplication.run(YourPal2Application.class, args);
    }

}
