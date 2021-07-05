package com.example.GUI;

import com.example.Conexion.OpenOWL;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FormPizza extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    public ArrayList<String> ListPizzas = new ArrayList<>();

    public FormPizza() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
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
                    " ?restriction owl:hasValue pizza:Hot .}";

            ResultSet results = OpenOWL.ExecSparQl(queryString);
//            String results = OpenOWL.ExecSparQlString(queryString);
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                String NameOfPerson = soln.getResource("s").getLocalName();
//                 Literal NameOfPerson = soln.getLiteral("Pizza");
                if (NameOfPerson != null){
                    System.out.println(NameOfPerson);
                    ListPizzas.add(NameOfPerson.toString());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("entra");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        FormPizza dialog = new FormPizza();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
