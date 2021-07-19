package com.example.pizza.Persistence;


import com.example.pizza.Conexion.OpenOWL;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class PizzaService implements ServicioPizza{
    public List<String> ListPizzas = new ArrayList<>();

    @Override
    public List<String> listar(String nivel) {
        try {
            String queryString;
            queryString = "PREFIX pizza:<http://www.semanticweb.org/marlonl/ontologies/2021/PizzaTutorial#>"
                    + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + "PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                    + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                    + "SELECT DISTINCT ?s "
                    + "WHERE {  ?s rdfs:subClassOf ?restriction . " +
                    " ?restriction owl:onProperty pizza:hasSpiciness . " +
                    " ?restriction owl:hasValue pizza:"+nivel +".}";

            ResultSet results = OpenOWL.ExecSparQl(queryString);
//            String results = OpenOWL.ExecSparQlString(queryString);
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                String NameOfPerson = soln.getResource("s").getLocalName();
//                 Literal NameOfPerson = soln.getLiteral("Pizza");
                if (NameOfPerson != null){
                    ListPizzas.add(NameOfPerson.toString());
                }
            }
            return ListPizzas;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("entra");
        }
        return null;
    }
}
