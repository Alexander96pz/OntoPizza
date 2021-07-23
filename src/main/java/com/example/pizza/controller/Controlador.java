package com.example.pizza.controller;

import com.example.pizza.Persistence.PizzaService;
import com.example.pizza.Persistence.ServicioPizza;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controlador {
    List<String> lista= new ArrayList<>();
    ServicioPizza ServicioPizza = new PizzaService();
    @GetMapping("/listar")
    public List<String> listar(@RequestParam String picante){
        lista=ServicioPizza.listarFuseki(picante);
        return lista;
    }
    @GetMapping("/allPizzasNamed")
    public List<String> listarPizzaNamed(){
        return lista;
    }
}
