package com.example.firstapplication.controller;

import com.example.firstapplication.model.Account;
import com.example.firstapplication.model.Model;
import com.example.firstapplication.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    private final AccountRepository accountRepository;

    @GetMapping("/account")
    public Map<String, Object> getAccounts(){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", true);
        response.put("account", accountRepository.findAll());
        response.put("error", "");
        return response;
    }

    @GetMapping("/account/{id}")
    public Map<String, Object> getAccount(@PathVariable(value = "id") Long id){
        Map<String, Object> response = new LinkedHashMap<>();
        try{
            Optional<Account> account = accountRepository.findById(id);
            response.put("status", account.isPresent());
            if(account.isPresent()){
                response.put("account", account);
                response.put("error", "");
            }else{
                response.put("account", new HashMap<>());
                response.put("error", "User not found");
            }
            return response;
        }catch (Exception e){
            response.put("status", false);
            response.put("account", new HashMap<>());
            response.put("error", "User found");
            return response;
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
