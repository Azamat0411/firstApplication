package com.example.firstapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "openBudgetAccount")
public class OpenBudgetAccount {
    public OpenBudgetAccount(){}

    public OpenBudgetAccount(Long id, String application) {
        this.id = id;
        this.application = application;
    }

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "application")
    private String application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
