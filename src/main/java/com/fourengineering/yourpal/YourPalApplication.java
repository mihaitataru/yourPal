package com.fourengineering.yourpal;

import com.fourengineering.yourpal.Services.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Base64;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EntityScan("com.fourengineering.yourpal.Entities")
public class YourPalApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourPalApplication.class, args);
    }

}
