package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author frankcoenen
 */
public class WoordenController implements Initializable {

    private static final String DEFAULT_TEXT = "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Heb je dan geen hoedje meer\n"
            + "Maak er één van bordpapier\n"
            + "Eén, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van, hoedje van\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier\n"
            + "\n"
            + "En als het hoedje dan niet past\n"
            + "Zetten we 't in de glazenkas\n"
            + "Een, twee, drie, vier\n"
            + "Hoedje van papier";

    @FXML
    private Button btAantal;
    @FXML
    private TextArea taInput;
    @FXML
    private Button btSorteer;
    @FXML
    private Button btFrequentie;
    @FXML
    private Button btConcordantie;
    @FXML
    private TextArea taOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        //RunTime tijd = O(N)
        int wordCount = 0;
        Set<String> uniqueWords = new HashSet<String>();

        for (String s : this.taInput.getText().replace("\n", " ").replace(",", "").split(" ")) {
            if (!s.equals("")) {
                //System.out.println(s);
                wordCount++;
                uniqueWords.add(s);
            }
        }
        this.taOutput.setText("Totaal aantal woorden:  " + wordCount + "\nAantal verschillende woorden:  " + uniqueWords.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        //RunTime tijd = O(N)
        TreeSet<String> sortedUniqueWords = new TreeSet<String>();

        for (String s : this.taInput.getText().replace("\n", " ").replace(",", "").split(" ")) {
            if (!s.equals("")) {
                sortedUniqueWords.add(s);
            }
        }
        StringBuilder sb = new StringBuilder();

        for (String s : sortedUniqueWords.descendingSet()) {
            sb.append(s + "\n");
        }
        /*
         Iterator it = sortedUniqueWords.descendingIterator();
         while (it.hasNext()) {
         sb.append(it.next() + "\n");
         }*/
        this.taOutput.setText(sb.toString());
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        //Map<String, Integer> freq = new 

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);

        for (String s : this.taInput.getText().replace("\n", " ").replace(",", "").split(" ")) {
            if (!s.equals("")) {
                if (!map.containsKey(s)) {
                    map.put(s, 1);
                } else {
                    map.put(s, map.get(s) + 1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        sorted_map.putAll(map);
        for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {
            sb.append(entry.getKey() + ":    " + entry.getValue() + "\n");
        }

        this.taOutput.setText(sb.toString());

    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        HashSet<String> curr = new HashSet<String>();
        HashMap<String, String> concordatie = new HashMap<>();
        int line = 1;
        
        for (String s : this.taInput.getText().toLowerCase().replace(",", "").split("\n")) {
            if(!s.equals("")){
                for(String sub: s.split(" ")){
                    if (!concordatie.containsKey(sub)) {
                        concordatie.put(sub, Integer.toString(line));
                    } else {
                        concordatie.put(sub, concordatie.get(sub) + ", " + Integer.toString(line));
                    }
                }
            }
            line++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (String key : concordatie.keySet()) {
            sb.append(key + ":  " + concordatie.get(key) + "\n");
        }
        this.taOutput.setText(sb.toString());
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) == base.get(b)) {
            return a.compareTo(b);
        } else {
            if (base.get(a) <= base.get(b)) {
                return -1;
            } else {
                return 1;
            } // returning 0 would merge keys
        }
    }
}
