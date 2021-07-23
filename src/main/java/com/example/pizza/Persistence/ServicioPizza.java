package com.example.pizza.Persistence;

import java.util.List;

public interface ServicioPizza {
    List<String> listarFuseki(String nivel);
    List<String> allPizzaNamed();
}
