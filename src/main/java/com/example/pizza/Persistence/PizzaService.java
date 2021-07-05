package com.example.pizza.Persistence;


import java.util.ArrayList;
import java.util.List;

public class PizzaService implements ServicioPizza{
    public List<String> ListPizzas = new ArrayList<>();

    @Override
    public List<String> listar() {
        return ListPizzas;
    }
}
