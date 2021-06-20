package com.example.EmployeeRestServiceAPI.controller;

import com.example.EmployeeRestServiceAPI.entities.Country;
import com.example.EmployeeRestServiceAPI.repository.CountryRepository;
import com.example.EmployeeRestServiceAPI.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CountryController {
    @Autowired
    CityService cityService;

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("getCountryById")
    public Country getCountryById(@RequestParam(value = "country_id")int id){
        return cityService.getCountryById(id);
    }

    @GetMapping("getCountryByName")
    public Country getCountryByName(@RequestParam(value = "country_name")String countryName){
        return cityService.getCountryByName(countryName);
    }

    //This is JPL Query Method
    @GetMapping("getCountryByPrefix")
    public List<Country> getCountryByPrefix(@RequestParam(value = "country_prefix")String prefix){
        return cityService.getCountryByPrefix(prefix);
    }

    //This is the regular method
    @GetMapping("getCountryStartWith")
    public List<Country> getCountryStartingWith(@RequestParam(value = "country_start_with")String prefix){
        return countryRepository.findByCountrynameStartingWith(prefix);
    }

    @GetMapping("getByCountryNameAndPopulation")
    public List<Object[]> getByCountryNameAndPopulation(@RequestParam(value = "country_name") String countryName, @RequestParam(value = "population") long population){
        return cityService.getByCountryNameAndPopulation(countryName, population);
    }

    //NATIVE JPL QUERY
    @GetMapping("getByCountryNameAndPopulationNative")
    public List<Object[]> getByCountryNameAndPopulationNative(@RequestParam(value = "country_name") String countryName, @RequestParam(value = "population") long population){
        return cityService.getByCountryNameAndPopulationNative(countryName, population);
    }

    @GetMapping("getCountriesByListIds")
    public List<Country> getCountriesByListIds(@RequestBody Set<Integer> ids){
        return cityService.getCountriesByListIds(ids);
    }
}
