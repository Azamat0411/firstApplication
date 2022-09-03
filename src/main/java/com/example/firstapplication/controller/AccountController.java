package com.example.firstapplication.controller;

import com.example.firstapplication.model.Account;
import com.example.firstapplication.model.Application;
import com.example.firstapplication.model.OpenBudgetAccount;
import com.example.firstapplication.repository.AccountRepository;
import com.example.firstapplication.repository.ApplicationRepository;
import com.example.firstapplication.repository.OpenBudgetRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AccountController {

    public AccountController(AccountRepository accountRepository, ApplicationRepository applicationRepository, OpenBudgetRepository openBudgetRepository) {
        this.accountRepository = accountRepository;
        this.applicationRepository = applicationRepository;
        this.openBudgetRepository = openBudgetRepository;
    }

    private final AccountRepository accountRepository;
    private final ApplicationRepository applicationRepository;
    private final OpenBudgetRepository openBudgetRepository;

    @GetMapping("/account")
    public Map<String, Object> getAccounts() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", true);
        response.put("account", accountRepository.findAll());
        response.put("error", "");
        return response;
    }

    @GetMapping("/account/{id}")
    public Map<String, Object> getAccount(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Optional<Account> account = accountRepository.findById(id);
            response.put("status", account.isPresent());
            if (account.isPresent()) {
                response.put("account", account);
                response.put("error", "");
            } else {
                response.put("account", new HashMap<>());
                response.put("error", "User not found");
            }
            return response;
        } catch (Exception e) {
            response.put("status", false);
            response.put("account", new HashMap<>());
            response.put("error", "User found");
            return response;
        }
    }

    @PostMapping("/create-account")
    public Map<String, Object> createAccount(@RequestBody Account body) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            boolean isDuplicate = false;
            if (body == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Body isn't null");
                return response;
            }
            if (body.getUsername() == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Username isn't null");
                return response;
            }
            if (body.getPassword() == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Password isn't null");
                return response;
            }
            for (Account a : accountRepository.findAll()) {
                if (Objects.equals(a.getUsername(), body.getUsername())) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                Account account = accountRepository.save(new Account(body.getUsername(), body.getPassword()));
                response.put("status", true);
                response.put("account", account);
                response.put("error", "");
                return response;
            }
            response.put("status", false);
            response.put("account", new HashMap<>());
            response.put("error", "Username is duplicate");
            return response;
        } catch (Exception e) {
            response.put("status", false);
            return response;
        }
    }

    @PutMapping("/account/{id}")
    public Map<String, Object> updateAccount(@RequestBody Account body, @PathVariable(value = "id") Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            boolean isDuplicate = false;
            if (body == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Body isn't null");
                return response;
            }
            if (body.getUsername() == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Username isn't null");
                return response;
            }
            if (body.getPassword() == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Password isn't null");
                return response;
            }
            for (Account a : accountRepository.findAll()) {
                if (Objects.equals(a.getUsername(), body.getUsername())) {
                    isDuplicate = true;
                    break;
                }
            }
            Account account = accountRepository.findById(id).orElse(null);
            if (account != null) {
                if (!isDuplicate || body.getUsername().equals(account.getUsername())) {
                    account.setUsername(body.getUsername());
                    account.setPassword(body.getPassword());
                    Account a = accountRepository.save(account);
                    response.put("status", true);
                    response.put("account", a);
                    return response;
                }
                response.put("status", false);
                response.put("error", "Username is duplicate");
                return response;
            }
            response.put("status", false);
            response.put("error", "User not found");
            return response;
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "User not found");
            return response;
        }
    }

    @DeleteMapping("/account/{id}")
    public Map<String, Object> deleteAccount(@PathVariable(value = "id") Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Account account = accountRepository.findById(id).orElse(null);
            if (account != null) {
                accountRepository.delete(account);
                response.put("status", true);
                return response;
            }
            response.put("status", false);
            response.put("error", "User not found");
            return response;
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "User not found");
            return response;
        }
    }

    @GetMapping("/application")
    public Map<String, Object> getApplication(){
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", true);
        response.put("application", applicationRepository.findAll());
        return response;
    }

    @PostMapping("/application")
    public Map<String, Object> createApplication(@RequestBody Application body){
        Map<String, Object> response = new LinkedHashMap<>();
        if(body.getTitle().isEmpty()){
            response.put("status", false);
            return response;
        }
        Application a = applicationRepository.save(body);
        response.put("status", true);
//        response.put("application", a);
        return response;
    }

    @GetMapping("/openBudgetAccount/{id}")
    public Map<String, Object> getOpenBudgetAccount(@PathVariable(value = "id") Long id){
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Optional<OpenBudgetAccount> account = openBudgetRepository.findById(id);
            response.put("status", account.isPresent());
            if (account.isPresent()) {
                response.put("account", account);
//                response.put("error", "");
            } else {
                response.put("account", new HashMap<>());
//                response.put("error", "User not found");
            }
            return response;
        } catch (Exception e) {
            response.put("status", false);
            response.put("account", new HashMap<>());
            response.put("error", "User found");
            return response;
        }
    }

    @PutMapping("/openBudgetAccount/{id}")
    public Map<String, Object> updateOpenBudgetAccount(@RequestBody OpenBudgetAccount body, @PathVariable(value = "id") Long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
//            boolean isDuplicate = false;
            if (body == null) {
                response.put("status", false);
                response.put("account", new HashMap<>());
                response.put("error", "Body isn't null");
                return response;
            }
//            if (body.getId() == null) {
//                response.put("status", false);
//                response.put("account", new HashMap<>());
//                response.put("error", "Username isn't null");
//                return response;
//            }
//            if (body.getApplication() == null) {
//                response.put("status", false);
//                response.put("account", new HashMap<>());
//                response.put("error", "Password isn't null");
//                return response;
//            }
//            for (OpenBudgetAccount a : openBudgetRepository.findAll()) {
//                if (Objects.equals(a.getId(), body.getUsername())) {
//                    isDuplicate = true;
//                    break;
//                }
//            }
            OpenBudgetAccount account = openBudgetRepository.findById(id).orElse(null);
            if (account != null) {
                account.setApplication(body.getApplication());
                openBudgetRepository.save(account);
                response.put("status", true);
                return response;
//                if (body.getUsername().equals(account.getUsername())) {
//                    account.setUsername(body.getUsername());
//                    account.setPassword(body.getPassword());
//                    Account a = accountRepository.save(account);
//                    response.put("status", true);
//                    response.put("account", a);
//                    return response;
//                }
//                response.put("status", false);
//                response.put("error", "Username is duplicate");
//                return response;
            }
            response.put("status", false);
            response.put("error", "User not found");
            return response;
        } catch (Exception e) {
            response.put("status", false);
            response.put("error", "User not found");
            return response;
        }
    }


    @PostMapping("/openBudgetAccount")
    public Map<String, Object> createOpenBudgetAccount(@RequestBody OpenBudgetAccount body){
        Map<String, Object> response = new LinkedHashMap<>();
        if(body.getId() == null){
            response.put("status", false);
            return response;
        }
        if(body.getApplication() == null){
            response.put("status", false);
            return response;
        }
        OpenBudgetAccount a = openBudgetRepository.save(body);
        response.put("status", true);
        return response;
    }
}























