package com.apeng.smartcanteenbackend.repository;


import com.apeng.smartcanteenbackend.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, String> {
}