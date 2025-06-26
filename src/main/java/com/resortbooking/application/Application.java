package com.resortbooking.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableMethodSecurity // ✅ enables @PreAuthorize
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
