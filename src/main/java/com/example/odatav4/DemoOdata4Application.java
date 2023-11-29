package com.example.odatav4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // servlet auto register
@SpringBootApplication
public class DemoOdata4Application {

    public static void main(String[] args) {
        SpringApplication.run(DemoOdata4Application.class, args);
    }

}
