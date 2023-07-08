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

import com.example.springapp.model.MenuItem;
import com.example.springapp.service.MenuItemService;

@RestController
@RequestMapping("/menu-item")
public class MenuItemController {
    
    @Autowired
    private MenuItemService menuItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createMenuItem(@RequestBody MenuItem m) {
        menuItemService.createMenuItem(m);
        return "Menu Item Created";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem createMenu(@RequestParam Long restaurantId ,@RequestBody MenuItem m) {
        return menuItemService.createMenu(restaurantId,m);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getAllMenuItem() {
        return menuItemService.getAllMenuItem();
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public List<MenuItem> getMenuItemById(@RequestParam String id) {
        long itemId = Long.parseLong(id.replaceAll("[^0-9]", ""));
        MenuItem item = menuItemService.getMenuItemById(itemId);
        List<MenuItem> items = new ArrayList<>();
        items.add(item);
        return items;
    }

    
}
