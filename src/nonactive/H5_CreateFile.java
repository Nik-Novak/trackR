package nonactive;


import java.util.ArrayList;
import java.util.List;

import ncsa.hdf.object.h5.H5File;

public class H5_CreateFile {
	static final String FILENAME = "H5_CreateFile.h5";

	private static void CreateFile() throws Exception {
		
		SongGetter getter = new SequentialGetter("Sequential");
//		List<H5File> a = getter.get_artist_terms();
//		String ret = Hdf5_getters.get_artist_name(a.get(0));
//		System.out.println(ret);
		
//		SongGetter getter = new SequentialGetter("Sequential");
//		H5File test = Hdf5_getters.hdf5_open_readonly("TRAAAAW128F429D538.h5");
//		List<String> l = new ArrayList<String>();
//		for(String i : Hdf5_getters.get_artist_terms(test)){
//			l.add(i);
//			System.out.println(i);
//		}
//		
//		List<H5File> matchedSongs = getter.get_artist_terms(l);
//		
//		System.out.println(matchedSongs.size());

		//for(H5File i : matchedSongs)
			//System.out.println(Hdf5_getters.get_title(i));
//		
//		
//		//testing getting a list of songs from database using song parameters
//		System.out.println("testing getting a list of songs from database using song parameters (artist)");
//		SequentialGetter getter =  new SequentialGetter("Sequential");
//		List<H5File> songs = getter.get_artist_name("Casual");
//		System.out.println(songs.size());
//		for(H5File i : songs)
//			System.out.println("Artist: " + Hdf5_getters.get_artist_name(i) + " -- " + "Track: " + Hdf5_getters.get_title(i));
//		
//		
//		
//		//testing getting an HDF5 file to be read into an H5File
//		System.out.println("\n" + "testing getting an HDF5 file to be read into an H5File");
//		H5File test = Hdf5_getters.hdf5_open_readonly("TRAAAAW128F429D538.h5");
//		System.out.println("Artist: " + Hdf5_getters.get_artist_name(test) + " -- " + "Track: " + Hdf5_getters.get_title(test) + System.lineSeparator());
//		
		//testing opening one track and printing all info
		System.out.println("\n" + "testing opening one track");
		String[] args = {"TRAAAAW128F429D538.h5"};
		Hdf5_getters.main(args);
	}

	public static void main(String[] args) throws Exception {
		H5_CreateFile.CreateFile();
	}
}