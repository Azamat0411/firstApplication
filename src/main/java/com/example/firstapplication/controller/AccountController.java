package com.example.firstapplication.controller;

import com.example.firstapplication.model.Account;
import com.example.firstapplication.model.Model;
import com.example.firstapplication.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountController {

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    private final AccountRepository accountRepository;

    @GetMapping("/account")
    public Model getAccounts(){
        return new Model(true, this.accountRepository.findAll(), "");
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Model> getAccount(@PathVariable(value = "id") Long id){
        try{
            Optional<Account> account = accountRepository.findById(id);
            Model model = account.isPresent()?new Model(true, account, ""):new Model(false, new HashMap<>(), "User not found");
            return ResponseEntity.ok().body(model);
        }catch (Exception e){
            System.out.println("error:" + e.getMessage());
            return ResponseEntity.ok().body(new Model(false, new HashMap<>(), "User not found"));
        }

    }

    @PostMapping("/create-account")
    public Model createAccount(@RequestBody Account body){
        try{
            boolean isDuplicate = false;
            if(body == null){
                return new Model(false, new HashMap<>(), "Body isn't null");
            }
            if(body.getUsername() == null){
                return new Model(false, new HashMap<>(), "Username isn't null");
            }
            if(body.getPassword() == null){
                return new Model(false, new HashMap<>(), "Password isn't null");
            }
            for(Account a:accountRepository.findAll()){
                if(Objects.equals(a.getUsername(), body.getUsername())){
                    isDuplicate = true;
                    break;
                }
            }
            if(!isDuplicate){
                Account account = accountRepository.save(new Account(body.getUsername(), body.getPassword()));
                return new Model(true, account, "");
            }
            return new Model(false, new HashMap<>(), "Username is duplicate");
        }catch (Exception e){
            return new Model(false);
        }

    }
}
