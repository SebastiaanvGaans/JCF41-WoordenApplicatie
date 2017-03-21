/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import woordenapplicatie.gui.WoordenWerker;

/**
 *
 * @author Gebruiker
 */
public class aantalTest {
    
    public aantalTest() {
    }
    
    WoordenWerker ww = new WoordenWerker();

    @Test
    public void edgecaseAllSameTest(){
        int amount = 1000;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<amount; i++){
            sb.append("test ");
        }
        ArrayList<Integer> output = ww.aantal(sb.toString());
        assertEquals(1,(int)output.get(1));
        assertEquals(amount,(int)output.get(0));
    }
}
