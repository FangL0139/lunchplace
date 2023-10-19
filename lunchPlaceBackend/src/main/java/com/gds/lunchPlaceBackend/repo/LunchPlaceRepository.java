package com.gds.lunchPlaceBackend.repo;

import com.gds.lunchPlaceBackend.entity.LunchPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LunchPlaceRepository extends JpaRepository<LunchPlace, Long> {
    Optional<LunchPlace> findByPlaceName(String placeName);

    @Query(value = "SELECT * FROM lunchplace ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<LunchPlace> findRandomPlace();
}
