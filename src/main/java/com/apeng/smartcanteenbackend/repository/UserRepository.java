package com.apeng.smartcanteenbackend.repository;


import com.apeng.smartcanteenbackend.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}