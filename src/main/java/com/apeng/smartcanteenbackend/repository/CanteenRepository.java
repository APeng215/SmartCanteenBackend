package com.apeng.smartcanteenbackend.repository;

import com.apeng.smartcanteenbackend.entity.Canteen;
import org.springframework.data.repository.CrudRepository;

public interface CanteenRepository extends CrudRepository<Canteen, String> {
}
