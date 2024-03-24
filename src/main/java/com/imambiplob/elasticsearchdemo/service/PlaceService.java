package com.imambiplob.elasticsearchdemo.service;

import com.imambiplob.elasticsearchdemo.entity.EsPlace;
import com.imambiplob.elasticsearchdemo.entity.Place;
import com.imambiplob.elasticsearchdemo.repository.EsPlaceRepository;
import com.imambiplob.elasticsearchdemo.repository.PlaceRepository;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final EsPlaceRepository esPlaceRepository;

    public PlaceService(PlaceRepository placeRepository, EsPlaceRepository esPlaceRepository) {
        this.placeRepository = placeRepository;
        this.esPlaceRepository = esPlaceRepository;
    }

    public Place savePlace(Place place) {
        Place savedPlace = placeRepository.save(place);

        EsPlace esPlace = new EsPlace();
        esPlace.setId(savedPlace.getId());
        esPlace.setName(savedPlace.getName());
        GeoPoint point = new GeoPoint(savedPlace.getLatitude(), savedPlace.getLongitude());
        esPlace.setLocation(point);
        esPlaceRepository.save(esPlace);

        return savedPlace;
    }

    public List<String> findPlacesNearby(Place place, double distance) {
        List<EsPlace> nearbyPlaces = esPlaceRepository.findNearbyLocation(distance + "km", place.getLatitude(), place.getLongitude());

        return nearbyPlaces.stream().map(EsPlace::getName).toList();
    }
}
