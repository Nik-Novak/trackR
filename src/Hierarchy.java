import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Hierarchy {
	private static HashMap<Integer, S> map; //mapping enums to numbers
	private static final String DATAROOT = "Data"; //default data root folder
	
	/**
	 * Range of Tempos in the data, TEMPO_RANGE[0] = lower bound, and [1] upper
	 */
	public static final double[] TEMPO_RANGE = {0,110};
	/**
	 * Range of Loudness in the data, TEMPO_RANGE[0] = lower bound, and [1] upper
	 */
	public static final double[] LOUDNESS_RANGE = {-41.806,-1.479};
	/**
	 * Range of Tempos in the data, TEMPO_RANGE[0] = lower bound, and [1] upper
	 */
	public static final double[] FAMIL_RANGE = {0.23330880981250302,1.0};
	
	private static class Tuple<T1,T2> implements Serializable{ //private class for holding tuples of data of any type.
		private static final long serialVersionUID = 1L;
		T1 param1;
		T2 param2;
		Tuple(T1 param1, T2 param2){
			this.param1=param1; this.param2=param2;
		}
	}
	
	/**
	 * Takes a String path to some data and cleans it of extra .csv and .IRAF files after hierarchy generation to reduce space consumption
	 * @param pathToDataRoot
	 */
	public static void cleanData(String pathToDataRoot){ cleanData(pathToDataRoot, 0); }
	private static void cleanData(String path, int depth) {
		File f = new File(path);
		for(File i : f.listFiles()){ //for each file
			if(i.isFile() && !i.isDirectory() && depth <(NUMCAT) ) //up to a certain depth
				i.delete(); //remove the file
			if(i.isDirectory())
				cleanData(i.getAbsolutePath(), depth+1);//recursively clean the next folder down
		}
				
	}
	
	private static Queue<Tuple<String, Integer>> queue; //queue for breadthfirst generating the tree
	static int NUMCAT = 3;
	static int NUMDIV = 10;
	public static void generateHierarchy(String absPathToData) throws ClassNotFoundException, IOException {
		
		queue = new LinkedList<Tuple<String,Integer>>();
		map = new HashMap<Integer, S>();
		//maj>tempo>loudness>fam
		
		//add map values so we can get
		map.put(1, S.tempo);
		map.put(2, S.loudness);
		map.put(3, S.artist_familiarity);											
		
		queue.add(new Tuple<String, Integer>(absPathToData, 1)); //enqueue the first folder to be worked on

		while(!queue.isEmpty()){ //breadth first search, adding folders to be worked on
			System.out.println("LOOPING");
			Tuple<String, Integer> q = queue.remove();
			String path = q.param1;
			int depth = q.param2;
			if(depth<=NUMCAT-1)
				System.out.println("Depth: " + depth + " Sorting: " + map.get(depth).toString());
			hierarchy(depth, path, "Data.csv", true); //work on a queued folder

		}

	}
	
	//generate folder hierarchy for the given folder
	private static void hierarchy(int depth, String parent, String datafile, boolean sort) throws ClassNotFoundException, IOException {
		if(depth>NUMCAT)
			return;//dont exceed max depth
		
		//get data
		S s = map.get(depth);
		IndexedRAF.convertToIRAF(new File(parent + "/" + datafile));
		IndexedRAF file = new IndexedRAF(parent + "/" + datafile, "rw");
		
		if(sort)
			file.sort(s);
		
		//this section divides all of the songs into a number of evenly distributed sub folders based on hte sorted metric it is applying
		int i=0;
		int mul=1;
		ArrayList<RAFLine> songs;
		while( i < file.getNumLines()){ //while there are songs
			songs = new ArrayList<RAFLine>((int)file.getNumLines()/NUMDIV);
		for(; i!= mul*file.getNumLines()/NUMDIV; i++){ //divide by multiples and add teh files to respective folders
			songs.add(file.getMap().get(i));
			System.out.println("Song added: " + file.value(file.getMap().get(i).pointer ,s) + " ****** SONG DATA: " + file.getLine(file.getMap().get(i).pointer));
		}
		//name the folder to be the range contained
		String folname = parent +"/" +  s.toString() + "[" + file.value(songs.get(0).pointer, s) + "," + file.value(songs.get(songs.size()-1).pointer, s) + "]";
		File f = new File(folname);
		if(f.exists() && f.isDirectory())
			f=new File(folname + "(2)");
		
		f.mkdir();
		System.out.println("Directory made: " + f.getAbsolutePath());
		System.out.println(f.isDirectory());
		
		//write the song to proper data folder
		File q = new File(f.getAbsolutePath() + "/" + "Data.csv");
		System.out.println(q.getAbsolutePath());
		q.createNewFile();
		IndexedRAF lofile = new IndexedRAF(q, "rw");
		for(int j=0; j!= songs.size(); j++){
			String gh = file.getLine(songs.get(j).pointer);
			lofile.writeLine(gh);
		}
		lofile.close();
		mul++;
		}
			
		file.close();
		
		//enqueue all other folders for generation
			File expl = new File(parent);
			for(File t : expl.listFiles()){
				if(t.isDirectory() && !t.isFile())
					queue.add(new Tuple<String, Integer>(t.getAbsolutePath(), depth+1));
			}
	}
	
	private static ArrayList<Song> songPaths;
	/**
	 * Takes in a variety of metrics and returns a list of similar songs pertaining ot those metrics.
	 * @param mode
	 * @param tempo
	 * @param loudness
	 * @param familiarity
	 * @param path
	 * @param sortSimilarArtistsFirst
	 * @return
	 */
	public static List<Song> getSimilarSongs(int mode, double tempo, double loudness, double familiarity, String path, boolean sortSimilarArtistsFirst){
		songPaths = new ArrayList<Song>();
		if(path == null)
			path = DATAROOT;
		if(mode==1) //major vs minor songs
			path+="/Major";
		else if(mode==0)
			path+="/Minor";
		else
			System.out.println("CRITICAL ERROR: MODE NOT 0 or 1");
		double[] metrics = new double[4];
		metrics[0] = mode; metrics[1] = tempo; metrics[2] = loudness; metrics[3] = familiarity; //store metrics
		File expl = new File(path);
		dive(expl, metrics, 1); //search the mian folder, recursively searching down
		return songPaths;
	}
	
	//searches recursively and depth first
	private static void dive(File path, double[] metrics, int depth) {
		for (File i : path.listFiles()){
			if(!i.isDirectory() && i.getName().equals("Data.csv") ){ //if we've hit bottom of tree, add all songs into our list
				List<Song> s = allSongs(i.getAbsolutePath());
				songPaths.addAll(s);
			}
			if(!i.isDirectory())
				continue;
			
			//consider and recursively check other directories
			String filename = i.getName();
			
			String range = filename.split("\\[")[1];
			if (inRange(metrics[depth], range)){
				dive(i.getAbsoluteFile(), metrics, depth+1);
			}
			
		}
	}

	//returns true if the metric is within the provided string range representation
	private static boolean inRange(double metric, String range) {	
		range = range.substring(0,range.indexOf(']'));
		double lo = Double.parseDouble(range.split(",")[0]);
		double hi = Double.parseDouble(range.split(",")[1]);
		return (metric >= lo && metric<=hi) ;
	}

	/**
	 * Returns all of the songs in a particular .csv datafile
	 * @param pathToDataFile
	 * @return
	 */
	public static List<Song> allSongs(String pathToDataFile){
		List<Song> ret = new ArrayList<Song>();
		try(BufferedReader br = new BufferedReader(new FileReader(new File( pathToDataFile )))) {
			int lc = 0;
		    for(String line; (line = br.readLine()) != null; ) { //for every line
		    	if(lc==0)
		    		line = line.substring(line.indexOf('.')-1);
		        ret.add(new Song(line)); //add a new song to the list
		    	lc++;
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
}
