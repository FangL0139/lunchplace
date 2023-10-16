package com.gds.lunchPlaceBackend.repo;

import com.gds.lunchPlaceBackend.entity.LunchPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchPlaceRepository extends JpaRepository<LunchPlace, Long> {
}
