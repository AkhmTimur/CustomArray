package com.example.customarray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CustomArrayApplication {

    public static void main(String[] args) {
        ArrayList<String> sda = new ArrayList<>();
        SpringApplication.run(CustomArrayApplication.class, args);
    }

}
