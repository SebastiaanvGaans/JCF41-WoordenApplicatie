package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
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
        Set<String> uniqueWords = new HashSet<>();

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
        TreeSet<String> sortedUniqueWords = new TreeSet<>();

        for (String s : this.taInput.getText().replace("\n", " ").replace(",", "").split(" ")) {
            if (!s.equals("")) {
                sortedUniqueWords.add(s);
            }
        }
        StringBuilder sb = new StringBuilder();

        sortedUniqueWords.descendingSet().stream().forEach((s) -> {
            sb.append(s + "\n");
        });

        this.taOutput.setText(sb.toString());
    }

    @FXML
    private void frequentieAction(ActionEvent event) {
        //Map<String, Integer> freq = new 

        Map<String, Integer> map = new HashMap<>();
        ValueComparator vc = new ValueComparator(map);
        Map<String, Integer> sortedMap = new TreeMap<>(vc);

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

        sortedMap.putAll(map);
        sortedMap.entrySet().stream().forEach((entry) -> {
            sb.append(entry.getKey() + ":    " + entry.getValue() + "\n");
        });

        this.taOutput.setText(sb.toString());

    }

    @FXML
    private void concordatieAction(ActionEvent event) {
        Set<String> curr = new HashSet<>();
        Map<String, String> concordatie = new HashMap<>();
        Set<String> regel = new HashSet<>();
        int line = 1;
        
        for (String s : this.taInput.getText().toLowerCase().replace(",", "").split("\n")) {
            if(!s.equals("")){
                for(String sub: s.split(" ")){
                    regel.add((sub));
                }
                for(String uniqueSub: regel){
                    if (!concordatie.containsKey(uniqueSub)) {
                        concordatie.put(uniqueSub, Integer.toString(line));
                    } else {
                        concordatie.put(uniqueSub, concordatie.get(uniqueSub) + ", " + Integer.toString(line));
                    }
                }
            }
            regel.clear();
            line++;
        }
        
        StringBuilder sb = new StringBuilder();
        concordatie.keySet().stream().forEach((key) -> {
            sb.append(key + ":  " + concordatie.get(key) + "\n");
        });
        this.taOutput.setText(sb.toString());
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Integer> base;

    public ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    @Override
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
