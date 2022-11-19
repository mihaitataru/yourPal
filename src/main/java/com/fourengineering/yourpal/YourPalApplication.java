package com.fourengineering.yourpal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.fourengineering.yourpal.Entities")
public class YourPalApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourPalApplication.class, args);
    }

}
