package com.example.springapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapp.model.MenuItem;
import com.example.springapp.model.Restaurant;
import com.example.springapp.repository.MenuItemRepo;
import com.example.springapp.repository.ResturantRepo;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepo menuItemRepo;

    @Autowired
    private ResturantRepo restaurantRepo;

    public boolean createMenuItem(MenuItem m) {
        menuItemRepo.save(m);
        return true;
    }
    
    public List<MenuItem> getAllMenuItem() {
        return menuItemRepo.findAll();
    }

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu Item not found with id: " + id));
    }

    @Transactional
    public MenuItem createMenu(Long restaurantId, MenuItem m) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        MenuItem menuItem = menuItemRepo.save(m);
        restaurant.getRestaurantmenu().add(menuItem);
        return menuItem;
    }

    
   
    
}
