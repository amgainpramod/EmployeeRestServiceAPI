package com.example.EmployeeRestServiceAPI.repository;

import com.example.EmployeeRestServiceAPI.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    City findByCityname(String cityname);

}
