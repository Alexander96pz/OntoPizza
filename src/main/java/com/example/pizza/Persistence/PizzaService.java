package com.example.pizza.Persistence;


import com.example.pizza.Conexion.OpenOWL;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import com.example.pizza.Conexion.FusekiConexion;
import java.util.ArrayList;
import java.util.List;

public class PizzaService implements ServicioPizza{
    public List<String> ListPizzas = new ArrayList<>();
    String serviceUri="http://localhost:3030/ds";
    String query="";
    @Override
    public List<String> listarFuseki(String picante) {

        query="PREFIX pizza:<http://www.semanticweb.org/marlonl/ontologies/2021/PizzaTutorial#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                + "SELECT DISTINCT ?s "
                + "WHERE {  ?s rdfs:subClassOf ?restriction . " +
                " ?restriction owl:onProperty pizza:hasSpiciness . " +
                " ?restriction owl:hasValue pizza:Hot .}";
        return ListPizzas= FusekiConexion.execSelectAndPrint(serviceUri,query);
    }

    @Override
    public List<String> allPizzaNamed() {
        query = "PREFIX pizza:<http://www.semanticweb.org/marlonl/ontologies/2021/PizzaTutorial#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                + "SELECT ?s "
                + "WHERE {  ?s rdfs:subClassOf pizza:NamedPizza }";
        return ListPizzas=FusekiConexion.execSelectAndPrint(serviceUri,query);
    }
    @Override
    public List<String> ListarIngredientes() {
//
//        query = "PREFIX pizza:<http://www.semanticweb.org/marlonl/ontologies/2021/PizzaTutorial#>"
//                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
//                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
//                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
//                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
//                + "SELECT ?s "
//                + "WHERE {  ?s rdfs:subClassOf pizza:NamedPizza }";
        return ListPizzas=FusekiConexion.execSelectAndPrint(serviceUri,query);
    }
    @Override
    public List<String> IndividuosPizza() {
        query = "PREFIX pizza:<http://www.semanticweb.org/marlonl/ontologies/2021/PizzaTutorial#>"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                + "select ?x ?y "
                + "WHERE { ?x rdfs:hasPizza ?y }";
        return ListPizzas=FusekiConexion.execSelectAndPrint(serviceUri,query);
    }

}
