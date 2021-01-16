package com.resliv.telegram.repository;

import com.resliv.telegram.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface JpaCitiesRepository extends JpaRepository<City, String> {
    Optional<City> getByName(String name);
}
