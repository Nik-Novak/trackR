/*
 * Josh Mitchell - 1422856
 * Nik Novak - 1406602
 * Matthew Shortt - 1417616
 * Phillip Pavlich - 1414960
 * Erin Varey - 1400605
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This method holds the algorithm for computing what songs the user may 
 * like. This is done by using data collected from responses the user had from certain songs.
 *
 */
public class computeSimilarSongs extends WindowsBuilderPractice {
	
	static List<Song> playedSongs = new ArrayList<Song>();
	
	static ArrayList<Song> path = new ArrayList<Song>();
	static Graph g = new Graph();
	
	
	/**
	 * constructor for computeSimilarSongs
	 * @param DATA
	 */
	public computeSimilarSongs(List<Song> DATA) {
		super(DATA);
		// TODO Auto-generated constructor stub
	}

	protected static List<Song> data;
	
	//used to calculate the data from the array
	//used to search data set for a similar song
	
	
	
	
	
	/**
	 * Test initializes the algorithm to compute the similar/dissimilar songs to the song provided
	 * @param data1 - data of all the songs
	 */
	public static void test(List<Song> data1){
		List<Song> hierarchsongs = new ArrayList<Song>(data1);
		
		System.out.println("TEST");
		data=data1;
	
		//ASSUME FOR NOW FIRST SONG PLAYED IS INDEX 0
		ArrayList<Integer> intAL = new ArrayList<Integer>();
		ArrayList<String> stringAL = new ArrayList<String>();

		String[] stringArr = {"Artist", "Tempo", "Loudness", "Dance", "Hotness"};
		int[] valueOrder = {2, -2, 1, -1, 0, -3};
	
//		sortArray(questionResponses, stringArr, valueOrder, intAL, stringAL);
//	
//		for(int i = 0; i < 5; i++){
//			questionResponses[i]=intAL.get(i);
//			stringArr[i]=stringAL.get(i);
//		}
//		
//		
//		
//		for(int I = 0; I < 5; I++){
//			start(questionResponses[I],stringArr[I],data.get(0));
//		}
//		System.out.println(data.size());
		
		//****************DELTA****************
		double delta_tempo;
		double delta_loud;
		int delta_mode;
		
		double delta_fam;
		//0 - artist voice (tags)
		//1- tempo
		//2 - loudness
		//3 - artist famil
		//4 - 
		System.out.println(WindowsBuilderPractice.questionResponses[0]);
		switch(WindowsBuilderPractice.questionResponses[0]){
		case -3: delta_mode=(data.get(0).getMode()==0 ? 1 : -1);	break;
		case -2: delta_mode=0;	break;
		case -1: delta_mode=0;	break;
		default: delta_mode=0;	
		}
		switch(WindowsBuilderPractice.questionResponses[1]){
		case -2: delta_tempo=-26.26*2;	break;
		case -1: delta_tempo=-26.26;	break;
		case 0: delta_tempo=0;	break;
		case 1: delta_tempo=26.26;	break;
		case 2: delta_tempo=26.26*2;	break;
		default: delta_tempo=0;	
		}
		switch(WindowsBuilderPractice.questionResponses[2]){
		case -2: delta_loud=-3.56*2;	break;
		case -1: delta_loud=-3.56;	break;
		case 0: delta_loud=0;	break;
		case 1: delta_loud=3.56;	break;
		case 2: delta_loud=3.56*2;	break;
		default: delta_loud=0;	
		}
		
		switch(WindowsBuilderPractice.questionResponses[3]){
		case -2: delta_fam=-0.2;	break;
		case -1: delta_fam=-0.1;	break;
		case 0: delta_fam=0;	break;
		case 1: delta_fam=0.1;	break;
		case 2: delta_fam=0.2;	break;
		default: delta_fam=0;	
		}
		Song s = hierarchsongs.get(0);
		System.out.println("Final mode: " + (s.getMode()+delta_mode));
		int fmode = (s.getMode()+delta_mode);
		data = Hierarchy.getSimilarSongs(fmode, s.getTempo()+ delta_tempo, s.getLoudness() + delta_loud, s.getArtist_familiarity() + delta_fam, null, true);
		//System.out.println("Mode: " + s.getMode() + " Tempo: " + (s.getTempo()+ delta_tempo) + " -- Loud: " + s.getLoudness() + delta_loud + " -- Fam: " + s.getArtist_familiarity() + delta_fam);
		double edge = s.getArtist_familiarity();
		while(data.size()<=0){
			System.out.println("loop<=0 line 102");
			edge = Math.random()*2;
			data = Hierarchy.getSimilarSongs(fmode, s.getTempo()+ delta_tempo, s.getLoudness() + delta_loud, edge + delta_fam, null, true);
		}
		
		
		if(data.size()>0 ){
			Song q = data.get(0);
			//data.remove(0);
			//multiple
			while(!MusicPlayer.getMP3(data.get(0).getTrack_7digitalid()) || playedSongs.contains(data.get(0))){
				data.remove(0);
				while(data.size() <= 0){
					int r = (int) (Math.random()*2);
					r= (r==0 ? -1 : 1);
					data = Hierarchy.getSimilarSongs(fmode, q.getTempo(), q.getLoudness(), q.getArtist_familiarity() + (0.1*r), null, true); //random song
				}
			}
			System.out.println("Mode: " + s.getMode() + " Tempo: " + s.getTempo() + " -- Loud: " + s.getLoudness() + " -- Hott: " + s.getSong_hotttnesss() + " -- Fam: " + s.getArtist_familiarity());
			playedSongs.add(data.get(0));
			g.addEdge(path.get(path.size()-1).getTitle(), data.get(0).getTitle());
			g.addVertex(data.get(0).getTitle());
			path.add(data.get(0));
			System.out.println(g.numVertices() + " -- " + g.numEdges());
			MusicPlayer.getMP3(data.get(0).getTrack_7digitalid());
			MusicPlayer newPlayer1 = new MusicPlayer(data.get(0));
			
			newPlayer1.addNextListener(a->{
				new GD();
			});
			
			newPlayer1.addExitListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					WindowsBuilderPractice run=new WindowsBuilderPractice(hierarchsongs);
					run.QA(data);
				}
			});
		}
		else{
//			if(data.size()>20)
//				data.remove(0);
			System.out.println(data.get(0).getArtist_name());
			System.out.println(data.get(0).getTitle());
			System.out.println(data.get(0).getArtist_7digitalid());
			System.out.println(data.get(0).getTrack_7digitalid());
			System.out.println(" ");
		}
		
		///////////////// THIS IS WHERE IT BECOMES RECURSIVE, CALL ON PLAY SONG WITH THE FIRST INDEX OF data, CALL ON QUESTION METHOD
		
		
		
		
	}
	
	/**
	 * Compiles a list of all the songs, mainly used for the subset of 10,000 songs
	 * @return List<Song> - a list of all songs.
	 */
	public static List<Song> allSongs(){
		List<Song> ret = new ArrayList<Song>();
		try(BufferedReader br = new BufferedReader(new FileReader(new File( "MSD.csv" )))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        ret.add(new Song(line));
		    }
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		//ret.remove(0);
		return ret;
	}

	
	
	
	
	/*  THIS SECTION WAS THE OLD VERSION TO COMPUTE SIMILAR SONGS BEFORE THE IMPLEMENTATION OF THE HIERARCHY */
	/*
	public static void sortArray(int[] intArray, String[] stringArray, int[] value, ArrayList<Integer> intAL, ArrayList<String> stringAL){
		
		for(int index = 0; index < 6; index++){
			for(int id2 = 0; id2 < 5; id2++ ){
				if(intArray[id2]==value[index]){
					intAL.add(intArray[id2]);
					stringAL.add(stringArray[id2]);
				}
			}
			
		}
		
	}
	
	private static void start(int value,String index,Song file){
		if(index.equals("Artist")){
		
			if(data.size()>20){
				artist(value,file);
			}
		}
		else if(index.equals("Tempo")){
			
			if(data.size()>20){
				tempo(value,file);
			}
		}
		else if(index.equals("Loudness")){
			
			if(data.size()>20){
				loudness(value,file);
			}
		}
		else if(index.equals("Hotness")){
			
			if(data.size()>20){
				songHot(value,file);
			}
		}
		else{
			
			if(data.size()>20){
				danceability(value,file);
			}
		}
		
	}
	
	
	private static void artist(int value,Song file) {
		
		List<Song> temp=new ArrayList<Song>();
		switch (value){
		case 2:
			try{
				
				String[] temporary=file.getSimilar_artists();
				if(temporary!=null){
					for(int b=0;b<temporary.length;b++){
						for(int a=0;a<data.size();a++){
							if(temporary[b].equals(data.get(a).getArtist_id()) && !temp.contains(data.get(a))){
								temp.add(data.get(a));
							}
						}
					}
					
					//data=new ArrayList<Song>();
					//data=temp;
					
				
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case -2:
			try{
				
				String[] temporary=file.getSimilar_artists();
				if(temporary!=null){
					for(int b=0;b<temporary.length;b++){
						for(int a=0;a<data.size();a++){
							if(temporary[b].equals(data.get(a).getArtist_id()) && !temp.contains(data.get(a))){
								temp.add(data.get(a));
							}
						}
					}
					
				if((data.size()-temp.size()>20))
						data.removeAll(temp);
					
				
					
				
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		
		case 1:
			try{
				
				String[] temporary=file.getSimilar_artists();
				if(temporary!=null){
					for(int b=0;b<temporary.length;b++){
						for(int a=0;a<data.size();a++){
							if(temporary[b].equals(data.get(a).getArtist_id()) && !temp.contains(data.get(a))){
								temp.add(data.get(a));
							}
						}
					}
					
					//data=new ArrayList<Song>();
					//data=temp;
					
				
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		case -1:
			try{
				
				String[] temporary=file.getSimilar_artists();
				if(temporary!=null){
					for(int b=0;b<temporary.length;b++){
						for(int a=0;a<data.size();a++){
							if(temporary[b].equals(data.get(a).getArtist_id()) && !temp.contains(data.get(a))){
								temp.add(data.get(a));
							}
						}
					}
					
					if((data.size()-temp.size()>20))
						data.removeAll(temp);
					
				
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case 0:
			
			break;
			
		case -3:
			
			break;
				
		default:
			System.out.println("There was an error!");
		}
	}
	
	private static void tempo(int value,Song file) {
		int rangeSmall=25;
		int rangeBig=35;
		List<Song> temp=new ArrayList<Song>();
		
		switch (value){
		case 2:
			try{
				double temporary=file.getTempo();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getTempo()>temporary-rangeSmall) && (data.get(a).getTempo()<temporary+rangeSmall))){
							if(data.get(a).getTempo()!=0){
								temp.add(data.get(a));
							}
						}
					}
					
					if((data.size()-temp.size()>20))
						data.removeAll(temp);
				
		
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case -2:
			try{
				double temporary=file.getTempo();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getTempo()>temporary-rangeBig) && (data.get(a).getTempo()<temporary+rangeBig))){
							if(data.get(a).getTempo()!=0){
								temp.add(data.get(a));
							}
						}
					}
					
					if((data.size()-temp.size()>20))
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		
		case 1:
			try{
				double temporary=file.getTempo();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getTempo()>temporary-rangeBig) && (data.get(a).getTempo()<temporary+rangeBig))){
							if(data.get(a).getTempo()!=0){
								temp.add(data.get(a));
							}
						}
					}
					
					if((data.size()-temp.size()>20))
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		case -1:
			try{
				double temporary=file.getTempo();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getTempo()>temporary-rangeSmall) && (data.get(a).getTempo()<temporary+rangeSmall))){
							if(data.get(a).getTempo()!=0){
								temp.add(data.get(a));
							}
						}
					}
					
					if((data.size()-temp.size()>20))
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case 0:
			
			break;
			
		case -3:
			
			break;
				
		default:
			System.out.println("There was an error!");
		}
	}
	private static void loudness(int value,Song file) {
		int rangeSmall=4;
		int rangeBig=6;
		List<Song> temp=new ArrayList<Song>();
		
		switch (value){
		case 2:
			try{
				double temporary=file.getLoudness();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getLoudness()>temporary-rangeSmall) && (data.get(a).getLoudness()<temporary+rangeSmall))){
							
							temp.add(data.get(a));
							
						}
					}
					
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case -2:
			try{
				double temporary=file.getLoudness();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getLoudness()>temporary-rangeBig) && (data.get(a).getLoudness()<temporary+rangeBig))){
						
							temp.add(data.get(a));
							
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		
		case 1:
			try{
				double temporary=file.getLoudness();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getLoudness()>temporary-rangeBig) && (data.get(a).getLoudness()<temporary+rangeBig))){
							
							temp.add(data.get(a));
							
						}
					}
					if((data.size()-temp.size())>20)
	
						data.removeAll(temp);
					
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		case -1:
			try{
				double temporary=file.getLoudness();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getLoudness()>temporary-rangeSmall) && (data.get(a).getLoudness()<temporary+rangeSmall))){
							temp.add(data.get(a));
							
						}
					}
					if((data.size()-temp.size())>20)

						data.removeAll(temp);
					
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case 0:
			
			break;
			
		case -3:
			
			break;
				
		default:
			System.out.println("There was an error!");
		}
	}
	private static void songHot(int value,Song file) {
		double rangeSmall=0.3;
		double rangeBig=0.9;
		List<Song> temp=new ArrayList<Song>();
		
		switch (value){
		case 2:
			try{
				double temporary=file.getSong_hotttnesss();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getSong_hotttnesss()>temporary-rangeSmall) && (data.get(a).getSong_hotttnesss()<temporary+rangeSmall))){
							if(data.get(a).getSong_hotttnesss()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case -2:
			try{
				double temporary=file.getSong_hotttnesss();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getSong_hotttnesss()>temporary-rangeBig) && (data.get(a).getSong_hotttnesss()<temporary+rangeBig))){
							if(data.get(a).getSong_hotttnesss()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		
		case 1:
			try{
				double temporary=file.getSong_hotttnesss();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getSong_hotttnesss()>temporary-rangeBig) && (data.get(a).getSong_hotttnesss()<temporary+rangeBig))){
							if(data.get(a).getSong_hotttnesss()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		case -1:
			try{
				double temporary=file.getSong_hotttnesss();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getSong_hotttnesss()>temporary-rangeSmall) && (data.get(a).getSong_hotttnesss()<temporary+rangeSmall))){
							if(data.get(a).getSong_hotttnesss()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case 0:
			
			break;
			
		case -3:
			
			break;
				
		default:
			System.out.println("There was an error!");
		}
	}
	
	private static void danceability(int value,Song file) {
		double rangeSmall=0.2;
		double rangeBig=0.6;
		List<Song> temp=new ArrayList<Song>();
		
		switch (value){
		case 2:
			try{
				double temporary=file.getDanceability();
				
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getDanceability()>temporary-rangeSmall) && (data.get(a).getDanceability()<temporary+rangeSmall))){
							if(data.get(a).getDanceability()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case -2:
			try{
				double temporary=file.getDanceability();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getDanceability()>temporary-rangeBig) && (data.get(a).getDanceability()<temporary+rangeBig))){
							if(data.get(a).getDanceability()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		
		case 1:
			try{
				double temporary=file.getDanceability();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(!((data.get(a).getDanceability()>temporary-rangeBig) && (data.get(a).getDanceability()<temporary+rangeBig))){
							if(data.get(a).getDanceability()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
		case -1:
			try{
				double temporary=file.getDanceability();
				if(temporary!=0){
					
					for(int a=0;a<data.size();a++){
						if(((data.get(a).getDanceability()>temporary-rangeSmall) && (data.get(a).getDanceability()<temporary+rangeSmall))){
							if(data.get(a).getDanceability()!=0){
								temp.add(data.get(a));
							}
						}
					}
					if((data.size()-temp.size())>20)
						data.removeAll(temp);
				
					
				}
			}
			catch(Exception e){
				System.out.println("error");
				e.printStackTrace();
			}
			break;
			
		case 0:
			
			break;
			
		case -3:
			
			break;
				
		default:
			System.out.println("There was an error!");
		}
	}
	*/
}