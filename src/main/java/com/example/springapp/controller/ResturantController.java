package com.example.springapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapp.model.Restaurant;
import com.example.springapp.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
public class ResturantController {
    
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurant();
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getRestaurantById(@RequestParam Long id) {
        Restaurant onerRestaurant = restaurantService.getRestaurantById(id);
        ArrayList<Restaurant> oneRestaurantList = new ArrayList<>();
        oneRestaurantList.add(onerRestaurant);
        return oneRestaurantList;
    }

    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public List<Restaurant> getRestaurantByName(@RequestParam String name) {
        Restaurant oneRestaurant = restaurantService.findByRestaurantName(name);
        ArrayList<Restaurant> oneRestaurantList = new ArrayList<>();
        oneRestaurantList.add(oneRestaurant);
        return oneRestaurantList;
    }

    @PostMapping("/link")
    @ResponseStatus(HttpStatus.CREATED)
    public String linkRestaurant(@RequestParam Long restaurantId, @RequestParam Long menuItemId) {
        restaurantService.linkRestaurant(restaurantId, menuItemId);
        return "Restaurant linked to menu item";
    }


}
