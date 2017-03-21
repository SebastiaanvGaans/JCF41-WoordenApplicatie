/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package woordenapplicatie.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Gebruiker
 */
public class WoordenWerker {
    
    public WoordenWerker(){
    }
    
    public ArrayList<Integer> aantal(String source){
        int wordCount = 0;
        Set<String> uniqueWords = new HashSet<>();
        
        for (String s : woordenOpsplitsen(source)) {
            wordCount++;
            uniqueWords.add(s);
        }
        return new ArrayList<Integer>(Arrays.asList(wordCount, uniqueWords.size()));
        
    } 
    
    public TreeSet<String> sorteer(String source) {
        
        TreeSet<String> sortedUniqueWords = new TreeSet<>();

        for (String s : this.woordenOpsplitsen(source)) {
            sortedUniqueWords.add(s);
        }

        return sortedUniqueWords;
    }
    
    public Map<String, Integer> frequentie(String source) {
        Map<String, Integer> map = new HashMap<>();
        ValueComparator vc = new ValueComparator(map);
        Map<String, Integer> sortedMap = new TreeMap<>(vc);

        for (String s : this.woordenOpsplitsen(source)) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                map.put(s, map.get(s) + 1);
            }
        }
        sortedMap.putAll(map);
        return sortedMap;
    }
    
    public Map<String,String> concordatie(String source){
        Map<String, String> concordatie = new HashMap<>();
        Set<String> regel = new HashSet<>();
        int line = 1;

        for (String s : this.zinnenOpsplitsen(source)) {
            s = s.toLowerCase();
            for (String sub : s.split(" ")) {
                regel.add((sub));
            }
            for (String uniqueSub : regel) {
                if (!concordatie.containsKey(uniqueSub)) {
                    concordatie.put(uniqueSub, Integer.toString(line));
                } else {
                    concordatie.put(uniqueSub, concordatie.get(uniqueSub) + ", " + Integer.toString(line));
                }
            }
            regel.clear();
            line++;
        }
        return concordatie;
    }
    
    public ArrayList<String> zinnenOpsplitsen(String source) {
        ArrayList<String> sentences = new ArrayList<>();

        for (String s : source.replace(",", "").split("\n")) {
            if (!"".equals(s)) {
                sentences.add(s);
            }
        }
        return sentences;
    }

    public ArrayList<String> woordenOpsplitsen(String source) {
        ArrayList<String> words = new ArrayList<>();
        for (String s : zinnenOpsplitsen(source)) {
            for (String w : s.split(" ")) {
                if (!"".equals(w)) {
                    words.add(w);
                }
            }
        }
        return words;
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
