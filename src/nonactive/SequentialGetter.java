package nonactive;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ncsa.hdf.object.h5.H5CompoundDS;
import ncsa.hdf.object.h5.H5File;
import ncsa.hdf.object.h5.H5ScalarDS;

public class SequentialGetter implements SongGetter{
	private final String foldername;
	
	/**
	 * Instantiate, use "Sequential" to depict our sequential dataset
	 * @param foldername
	 */
	public SequentialGetter(String foldername){
		this.foldername = foldername;
	}
	
	private  List<H5File> getRangeNumeric(double lo, double hi, S s) throws Exception{
		List<H5File> ret = new ArrayList<H5File>();
		File folder = new File(foldername);
    	File[] allF = folder.listFiles();
    	H5File h5;
    	for(File i : allF){
    		h5 = Hdf5_getters.hdf5_open_readonly(i.getAbsolutePath());
    		double value = Double.NaN;
    		try{
    		//enums
    		if(s.equals(S.num_songs))
    			value = Hdf5_getters.get_num_songs(h5);
    		else if(s.equals(S.artist_familiarity))
    			value = Hdf5_getters.get_artist_familiarity(h5);
    		else if(s.equals(S.artist_hotttnesss))
    			value = Hdf5_getters.get_artist_hotttnesss(h5);
    		else if(s.equals(S.artist_latitude))
    			value = Hdf5_getters.get_artist_latitude(h5);
    		else if(s.equals(S.artist_longitude))
    			value = Hdf5_getters.get_artist_longitude(h5);
    		else if(s.equals(S.song_hotttnesss))
    			value = Hdf5_getters.get_song_hotttnesss(h5);
    		else if(s.equals(S.analysis_sample_rate))
    			value = Hdf5_getters.get_analysis_sample_rate(h5);
    		else if(s.equals(S.danceability))
    			value = Hdf5_getters.get_danceability(h5);
    		else if(s.equals(S.duration))
    			value = Hdf5_getters.get_duration(h5);
    		else if(s.equals(S.end_of_fade_in))
    			value = Hdf5_getters.get_end_of_fade_in(h5);
    		else if(s.equals(S.energy))
    			value = Hdf5_getters.get_energy(h5);
    		else if(s.equals(S.key))
    			value = Hdf5_getters.get_key(h5);
    		else if(s.equals(S.key_confidence))
    			value = Hdf5_getters.get_key_confidence(h5);
    		else if(s.equals(S.loudness))
    			value = Hdf5_getters.get_loudness(h5);
    		else if(s.equals(S.mode))
    			value = Hdf5_getters.get_mode(h5);
    		else if(s.equals(S.mode_confidence))
    			value = Hdf5_getters.get_mode_confidence(h5);
    		else if(s.equals(S.start_of_fade_out))
    			value = Hdf5_getters.get_start_of_fade_out(h5);
    		else if(s.equals(S.tempo))
    			value = Hdf5_getters.get_tempo(h5);
    		else if(s.equals(S.time_signature))
    			value = Hdf5_getters.get_time_signature(h5);
    		else if(s.equals(S.time_signature_confidence))
    			value = Hdf5_getters.get_time_signature_confidence(h5);
    		else if(s.equals(S.year))
    			value = Hdf5_getters.get_year(h5);
    		
    		
    		if(value>= lo && value <= hi)
    			ret.add(h5);
    			Hdf5_getters.hdf5_close(h5);
    		
    	}catch(Exception e){
			System.out.println("Expected Error");
		}
    	
    	}
    	return ret;
	}
	
	private  List<H5File> getNumeric(double key, S s) throws Exception{
		List<H5File> ret = new ArrayList<H5File>();
		File folder = new File(foldername);
    	File[] allF = folder.listFiles();
    	
    	for(File i : allF){
    		H5File h5 = Hdf5_getters.hdf5_open_readonly(i.getAbsolutePath());
    		double value = Double.NaN;
    		
    		try{
    		//enums
    		if(s.equals(S.artist_playmeid))
    			value = Hdf5_getters.get_artist_playmeid(h5);
    		else if(s.equals(S.artist_7digitalid))
    			value = Hdf5_getters.get_artist_7digitalid(h5);
    		else if(s.equals(S.release_7digitalid))
    			value = Hdf5_getters.get_release_7digitalid(h5);
    		else if(s.equals(S.track_7digitalid))
    			value = Hdf5_getters.get_track_7digitalid(h5);
    		
    		
    		if(value == key)
    			ret.add(h5);
    			Hdf5_getters.hdf5_close(h5);
    		
    		}catch(Exception e){
    			System.out.println("Expected Error");
    		}
    	}
    	return ret;
	}
	
	private  List<H5File> getString(String key, S s) throws Exception{
		List<H5File> ret = new ArrayList<H5File>();
		File folder = new File(foldername);
    	File[] allF = folder.listFiles();
    	H5File h5;
    	int count = 0;
    	for(File i : allF){
    		//Thread.sleep(1);
    		if(count >= 9500)
    			break;
    		count++;
    		//System.out.println("Searching: " + i.getName() + " -- " + count); DEBUG
    		
    		String value = null;
    		
    		try{
    		h5 = Hdf5_getters.hdf5_open_readonly(i.getAbsolutePath());
    		if(s.equals(S.artist_id))
    			value = Hdf5_getters.get_artist_id(h5);
    		else if(s.equals(S.artist_mbid))
    			value = Hdf5_getters.get_artist_mbid(h5);
    		else if(s.equals(S.artist_location))
    			value = Hdf5_getters.get_artist_location(h5);
    		else if(s.equals(S.artist_name))
    			value = Hdf5_getters.get_artist_name(h5);
    		else if(s.equals(S.release))
    			value = Hdf5_getters.get_release(h5);
    		else if(s.equals(S.song_id))
    			value = Hdf5_getters.get_song_id(h5);
    		else if(s.equals(S.title))
    			value = Hdf5_getters.get_title(h5);
    		else if(s.equals(S.audio_md5))
    			value = Hdf5_getters.get_audio_md5(h5);
    		else if(s.equals(S.track_id))
    			value = Hdf5_getters.get_track_id(h5);
    		
    		if(value.equals(key))
    			ret.add(h5);
    			Hdf5_getters.hdf5_close(h5);
    		}
    		catch(Exception e){
    			System.out.println("Expected Error");
    		}
    	}
    	return ret;
	}
	
	private  List<H5File> getSArr(List<String> key, S s) throws Exception{
		List<H5File> ret = new ArrayList<H5File>();
		File folder = new File(foldername);
    	File[] allF = folder.listFiles();
    	
    	for(File i : allF){
    		try{
    		H5File h5 = Hdf5_getters.hdf5_open_readonly(i.getAbsolutePath());
    		String[] value = null;
    		if(s.equals(S.similar_artists))
    			value = Hdf5_getters.get_similar_artists(h5);
    		else if(s.equals(S.artist_terms))
    			value = Hdf5_getters.get_artist_terms(h5);
    		else if(s.equals(S.artist_mbtags))
    			value = Hdf5_getters.get_artist_mbtags(h5);
    		if(scontains(key, value))
    			ret.add(h5);
        		Hdf5_getters.hdf5_close(h5);
    		}catch(Exception e){
    			System.out.println("Expected Error");
    		}
    	}
    	return ret;
	}
	
	private boolean scontains(List<String> key, String[] elems){
		for(String i : elems)
			if(key.contains(i))
				return true;
		return false;
	}
	
    public  List<H5File> get_num_songs(int lo, int hi) throws Exception //i
    {
    	return getRangeNumeric(lo, hi, S.num_songs);
    }

    public  List<H5File> get_artist_familiarity(double lo, double hi) throws Exception //d
    {  
    	return getRangeNumeric(lo, hi, S.artist_familiarity);
    }

 
    public  List<H5File> get_artist_hotttnesss(double lo, double hi) throws Exception//d
    {    
    	return getRangeNumeric(lo, hi, S.num_songs);
    }


    public  List<H5File> get_artist_id(String key) throws Exception //TODO s
    {    
    	return getString(key, S.artist_id);
    }


    public  List<H5File> get_artist_mbid(String key) throws Exception //TODO s
    {    
    	return getString(key, S.artist_mbid);
    }

    public  List<H5File> get_artist_playmeid(int key) throws Exception //TODO i
    {    
    	return getNumeric(key, S.artist_playmeid);
    }

    public  List<H5File> get_artist_7digitalid(int key) throws Exception //TODO i
    {    
    	return getNumeric(key, S.artist_7digitalid);
    }

    public  List<H5File> get_artist_latitude(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.artist_latitude);
    }

    public  List<H5File> get_artist_longitude(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.artist_longitude);
    }

    public  List<H5File> get_artist_location(String key) throws Exception //TODO s
    {    
    	return getString(key, S.artist_location);
    }

    public  List<H5File> get_artist_name(String key) throws Exception //TODO s
    {  
    	return getString(key, S.artist_name);
    }

    public  List<H5File> get_release(String key) throws Exception  //TODO s
    {  
    	return getString(key, S.release);
    }

    public  List<H5File> get_release_7digitalid(int key) throws Exception //TODO i
    {    
    	return getNumeric(key, S.release_7digitalid);
    }

    public  List<H5File> get_song_id(String key) throws Exception //TODO s
    {    
    	return getString(key, S.song_id);
    }

    public  List<H5File> get_song_hotttnesss(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.song_hotttnesss);
    }

    public  List<H5File> get_title(String key) throws Exception //TODO s
    {    
    	return getString(key, S.title);
    }

    public  List<H5File> get_track_7digitalid(int key) throws Exception //TODO i
    {    
    	return getNumeric(key, S.track_7digitalid);
    }

    public  List<H5File> get_similar_artists(List<String> key) throws Exception //TODO DIFF s[]
    {    
    	return getSArr(key, S.similar_artists);
    }

    public  List<H5File> get_artist_terms(List<String> key) throws Exception //TODO DIFF s[]
    {    
    	return getSArr(key, S.artist_terms);
    }

    //NOT GOING TO BE USED MOST LIEKLY
   /* public  List<H5File> get_artist_terms_freq(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_artist_terms_weight(H5File h5, int songidx) throws Exception //TODO DIFF s[]
    {    
    }*/

    public  List<H5File> get_analysis_sample_rate(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.analysis_sample_rate);
    }

    public  List<H5File> get_audio_md5(String key) throws Exception //TODO s
    {    
    	return getString(key, S.audio_md5);
    } 

    public  List<H5File> get_danceability(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.danceability);
    } 

    public  List<H5File> get_duration(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.duration);
    }

    public  List<H5File> get_end_of_fade_in(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.end_of_fade_in);
    }

    public  List<H5File> get_energy(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.energy);
    } 

    public  List<H5File> get_key(int lo, int hi) throws Exception //i
    {    
    	return getRangeNumeric(lo, hi, S.key);
    }

    public  List<H5File> get_key_confidence(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.key_confidence);
    }
 
    public  List<H5File> get_loudness(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.loudness);
    }

    public  List<H5File> get_mode(int lo, int hi) throws Exception //i
    {    
    	return getRangeNumeric(lo, hi, S.mode);
    }

    public  List<H5File> get_mode_confidence(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.mode_confidence);
    }

    public  List<H5File> get_start_of_fade_out(double lo, double hi) throws Exception //d
    {   
    	return getRangeNumeric(lo, hi, S.start_of_fade_out);
    }

    public  List<H5File> get_tempo(double lo, double hi) throws Exception //d
    {
    	return getRangeNumeric(lo, hi, S.tempo);
    }

    public  List<H5File> get_time_signature(int lo, int hi) throws Exception //i
    {   
    	return getRangeNumeric(lo, hi, S.time_signature);
    }

    public  List<H5File> get_time_signature_confidence(double lo, double hi) throws Exception //d
    {    
    	return getRangeNumeric(lo, hi, S.time_signature_confidence);
    }

    public  List<H5File> get_track_id(String key) throws Exception //TODO s
    {   
    	return getString(key, S.track_id);
    }

    //DOUBLE ARRAY VALUES (Dont seem necessary)
    /*
    public  List<H5File> get_segments_start(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_segments_confidence(H5File h5, int songidx) throws Exception//TODO DIFF d[] 
    {   
    }

    public  List<H5File> get_segments_pitches(H5File h5, int songidx) throws Exception//TODO DIFF d[]
    {    
    }

    public  List<H5File> get_segments_timbre(H5File h5, int songidx) throws Exception//TODO DIFF d[]
    {    
    }

    public  List<H5File> get_segments_loudness_max(H5File h5, int songidx) throws Exception//TODO DIFF d[]
    {   
    }

    public  List<H5File> get_segments_loudness_max_time(H5File h5, int songidx) throws Exception//TODO DIFF d[]
    {   
    }

    public  List<H5File> get_segments_loudness_start(H5File h5, int songidx) throws Exception//TODO DIFF d[]
    {   
    }

    public  List<H5File> get_sections_start(H5File h5, int songidx) throws Exception//TODO DIFF d[]
    {    
    }

    public  List<H5File> get_sections_confidence(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_beats_start(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_beats_confidence(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_bars_start(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_bars_confidence(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_tatums_start(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }

    public  List<H5File> get_tatums_confidence(H5File h5, int songidx) throws Exception //TODO DIFF d[]
    {    
    }
    */
    public  List<H5File> get_year(int lo, int hi) throws Exception //i
    {    
    	return getRangeNumeric(lo, hi, S.year);
    }

    public  List<H5File> get_artist_mbtags(List<String> key) throws Exception //TODO DIFF s[]
    {    
    	return getSArr(key, S.artist_mbtags);
    }

    /* MOST LIKELY UNUSED
    public  List<H5File> get_artist_mbtags_count(H5File h5, int songidx) throws Exception //TODO DIFF i[]
    {    
    }
*/
}