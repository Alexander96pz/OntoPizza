package com.example.pizza.Conexion;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.query.DatasetAccessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FusekiConexion {
    public static void uploadRDF(File rdf, String serviceURI)
            throws IOException {

        // parse the file
        Model m = ModelFactory.createDefaultModel();
        try (FileInputStream in = new FileInputStream(rdf)) {
            m.read(in, null, "RDF/XML");
        }

        // upload the resulting model
        DatasetAccessor accessor = DatasetAccessorFactory
                .createHTTP(serviceURI);
        accessor.putModel(m);
    }

    public static List<String> execSelectAndPrint(String serviceURI, String query) {
        List<String> ListPizzas = new ArrayList<>();
        QueryExecution q = QueryExecutionFactory.sparqlService(serviceURI,
                query);
        ResultSet results = q.execSelect();
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            String resp = soln.getResource("s").getLocalName();
            if (resp != null){
                ListPizzas.add(resp.toString());
            }
        }
        System.out.println(ListPizzas);
        return ListPizzas;
    }

    public static void execSelectAndProcess(String serviceURI, String query) {

        QueryExecution q = QueryExecutionFactory.sparqlService(serviceURI,
                query);
        ResultSet results = q.execSelect();
        System.out.println(results);
        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            // assumes that you have an "?x" in your query
            RDFNode x = soln.get("x");
            System.out.println(x);
        }

    }
}
