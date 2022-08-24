package com.example.firstapplication;

import com.example.firstapplication.repository.AccountRepository;
import com.example.firstapplication.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FirstApplication implements CommandLineRunner {

//    @Autowired
//    private AccountRepository accountRepository;

    public static void main(String[] args) {
        SpringApplication.run(FirstApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        List<Account> accountList = accountRepository.findAll();
//
//        for (Account i:accountList){
//            System.out.println("id: " +i.getId());
//            System.out.println("username: " +i.getUsername());
//            System.out.println("password: " +i.getPassword());
//        }
//        try{
//            String sql = "INSERT INTO accounts (username, password) VALUES('Azamat11', '1234561')";
//
//            jdbcTemplate.update(sql);
//        }catch (Exception e){
//            System.out.println("error:"+e.getMessage());
//        }
    }
}
