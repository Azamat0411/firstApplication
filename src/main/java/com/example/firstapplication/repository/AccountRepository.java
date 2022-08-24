package com.example.firstapplication.repository;

import com.example.firstapplication.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
