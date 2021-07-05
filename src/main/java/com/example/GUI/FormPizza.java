package com.example.GUI;

import com.example.controller.OpenOWL;
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
            queryString = "PREFIX pz:<http://www.semanticweb.org/paul/ontologies/2021/5/mypizza#> "
                    + "SELECT  (str(?x) as ?Pizza) "
                    + "where { ?y pz:desc ?x. }";

            ResultSet results = OpenOWL.ExecSparQl(queryString); //all method ExecSparQl from OpenOWL class
            while (results.hasNext()) {
                QuerySolution soln = results.nextSolution();
                String NameOfPerson = soln.getLiteral("Pizza").getString();
                System.out.println(NameOfPerson.toString());
                ListPizzas.add(NameOfPerson.toString());

//                RDFNode x = soln.get("Propertyval");
//                String xx = String.valueOf(x);
//                java.nio.ByteBuffer xxx = Charset.forName("UTF-8").encode(xx);
//                String xs = xxx.toString();
//                System.out.println(xs);
            }
//            ComboBoxModel
//            PizzaDescList.removeAllItems(); //  combobox nameList
//            for (int i = 0; i < ListPizzas.size(); i++) {
//
//                PizzaDescList.addItem(ListPizzas.get(i));
//            }
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
