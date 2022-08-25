package com.example.firstapplication.repository;

import com.example.firstapplication.model.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

}
