package com.example.pizza.controller;

import com.example.pizza.Persistence.PizzaService;
import com.example.pizza.Persistence.ServicioPizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controlador {
    List<String> lista= new ArrayList<>();
    @GetMapping("/listar")
    public List<String> listar(){
        ServicioPizza objServicioPizza = new PizzaService();
        lista=objServicioPizza.listar();
        return lista;
    }
}
