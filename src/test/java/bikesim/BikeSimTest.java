package bikesim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class BikeSimTest {
	
	/**********************************************************/

	BikeSim sim;

	/**********************************************************/

    @BeforeEach                                         
    void setUp() {
        sim = new BikeSim();
    }

	/**********************************************************/

    @Test                                               
    @DisplayName("Test file 1")   
    void testFile1() {
    	sim.processInput("data/file1.txt");
        assertEquals("(0,6), NORTH", sim.getOutputString(), "Should process file 1");  
    }

	/**********************************************************/

    @Test                                               
    @DisplayName("Test file 2")   
    void testFile2() {
    	sim.processInput("data/file2.txt");
        assertEquals("(0,0), WEST", sim.getOutputString(), "Should process file 2");  
    }

	/**********************************************************/

    @Test                                               
    @DisplayName("Test file 3")   
    void testFile3() {
    	sim.processInput("data/file3.txt");
        assertEquals("(3,3), NORTH", sim.getOutputString(), "Should process file 3");  
    }

}
