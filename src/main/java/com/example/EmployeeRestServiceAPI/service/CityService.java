package com.example.EmployeeRestServiceAPI.service;

import com.example.EmployeeRestServiceAPI.entities.City;
import com.example.EmployeeRestServiceAPI.entities.Country;
import com.example.EmployeeRestServiceAPI.repository.CityRepository;
import com.example.EmployeeRestServiceAPI.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    CountryRepository countryRepository;

    public CityService() {

    }

    public List<City> getCities() {

        return cityRepository.findAll();

    }

    public City saveCity(City city) {

        return cityRepository.save(city);

    }

    public City getCity(String cityname) {
        return cityRepository.findByCityname(cityname);
    }

    public Country getCountryById(int id) {
        return countryRepository.getById(id);
    }

    public Country getCountryByName(String countryName) {
        return countryRepository.getByCountryName(countryName);
    }

    public List<Country> getCountryByPrefix(String prefix) {
       return countryRepository.getByCountryPrefix(prefix);
    }

    public List<Object[]> getByCountryNameAndPopulation(String countryName, long population) {
        return countryRepository.getByCountryNameAndPopulation(countryName, population);
    }

    //This is for NATIVE JPL QUERY
    public List<Object[]> getByCountryNameAndPopulationNative(String countryName, long population) {
        return countryRepository.getByCountryNameAndPopulationNative(countryName, population);
    }

    public List<Country> getCountriesByListIds(Set<Integer> ids) {
        return countryRepository.getCountriesByIds(ids);
    }
}
