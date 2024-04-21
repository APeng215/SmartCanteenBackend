package com.apeng.smartcanteenbackend.repository;


import com.apeng.smartcanteenbackend.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}