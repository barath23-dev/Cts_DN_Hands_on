package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Search by partial name containing text
    List<Country> findByNameContainingOrderByNameAsc(String text);

    // Search by starting alphabet
    List<Country> findByNameStartingWith(String letter);
}
