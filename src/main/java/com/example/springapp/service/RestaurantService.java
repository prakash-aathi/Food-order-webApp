package com.example.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import com.example.springapp.model.MenuItem;
import com.example.springapp.model.Restaurant;
import com.example.springapp.repository.MenuItemRepo;
import com.example.springapp.repository.ResturantRepo;

@Service
public class RestaurantService {
    
    @Autowired
    private ResturantRepo restaurantRepo;

    @Autowired
    private MenuItemRepo menuItemRepo;

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepo.findById(restaurantId)
            .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    public Restaurant findByRestaurantName(String restaurantName) {
        return restaurantRepo.findByRestaurantName(restaurantName)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    @Transactional
    public void linkRestaurant(Long restaurantId, Long menuItemId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        System.out.println("Restaurant: " + restaurant);
        MenuItem menuItem = menuItemRepo.findById(menuItemId)
                .orElseThrow(() -> new RuntimeException("Menu Item not found"));
        System.out.println("Menu Item: " + menuItem);
        System.out.println("Restaurant Menu: " + restaurant.getRestaurantmenu());
        restaurant.getRestaurantmenu().add(menuItem);
        }
        
}
