package com.example.EmployeeRestServiceAPI.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name ="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="country_id")
    private int id;

    @NonNull
    @Column(name="country_name")
    private String countryname;

    @NonNull
    @Column(name = "population")
    private long population;

    //private int gdp <----gdp undefined in Country class for privacy. In this case JPL QUERY comes in handy to do so

    @JsonIgnore
    @OneToMany
    @JoinColumn(name ="country_id")
    private Set<City> cities;

    public Country() {
        countryname = "NA";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }


}

