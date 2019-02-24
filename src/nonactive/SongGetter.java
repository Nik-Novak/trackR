package nonactive;
import java.util.List;

import ncsa.hdf.object.h5.H5File;

/**
 * Gets song HDF5 file based on passed in song parameters
 * @author nnova
 *
 */
public interface SongGetter {
	/**
	 * reutns number of songs contained in this file (should always be 1)
	 * @param lo
	 * @param hi
	 * @return
	 * @throws Exception
	 */
	public List<H5File> get_num_songs(int lo, int hi) throws Exception;

	/**
	 * 	float	algorithmic estimation
	 * @param lo
	 * @param hi
	 * @return
	 * @throws Exception
	 */
    public  List<H5File> get_artist_familiarity(double lo, double hi) throws Exception;
 
    /**
     * 	float	algorithmic estimation
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_hotttnesss(double lo, double hi) throws Exception;


    /**
     * 	string	Echo Nest ID
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_id(String key) throws Exception;

    /**
     * artist mbid	string	ID from musicbrainz.org
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_mbid(String key) throws Exception ;
    
    /**
     * int	ID from playme.com, or -1
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_playmeid(int key) throws Exception ;

    /**
     * 	int	ID from 7digital.com or -1
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_7digitalid(int key) throws Exception;

    /**
     * 	float	latitude
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_latitude(double lo, double hi) throws Exception;

    /**
     * float	longitude
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_longitude(double lo, double hi) throws Exception;

    /**
     * string	location name
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_location(String key) throws Exception;

    /**
     * string	artist name
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_name(String key) throws Exception;
    
    /**
     * 	string	album name
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_release(String key) throws Exception ;

    /**
     * 	int	ID from 7digital.com or -1
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_release_7digitalid(int key) throws Exception;

    /**
     * 	string	Echo Nest song ID
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_song_id(String key) throws Exception;

    /**
     * 	float	algorithmic estimation
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_song_hotttnesss(double lo, double hi) throws Exception;

    /**
     * string	song title
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_title(String key) throws Exception;

    /**
     * int	ID from 7digital.com or -1
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_track_7digitalid(int key) throws Exception;

    /**
     * array string	Echo Nest artist IDs (sim. algo. unpublished)
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_similar_artists(List<String> key) throws Exception;

    /**
     * array string	Echo Nest tags
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_terms(List<String> key) throws Exception;

    /**
     * 	float	sample rate of the audio used
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_analysis_sample_rate(double lo, double hi) throws Exception;

    /**
     * 	string	audio hash code
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_audio_md5(String key) throws Exception;

    /**
     * float	algorithmic estimation
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_danceability(double lo, double hi) throws Exception;

    /**
     * float	in seconds
     * danceability measure of this song according to The Echo Nest (between 0 and 1, 0 => not analyzed)
     * 
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_duration(double lo, double hi) throws Exception;

    /**
     * end of fade in	float	seconds at the beginning of the song
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_end_of_fade_in(double lo, double hi) throws Exception;

    /**
     * 	float	energy from listener point of view
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_energy(double lo, double hi) throws Exception;

    /**
     * 	int	key the song is in
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_key(int lo, int hi) throws Exception;

    /**
     * 	float	confidence measure
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_key_confidence(double lo, double hi) throws Exception;
 
    /**
     * float	overall loudness in dB
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_loudness(double lo, double hi) throws Exception;

    /**
     * int	major or minor
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_mode(int lo, int hi) throws Exception;

    /**
     * 	float	confidence measure
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_mode_confidence(double lo, double hi) throws Exception;

    /**
     * float	time in sec
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_start_of_fade_out(double lo, double hi) throws Exception;

    /**
     * float	estimated tempo in BPM
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_tempo(double lo, double hi) throws Exception;

    /**
     * int	estimate of number of beats per bar, e.g. 4
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_time_signature(int lo, int hi) throws Exception;

    /**
     * float	confidence measure
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_time_signature_confidence(double lo, double hi) throws Exception;

    /**
     * string	Echo Nest track ID
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_track_id(String key) throws Exception;

    /**
     * int	song release year from MusicBrainz or 0
     * @param lo
     * @param hi
     * @return
     * @throws Exception
     */
    public  List<H5File> get_year(int lo, int hi) throws Exception;

    /**
     * array string	tags from musicbrainz.org
     * @param key
     * @return
     * @throws Exception
     */
    public  List<H5File> get_artist_mbtags(List<String> key) throws Exception;

}
