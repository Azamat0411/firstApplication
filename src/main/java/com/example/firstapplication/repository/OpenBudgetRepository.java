package com.example.firstapplication.repository;

import com.example.firstapplication.model.OpenBudgetAccount;
import org.springframework.data.repository.CrudRepository;

public interface OpenBudgetRepository extends CrudRepository<OpenBudgetAccount, Long> {
}
