import java.util.HashMap;
import java.util.Map;

public class Song implements Comparable<Song>{
	
	//All song data fields retrieved from dataset
	private double artist_familiarity = -1;
	private double artist_hotttnesss=-1;
	private String artist_id="";
	private String artist_mbid="";
	private int artist_playmeid=-1;
	private int artist_7digitalid=-1;
	private double artist_latitude=-1;
	private double artist_longitude=-1;
	private String artist_location="";
	private String artist_name="";
	private String release="";
	private String song_id="";
	private double song_hotttnesss=-1;
	private String title="";
	private int track_7digitalid=-1;
	private String[] similar_artists=null;
	private String[] artist_terms=null;
	private String audio_md5="";
	private double danceability=-1;
	private double duration=-1;
	private double end_of_fade_in=-1;
	private double energy=-1;
	private int key=-1;
	private double key_confidence=-1;
	private double loudness=-1;
	private int mode=-1;
	private double mode_confidence=-1;
	private double start_of_fade_out=-1;
	private double tempo=-1;
	private int time_signature=-1;
	private double time_signature_confidence=-1;
	private String track_id="";
	private int year=0;
	private final String csvline;
	
	private final static int numFields = 33;//number of total fields
	
	public static S sortBy; //value to sort by when calling sort, by default it is title
	
	public static Map<S,Integer> map; //map mapping enumerated types to indices for an array
	

	/**
	 * Initializes the map data to map types to integers ina  consistent way
	 */
	public static void initHash(){
		map = new HashMap<S,Integer>(33);
		map.put(S.artist_familiarity, 0);
		map.put(S.artist_hotttnesss, 1);
		map.put(S.artist_id, 2);
		map.put(S.artist_mbid, 3);
		map.put(S.artist_playmeid, 4);
		map.put(S.artist_7digitalid, 5);
		map.put(S.artist_latitude, 6);
		map.put(S.artist_longitude, 7);
		map.put(S.artist_location, 8);
		map.put(S.artist_name, 9);
		map.put(S.release, 10);
		map.put(S.song_id, 11);
		map.put(S.song_hotttnesss, 12);
		map.put(S.title, 13);
		map.put(S.track_7digitalid, 14);
		map.put(S.similar_artists, 15);
		map.put(S.artist_terms, 16);
		map.put(S.audio_md5, 17);
		map.put(S.danceability, 18);
		map.put(S.duration, 19);
		map.put(S.end_of_fade_in, 20);
		map.put(S.energy, 21);
		map.put(S.key, 22);
		map.put(S.key_confidence, 23);
		map.put(S.loudness, 24);
		map.put(S.mode, 25);
		map.put(S.mode_confidence, 26);
		map.put(S.start_of_fade_out, 27);
		map.put(S.tempo, 28);
		map.put(S.time_signature, 29);
		map.put(S.time_signature_confidence, 30);
		map.put(S.track_id, 31);
		map.put(S.year, 32);
	}
	
	/**
	 * Initialize and create a new song based on a csvline from the dataset
	 * @param csvline
	 */
	public Song(String csvline){
		this.csvline=csvline;
		String[] data = csvline.split("<S>");
		for(int i=0; i!= numFields; i++)
			set(i,data[i]);
		sortBy=S.title;
	}
	
	//parses a csv array
	private String[] decodeArr(String repr){
		repr = repr.substring(1,repr.length()-1);
		return repr.split(":");
	}
	
	/**
	 * Sets the data based on the datatype's integer ID and replaces it with the passed in value
	 * @param s
	 * @param val
	 */
	public void set(int s, Object val){
		switch(s){
			case 0:
				setArtist_familiarity(Double.parseDouble(val.toString()));
				break;
			case 1:
				setArtist_hotttnesss(Double.parseDouble(val.toString()));
				break;
			case 2:
				setArtist_id((String) val);
				break;
			case 3:
				setArtist_mbid((String) val);
				break;
			case 4:
				setArtist_playmeid(Integer.parseInt(val.toString()));
				break;
			case 5:
				setArtist_7digitalid(Integer.parseInt(val.toString()));
				break;
			case 6:
				setArtist_latitude(Double.parseDouble(val.toString()));
				break;
			case 7:
				setArtist_longitude(Double.parseDouble(val.toString()));
				break;
			case 8:
				setArtist_location((String) val);
				break;
			case 9:
				setArtist_name((String) val);
				break;
			case 10:
				setRelease((String) val);
				break;
			case 11:
				setSong_id((String) val);
				break;
			case 12:
				setSong_hotttnesss(Double.parseDouble(val.toString()));
				break;
			case 13:
				setTitle((String) val);
				break;
			case 14:
				setTrack_7digitalid(Integer.parseInt(val.toString()));
				break;
			case 15:
				setSimilar_artists(decodeArr(val.toString()));
				break;
			case 16:
				setArtist_terms(decodeArr(val.toString()));
				break;
			case 17:
				setAudio_md5((String) val);
				break;
			case 18:
				setDanceability(Double.parseDouble(val.toString()));
				break;
			case 19:
				setDuration(Double.parseDouble(val.toString()));
				break;
			case 20:
				setEnd_of_fade_in(Double.parseDouble(val.toString()));
				break;
			case 21:
				setEnergy(Double.parseDouble(val.toString()));
				break;
			case 22:
				setKey(Integer.parseInt(val.toString()));
				break;
			case 23:
				setKey_confidence(Double.parseDouble(val.toString()));
				break;
			case 24:
				setLoudness(Double.parseDouble(val.toString()));
				break;
			case 25:
				setMode(Integer.parseInt(val.toString()));
				break;
			case 26:
				setMode_confidence(Double.parseDouble(val.toString()));
				break;
			case 27:
				setStart_of_fade_out(Double.parseDouble(val.toString()));
				break;
			case 28:
				setTempo(Double.parseDouble(val.toString()));
				break;
			case 29:
				setTime_signature(Integer.parseInt(val.toString()));
				break;
			case 30:
				setTime_signature_confidence(Double.parseDouble(val.toString()));
				break;
			case 31:
				setTrack_id((String) val);
				break;
			case 32:
				setYear(Integer.parseInt(val.toString()));
				break;
		}
	}
	
	//getters and setters for Song data
	public double getArtist_familiarity() {
		return artist_familiarity;
	}
	public void setArtist_familiarity(double artist_familiarity) {
		this.artist_familiarity = artist_familiarity;
	}
	public double getArtist_hotttnesss() {
		return artist_hotttnesss;
	}
	public void setArtist_hotttnesss(double artist_hotttnesss) {
		this.artist_hotttnesss = artist_hotttnesss;
	}
	public String getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(String artist_id) {
		this.artist_id = artist_id;
	}
	public String getArtist_mbid() {
		return artist_mbid;
	}
	public void setArtist_mbid(String artist_mbid) {
		this.artist_mbid = artist_mbid;
	}
	public int getArtist_playmeid() {
		return artist_playmeid;
	}
	public void setArtist_playmeid(int artist_playmeid) {
		this.artist_playmeid = artist_playmeid;
	}
	public int getArtist_7digitalid() {
		return artist_7digitalid;
	}
	public void setArtist_7digitalid(int artist_7digitalid) {
		this.artist_7digitalid = artist_7digitalid;
	}
	public double getArtist_latitude() {
		return artist_latitude;
	}
	public void setArtist_latitude(double artist_latitude) {
		this.artist_latitude = artist_latitude;
	}
	public double getArtist_longitude() {
		return artist_longitude;
	}
	public void setArtist_longitude(double artist_longitude) {
		this.artist_longitude = artist_longitude;
	}
	public String getArtist_location() {
		return artist_location;
	}
	public void setArtist_location(String artist_location) {
		this.artist_location = artist_location;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getSong_id() {
		return song_id;
	}
	public void setSong_id(String song_id) {
		this.song_id = song_id;
	}
	public double getSong_hotttnesss() {
		return song_hotttnesss;
	}
	public void setSong_hotttnesss(double song_hotttnesss) {
		this.song_hotttnesss = song_hotttnesss;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTrack_7digitalid() {
		return track_7digitalid;
	}
	public void setTrack_7digitalid(int track_7digitalid) {
		this.track_7digitalid = track_7digitalid;
	}
	public String[] getSimilar_artists() {
		return similar_artists;
	}
	public void setSimilar_artists(String[] similar_artists) {
		this.similar_artists = similar_artists;
	}
	public String[] getArtist_terms() {
		return artist_terms;
	}
	public void setArtist_terms(String[] artist_terms) {
		this.artist_terms = artist_terms;
	}
	public String getAudio_md5() {
		return audio_md5;
	}
	public void setAudio_md5(String audio_md5) {
		this.audio_md5 = audio_md5;
	}
	public double getDanceability() {
		return danceability;
	}
	public void setDanceability(double danceability) {
		this.danceability = danceability;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public double getEnd_of_fade_in() {
		return end_of_fade_in;
	}
	public void setEnd_of_fade_in(double end_of_fade_in) {
		this.end_of_fade_in = end_of_fade_in;
	}
	public double getEnergy() {
		return energy;
	}
	public void setEnergy(double energy) {
		this.energy = energy;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public double getKey_confidence() {
		return key_confidence;
	}
	public void setKey_confidence(double key_confidence) {
		this.key_confidence = key_confidence;
	}
	public double getLoudness() {
		return loudness;
	}
	public void setLoudness(double loudness) {
		this.loudness = loudness;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public double getMode_confidence() {
		return mode_confidence;
	}
	public void setMode_confidence(double mode_confidence) {
		this.mode_confidence = mode_confidence;
	}
	public double getStart_of_fade_out() {
		return start_of_fade_out;
	}
	public void setStart_of_fade_out(double start_of_fade_out) {
		this.start_of_fade_out = start_of_fade_out;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	public int getTime_signature() {
		return time_signature;
	}
	public void setTime_signature(int time_signature) {
		this.time_signature = time_signature;
	}
	public double getTime_signature_confidence() {
		return time_signature_confidence;
	}
	public void setTime_signature_confidence(double time_signature_confidence) {
		this.time_signature_confidence = time_signature_confidence;
	}
	public String getTrack_id() {
		return track_id;
	}
	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int compareTo(Song o) { //sorts based on sortBy value
		switch(sortBy){
			case title:
				return title.compareTo(o.title);
			case tempo:
				return compareTo(tempo, o.tempo);
			case loudness:
				return compareTo(loudness, o.loudness);
			case song_hotttnesss:
				return compareTo(song_hotttnesss, o.song_hotttnesss);
			default:
				return title.compareTo(o.title);
		}
	}
	
	public boolean equals(Object o){
		return track_7digitalid == ((Song)o).track_7digitalid;
	}
	
	private int compareTo(double first, double second){
		if(first-second<0)
			return -1;
		else if (first==second)
			return 0;
		else
			return 1;
	}

	public String getCsvline() {
		return csvline;
	}

}
