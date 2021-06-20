package com.example.EmployeeRestServiceAPI.repository;

import com.example.EmployeeRestServiceAPI.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findById(int id);

    Country findByCountryname(String name);


    //JPL QUERY
    @Query("from Country where id = ?1") //<---1 is replaced by any country_id value sent
    Country getById(int id);

    @Query("from Country where countryname like ?1")
    Country getByCountryName(String countryName);


    @Query("SELECT c FROM Country c Where c.countryname like ?1% ORDER BY c.population ASC")
    List<Country> getByCountryPrefix(String prefix);

    //What if i have used previous way of querying?? Does it say anything about gdp field not declared?
    //Lets see....
    List<Country> findByCountrynameStartingWith(String prefix);
    //Nop its the same result. Just that its more readable with @Query



    //?1 refers to the first position in the parameter. This is called bind by position
    //:name refers to biding by name. it is independent of position
    //This approach is more preferable

    /***
     *
     * {
     *         "id": 151,
     *         "countryname": "Nepal",
     *         "population": 128405720
     *     }
     *
     */
    //Also you can select explicitly which fields to show in respose body by querying to select only those particular fields like given below
    //Here we will only select country name and population
    //But since we are missing id here, JPA will fail to map the result to Country type
    //We can simply replace the return type with either pojo class with custom class or simply return array of Object
    @Query("SELECT c.countryname, c.population FROM Country c WHERE c.countryname like :countryname% AND c.population >= :population")
    List<Object[]> getByCountryNameAndPopulation(@Param("countryname") String countryName, @Param("population") long population);

    /***
     *
     *[
     *         "Nepal",
     *         128405720
     *     ]
     *
     *
     */

    //Now Lets replicate the above JPL Queries to Native Queries
    //With this we can even access the GDP data from table which is not defined in the Country entity. We just need to add gpd column in our query
    //This is very cool

    //We can write query to fetch data from multiple table

    //Lets fetch data from cityname column along with the privided data

    @Query(value = "SELECT c.country_name, c.population, c.gdp, ct.city_name FROM countries c LEFT JOIN cities ct ON c.country_id = ct.country_id " +
            "WHERE c.country_name like :countryname% AND c.population >= :population", nativeQuery = true)
    List<Object[]> getByCountryNameAndPopulationNative(@Param("countryname") String countryName, @Param("population") long population);

    @Query(value = "SELECT c.country_id, c.population, c.country_name FROM countries c WHERE country_id IN (?1)", nativeQuery = true)
    List<Country> getCountriesByIds(Set<Integer> ids);
}
