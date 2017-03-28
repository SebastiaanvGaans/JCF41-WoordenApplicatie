/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeSet;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import woordenapplicatie.gui.WoordenWerker;

/**
 *
 * @author Gebruiker
 */
public class aantalTest {
    
    StringBuilder sb = new StringBuilder();
    WoordenWerker ww = new WoordenWerker();
    int amount = 1000;
    
    public aantalTest() {
    }
    
    @Before
    public void setUp() {
        
        
        for(int i = 0; i<amount; i++){
            sb.append("test ");
        }
    }

    @Test
    public void edgecaseAllSameTest(){
        ArrayList<Integer> outputAantal = ww.aantal(sb.toString());
        assertEquals(1,(int)outputAantal.get(1));
        assertEquals(amount,(int)outputAantal.get(0)); 
        
    }
    
    @Test
    public void sorteerTest(){
        TreeSet<String> outputSorteer = ww.sorteer(sb.toString());
        assertTrue("test".equals(outputSorteer.first()));
        assertEquals(outputSorteer.size(), 1);
    }
    
    @Test
    public void frequentieTest(){
        Map<String, Integer> outputFrequentie = ww.frequentie(sb.toString());
        assertEquals((int)outputFrequentie.get("test"), amount);
    }
    
    @Test
    public void concordantieTest(){
        Map<String, ArrayList<Integer>> outputConcordantie = ww.concordatie(sb.toString());
        assertEquals((int)outputConcordantie.get("test").get(0), 1);
    }
}
