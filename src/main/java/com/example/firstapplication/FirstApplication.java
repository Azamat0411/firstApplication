package com.example.firstapplication;

import com.example.firstapplication.repository.AccountRepository;
import com.example.firstapplication.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FirstApplication{

    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class, args);
    }
}
