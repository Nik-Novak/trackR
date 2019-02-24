//package nonactive;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import ncsa.hdf.object.h5.H5File;
//
//public class CSVConversion {
//	public static void main(String[] args) throws IOException{
//		int count = 0;
//		FileWriter f = new FileWriter("MSD.csv");
//		
//		File folder = new File("Sequential");
//    	File[] allF = folder.listFiles();
//    	
//    	String out = "";
//    	
//    	double artist_familiarity = -1;
//    	double artist_hotttnesss=-1;
//    	String artist_id="";
//    	String artist_mbid="";
//    	int artist_playmeid=-1;
//    	int artist_7digitalid=-1;
//    	double artist_latitude=-1;
//    	double artist_longitude=-1;
//    	String artist_location="";
//    	String artist_name="";
//    	String release="";
//    	String song_id="";
//    	double song_hotttnesss=-1;
//    	String title="";
//    	int track_7digitalid=-1;
//    	String[] similar_artists=null;
//    	String[] artist_terms=null;
//    	String audio_md5="";
//    	double danceability=-1;
//    	double duration=-1;
//    	double end_of_fade_in=-1;
//    	double energy=-1;
//    	int key=-1;
//    	double key_confidence=-1;
//    	double loudness=-1;
//    	int mode=-1;
//    	double mode_confidence=-1;
//    	double start_of_fade_out=-1;
//    	double tempo=-1;
//    	int time_signature=-1;
//    	double time_signature_confidence=-1;
//    	String track_id="";
//    	int year=0;
//    	for(File i : allF){
//    		System.out.println(++count);
//    		H5File h5 = Hdf5_getters.hdf5_open_readonly(i.getAbsolutePath());
//    		try {
//				artist_familiarity = Hdf5_getters.get_artist_familiarity(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_hotttnesss = Hdf5_getters.get_artist_hotttnesss(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_id = Hdf5_getters.get_artist_id(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_mbid = Hdf5_getters.get_artist_mbid(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_playmeid = Hdf5_getters.get_artist_playmeid(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_latitude=Hdf5_getters.get_artist_latitude(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_longitude = Hdf5_getters.get_artist_longitude(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_location=Hdf5_getters.get_artist_location(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_name=Hdf5_getters.get_artist_name(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				release=Hdf5_getters.get_release(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_7digitalid=Hdf5_getters.get_artist_7digitalid(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				song_id=Hdf5_getters.get_song_id(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				song_hotttnesss=Hdf5_getters.get_song_hotttnesss(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				title=Hdf5_getters.get_title(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				track_7digitalid=Hdf5_getters.get_track_7digitalid(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				similar_artists=Hdf5_getters.get_similar_artists(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				artist_terms=Hdf5_getters.get_artist_terms(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				audio_md5=Hdf5_getters.get_audio_md5(h5);
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//    		 try {
//				danceability=Hdf5_getters.get_danceability(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				duration=Hdf5_getters.get_duration(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				end_of_fade_in=Hdf5_getters.get_end_of_fade_in(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				energy=Hdf5_getters.get_energy(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				key=Hdf5_getters.get_key(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				key_confidence=Hdf5_getters.get_key_confidence(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				loudness=Hdf5_getters.get_loudness(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				mode=Hdf5_getters.get_mode(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				mode_confidence=Hdf5_getters.get_mode_confidence(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				start_of_fade_out=Hdf5_getters.get_start_of_fade_out(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				tempo=Hdf5_getters.get_tempo(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				time_signature=Hdf5_getters.get_time_signature(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				time_signature_confidence=Hdf5_getters.get_time_signature_confidence(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				track_id=Hdf5_getters.get_track_id(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		 try {
//				year=Hdf5_getters.get_year(h5);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    	
//    	
//    		f.write(artist_familiarity + "<S>" + artist_hotttnesss + "<S>" + artist_id + "<S>" + artist_mbid + "<S>" +artist_playmeid+ "<S>"  +artist_7digitalid+ "<S>" +
//    				artist_latitude+ "<S>" +artist_longitude+ "<S>" +artist_location+ "<S>" +artist_name+ "<S>" +release+ "<S>" +song_id+ "<S>" +song_hotttnesss+ "<S>" +title
//    				+ "<S>" +track_7digitalid+ "<S>" +toArrString(similar_artists)+ "<S>" +toArrString(artist_terms)+ "<S>" +audio_md5+ "<S>" +danceability+ "<S>" +duration+ "<S>" +end_of_fade_in+ "<S>" +energy+ "<S>" +key+ "<S>" +key_confidence+ "<S>" +
//    				loudness+ "<S>" +mode+ "<S>" +mode_confidence+ "<S>" +start_of_fade_out+ "<S>" +tempo+ "<S>" +time_signature+ "<S>" +time_signature_confidence+ "<S>" +track_id+ "<S>" +year+ System.lineSeparator());
//    	Hdf5_getters.hdf5_close(h5);
//    	/*
//    	 * double artist_familiarity = -1;
//    	double artist_hotttnesss=-1;
//    	String artist_id="";
//    	String artist_mbid="";
//    	int artist_playmeid=-1;
//    	int artist_7digitalid=-1;
//    	double artist_latitude=-1;
//    	double artist_longitude=-1;
//    	String artist_location="";
//    	String artist_name="";
//    	String release="";
//    	int s7digitalid=-1;
//    	String song_id="";
//    	double song_hotttnesss=-1;
//    	String title="";
//    	int track_7digitalid=-1;
//    	String[] similar_artists=null;
//    	String[] artist_terms=null;
//    	String audio_md5="";
//    	double danceability=-1;
//    	double duration=-1;
//    	double end_of_fade_in=-1;
//    	double energy=-1;
//    	int key=-1;
//    	double key_confidence=-1;
//    	double loudness=-1;
//    	int mode=-1;
//    	double mode_confidence=-1;
//    	double start_of_fade_out=-1;
//    	double tempo=-1;
//    	int time_signature=-1;
//    	double time_signature_confidence=-1;
//    	String track_id="";
//    	int year=0;
//    	 */
//    	}
//		
//		
//    	
//		f.close();
//	}
//	private static String toArrString(String[] s){
//		String ret = "[";
//		for(String i : s){
//			ret+=i+':';
//		}
//		return ret.substring(0,ret.length()-1) + "]";
//	}
//}
