import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Test {
	public static void main(String[] args) throws FileNotFoundException, IOException, URISyntaxException, ClassNotFoundException{
		//System.out.println(Hierarchy.getSimilarSongs(1, 66.232, -23.112, 0.7044114066677578, null, true));
		for(Song i : Hierarchy.getSimilarSongs(1, 66.232, -23.112, 0.7044114066677578, null, true))
			System.out.println(i.getCsvline());
		//Mode: 1 Tempo: 66.232 -- Loud: -23.112 -- Hott: 0.0 -- Fam: 0.7044114066677578
	}
	
	//Fam: 0.0814
	//loud: 3.5624
	//Tempo = 26.26
	
}
