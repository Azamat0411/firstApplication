package com.example.firstapplication.controller;

import com.example.firstapplication.model.Account;
import com.example.firstapplication.model.Model;
import com.example.firstapplication.repository.AccountRepository;
import com.sun.istack.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    private AccountRepository accountRepository;

    @GetMapping("/account")
    public Model getAccounts(){
        return new Model(true, this.accountRepository.findAll());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Model> getAccount(@PathVariable(value = "id") Long id){
        try{
            Optional<Account> account = accountRepository.findById(id);
            Model model = account.isPresent()?new Model(true, account):new Model(false);
            return ResponseEntity.ok().body(model);
        }catch (Exception e){
            System.out.println("error:" + e.getMessage());
            return ResponseEntity.ok().body(new Model(false, new ArrayList<>()));
        }

    }

    @PostMapping("/create-account")
    public Model createAccount(@RequestBody @NotNull Account body){
        try{
            if(body == null){
                return new Model(false, new ArrayList<>());
            }
            if(body.getPassword() == null){
                return new Model(false, new ArrayList<>());
            }
            if(body.getUsername() == null){
                return new Model(false, new ArrayList<>());
            }
            boolean isDuplicate = false;
            for(Account a:accountRepository.findAll()){
                if(Objects.equals(a.getUsername(), body.getUsername())){
                    isDuplicate = true;
                    break;
                }
            }
            if(!isDuplicate){
                accountRepository.save(body);
                return new Model(true, body);
            }
            return new Model(false, "Username is duplicate");
        }catch (Exception e){
            return new Model(false);
        }

    }
}
