/*
 * Josh Mitchell - 1422856
 * Nik Novak - 1406602
 * Matthew Shortt - 1417616
 * Phillip Pavlich - 1414960
 * Erin Varey - 1400605
 * 
 * class: main
 * This class is the starting point of our project. We call on the class FinalProject_SwingDesigner in order to have the main
 * menu pop up to begin our program
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;


public class main {
	public static void main(String[] args) throws InterruptedException {//starts here
		FinalProject_SwingDesigner.Opening();
		
		//Hierarchy.getSimilarSongs(50, -10, -1.7976931348623157E100, 0.55, null, false);
	}
}

//tempo > loudness > hottnesss > artist familiarity
//hottness: would you have liked this song if it was more popular?
//artist fam: Would you like the song more if you had known the artist?
