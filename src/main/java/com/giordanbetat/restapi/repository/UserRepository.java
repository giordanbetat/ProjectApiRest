package com.giordanbetat.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giordanbetat.restapi.model.Person;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

}
