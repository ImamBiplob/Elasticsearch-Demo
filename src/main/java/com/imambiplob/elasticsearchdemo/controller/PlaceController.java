package com.imambiplob.elasticsearchdemo.controller;

import com.imambiplob.elasticsearchdemo.entity.Place;
import com.imambiplob.elasticsearchdemo.service.PlaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {
    private final PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public Place savePlace(@RequestBody Place place) {
        return placeService.savePlace(place);
    }

    @GetMapping("/searchLocationNearby/{distance}")
    public List<String> searchLocationNearby(@RequestBody Place place, @PathVariable double distance) {
        return placeService.findPlacesNearby(place, distance);
    }
}
