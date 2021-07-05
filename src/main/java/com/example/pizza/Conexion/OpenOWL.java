package com.example.pizza.Conexion;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenOWL {
    static  String  s;
//    Abrir una conexión
    static OntModel OpenConnectOWL(){

        OntModel mode = null;
        mode = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_RULE_INF );
        InputStream in = FileManager.get().open("src/main/resources/data/PizzaDesing.owl");
        if (in == null) {
            throw new IllegalArgumentException("Sin base de conocimientos para conectarse");  //no hay archivo para conectar
        }
        return  (OntModel) mode.read(in, "");
    }


    // jena.query.ResultSet  return
    //conjunto resultante
    public static ResultSet ExecSparQl(String Query){
        Query query = QueryFactory.create(Query);
        QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());
        ResultSet results = qe.execSelect();

        return results;  // Return jena.query.ResultSet

    }
    // String return (convert jena.query.ResultSet  to String)
    // Conectado al archivo OWL y devuelve la cadena
    public static String ExecSparQlString(String Query){
        try {
            Query query = QueryFactory.create(Query);
            QueryExecution qe = QueryExecutionFactory.create(query, OpenConnectOWL());
            ResultSet results = qe.execSelect();
            // Test
            if(results.hasNext()){
                // if iS good
                ByteArrayOutputStream go = new ByteArrayOutputStream ();
                ResultSetFormatter.out((OutputStream)go ,results, query);
                //  String s = go.toString();
                s = new String(go.toByteArray(), "UTF-8");
            }
            // not okay
            else{
                s = "no bien";
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OpenOWL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;   // return  jena.query.ResultSet  as string
    }
}
