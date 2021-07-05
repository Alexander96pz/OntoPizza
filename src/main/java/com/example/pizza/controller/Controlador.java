package com.example.pizza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controlador {
    List<String> lista= new ArrayList<>();
//    @Autowired
//    private ServicioPizza objServicio;

    @GetMapping("/example")
    public String listar(){
//        ServicioPizza objServicioPizza = new PizzaService();
//        lista=objServicioPizza.listar();
//        for (int i = 0; i < lista.size(); i++) {
//
//            System.out.println(lista.get(i));
//        }
        return "Funciona";
    }
}
