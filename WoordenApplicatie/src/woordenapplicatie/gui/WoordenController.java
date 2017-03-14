package woordenapplicatie.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ArrayList;
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
    
    private WoordenWerker ww = new WoordenWerker();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taInput.setText(DEFAULT_TEXT);
    }

    @FXML
    private void aantalAction(ActionEvent event) {
        //RunTime tijd = O(N)
        int wordCount = 0;
        Set<String> uniqueWords = new HashSet<>();

        for (String s : ww.woordenOpsplitsen(this.taInput.getText())) {
            System.out.println(s);
            wordCount++;
            uniqueWords.add(s);
        }
        this.taOutput.setText("Totaal aantal woorden:  " + wordCount + "\nAantal verschillende woorden:  " + uniqueWords.size());
    }

    @FXML
    private void sorteerAction(ActionEvent event) {
        //RunTime tijd = O(N)

        TreeSet<String> sortedUniqueWords = ww.sorteer(this.taInput.getText());

        StringBuilder sb = new StringBuilder();
        for (String s : sortedUniqueWords.descendingSet()) {
            sb.append(s + "\n");
        }
        this.taOutput.setText(sb.toString());
    }

    

    @FXML
    private void frequentieAction(ActionEvent event) {
        //Map<String, Integer> freq = new 
        Map<String, Integer> sortedMap = ww.frequentie(this.taInput.getText());

        StringBuilder sb = new StringBuilder();
        sortedMap.entrySet().stream().forEach((entry) -> {
            sb.append(entry.getKey() + ":    " + entry.getValue() + "\n");
        });

        this.taOutput.setText(sb.toString());
    }

    

    @FXML
    private void concordatieAction(ActionEvent event) {
        Map<String, String> concordatie = ww.concordatie(this.taInput.getText());
        
        StringBuilder sb = new StringBuilder();
        concordatie.keySet().stream().forEach((key) -> {
            sb.append(key + ":  " + concordatie.get(key) + "\n");
        });
        this.taOutput.setText(sb.toString());
    }
    
}

    


