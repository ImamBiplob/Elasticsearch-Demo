package com.imambiplob.elasticsearchdemo.repository;

import com.imambiplob.elasticsearchdemo.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
