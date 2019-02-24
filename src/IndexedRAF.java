
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class IndexedRAF extends RandomAccessFile {
	private ArrayList<RAFLine> map; //K: file pointer loc, V: RAFLine .line, .bytes
	private String fname; //stores file path
	final static String EXT = ".IRAF";
	private long numLines; //stores number of lines in file
	private boolean existing; //stores whether this was an existing RAF file or a new RAF
	private final static short IDENTIFIER = Short.MAX_VALUE - 21; //Identifier at front of file for determining whether the file is an IRAF or not
	
/**
 * Init with the file and the mode, read or write
 * @param file
 * @param mode
 * @throws ClassNotFoundException
 * @throws IOException
 */
	public IndexedRAF(File file, String mode) throws ClassNotFoundException, IOException {
		super(file, mode);
		init(file.getAbsolutePath(), mode);
	}

	/**
	 * Init with the file and the mode, read or write
	 * @param file
	 * @param mode
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public IndexedRAF(String file, String mode) throws ClassNotFoundException, IOException {
		super(file, mode);
		init(file, mode);
	}
	
	/**
	 * Sorts the song data by whatever metric type is passed in
	 * @param sortBy
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void sort(S sortBy) throws NumberFormatException, IOException{ //TODO try with Integer object
		Song.initHash();
		int sortind = Song.map.get(sortBy); //get index of item in string
		
		    int ins, i, j;
			int n=(int) (numLines);
		    RAFLine tmp;

		    //BINARY INSERTION SORT
		    for (i = 1; i < n; i++) {
		    	seek(map.get( i).pointer);
		    	
		    	//System.out.println("Sorting: " + i);
		    	
		    	switch(sortBy){
		    	case tempo: ins = binSrchD ( 0, (int) (i), Double.parseDouble(readLine().split("<S>")[sortind]), sortind); break;
		    	case loudness: ins = binSrchD ( 0, (int) (i), Double.parseDouble(readLine().split("<S>")[sortind]), sortind); break;
		    	case song_hotttnesss: ins = binSrchD ( 0, (int) (i), Double.parseDouble(readLine().split("<S>")[sortind]), sortind); break;
		    	case artist_familiarity: ins = binSrchD ( 0, (int) (i), Double.parseDouble(readLine().split("<S>")[sortind]), sortind); break;
				default: ins = binSrchS ( 0, (int) (i), readLine().split("<S>")[Song.map.get(S.title)], Song.map.get(S.title)); break;
		    	}
		    	
		        tmp = map.get(i);
		        for (j = i - 1; j >= ins; j--)
		            map.set( (j+1), map.get( j));//a[j + 1] = a[j];
		        map.set( ins, tmp); //;a[ins] = tmp;
		    }
	}
	
	/**
	 * Binary search of double value, or closest inbetween
	 * @param lo
	 * @param hi
	 * @param key
	 * @param sortind
	 * @return
	 * @throws IOException
	 */
	public int binSrchD(int lo, int hi, double key, int sortind) throws IOException{
		int mid = -1;

		//STANDARD BINARY SEARCH FOR INSERTION BINARY (meaning find the closest value if one does not exist)
		while(!(lo==hi)){
	    mid = lo + ((hi - lo) / 2);
	    
	    RAFLine find = map.get( mid);
	    seek(find.pointer);
	    double val = Double.parseDouble( readLine().split("<S>")[sortind] );
	    if(val == key)
	    	return mid;
	    if(lo==hi)
	    	return lo;
	    //System.out.println("Lo: " + lo + " Hi: " + hi + " val: "  + val + " key: " + key);
	    if (key > val)
	        lo=mid+1;
	    else if (key < val)
	        hi=mid;
	    else
	    	return mid;
		}
		return lo;
	}
	
	/**
	 * Binary search of int value, or closest inbetween
	 * @param lo
	 * @param hi
	 * @param key
	 * @param sortind
	 * @return
	 * @throws IOException
	 */
	public int binSrchI(int lo, int hi, int key, int sortind) throws IOException{
		int mid = -1;

		while(!(lo==hi)){

	    mid = lo + ((hi - lo) / 2);
	    
	    RAFLine find = map.get(mid);
	    seek(find.pointer);
	    int val = Integer.parseInt( readLine().split("<S>")[sortind] );
	    if (key > val)
	        lo=mid+1;
	    else if (key < val)
	        hi=mid;
	    
		}
		return lo;
	}
	
	/**
	 * Binary search of String value
	 * @param lo
	 * @param hi
	 * @param key
	 * @param sortind
	 * @return
	 * @throws IOException
	 */
	public int binSrchS(int lo, int hi, String key, int sortind) throws IOException{
		int mid = -1;

		while(!(lo==hi)){

	    mid = lo + ((hi - lo) / 2);
	    
	    RAFLine find = map.get(mid);
	    seek(find.pointer);
	    String val = readLine().split("<S>")[sortind] ;
	    if (key.compareTo(val) >0)
	        lo=mid+1;
	    else if (key.compareTo(val) < 0)
	        hi=mid;
	    
		}
		return lo;
	}
	
	//init method called on instantiation
	private void init(String file, String mode) throws ClassNotFoundException, IOException {
		File f = new File(file);
		long timestamp = f.lastModified();
		if(super.length()>=2){
			if(!isRaf(this)) //check if valid IRAF file
				throw new IOException("Not an IndexedRAF file.");
				
		}
		else{ //if not make it one
			System.out.println("DEFAULTING");
			writeShort(IDENTIFIER);
			writeLong(0);
		}
		f = new File(file + EXT);
		map = (f.exists()&&!f.isDirectory() && timestamp == f.lastModified() ?loadLines(file) : getHashMap(f));
		fname = file;
		if(!existing)
			numLines = countLines(); //load number of lines
		seek(0);
	}
	
	/**
	 * Checks if a given file is an IRAF or not
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static boolean isRaf(RandomAccessFile f) throws IOException {
		long pos = f.getFilePointer();
		if(f.length()<0)
			return false;
		if(f instanceof IndexedRAF)
			f.seek(-10);
		else
			f.seek(0);
		boolean ret = (f.readShort() == IDENTIFIER); //check if file IDentifier is there
		f.seek(pos);
		return ret;
	}

	//count how many lines in a file
	private long countLines() throws IOException {
		LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(fname)));
		lnr.skip(Long.MAX_VALUE);
		long ret = lnr.getLineNumber();
		lnr.close();
		return ret;
	}

	//loads metadata for IRAF (header file)
	private ArrayList<RAFLine> loadLines(String file) throws ClassNotFoundException, IOException {
		System.out.println("Stamps match, loading..");
		existing = true;
		file+=EXT;
		FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        ArrayList<RAFLine> data = (ArrayList<RAFLine>) in.readObject();  //serialize in the previously written out object
        in.close();
        fileIn.close();
        super.seek(2);
        numLines = super.readLong(); //load number of lines
        seek(0);
		return data;
	}
	
	/**
	 * Returns a line of a file given a line index
	 * @param lineIndex
	 * @return
	 * @throws IOException
	 */
	public String getLine(int lineIndex) throws IOException{
		seek(map.get(lineIndex).pointer);
		return readLine();
	}
	
	/**
	 * Returns a line of a file given a pointer location
	 * @param longpointer
	 * @return
	 * @throws IOException
	 */
	public String getLine(long pointer) throws IOException{
		seek(pointer);
		return readLine();
	}
	
	/**
	 * returns the size of the file in lines
	 * @return
	 */
	public long size(){
		return numLines;
	}
	
	/**
	 * Returns a specified value in the line specified, such as Tempo, etc.
	 * @param lineIndex
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String value(int lineIndex, S s) throws IOException{
		Song.initHash();
		int i = Song.map.get(s);
		seek(map.get(lineIndex).pointer);
		return readLine().split("<S>")[i];
	}
	
	/**
	 * Returns a specified value at the pointer specified, such as Tempo, etc.
	 * @param lineIndex
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public String value(long pointer, S s) throws IOException{
		Song.initHash();
		int i = Song.map.get(s);
		seek(pointer);
		return readLine().split("<S>")[i];
	}

	/**
	 * Writes a line at the end of the file
	 * @param paramString
	 * @throws IOException
	 */
	public final void writeLine(String paramString)
		    throws IOException
		  {
			paramString+=System.lineSeparator();
			long orig = getFilePointer();
			long pointer = length();
			seek(pointer);
			map.add( new RAFLine(pointer, paramString.getBytes().length));
			numLines++;
		    writeBytes(paramString);
		    seek(orig);
		  }
	/**
	 * Writes a line at the insert point, adjusting all succeeding lines. Line number starts from 1 and goes to the actual number of lines
	 * @param paramString
	 * @throws IOException
	 */
	public final void insertLine(int lineIndex, String paramString)
		    throws IOException
		  {
			RAFLine val = map.get( lineIndex);
			if(val==null)
				throw new IOException("There was no line data. Check to make sure the accessory .IRAF file is not being modified.");
		    String last = paramString;
		    String curr;
		    numLines++;
		    int line = lineIndex;
			while(line <= numLines){
				long posCurr = getFilePointer();
				curr = readLine();
		//		long posNextOld = getFilePointer();
				seek(posCurr);
				writeBytes(last + "\n");
				
				RAFLine oldCurr = map.get( line);
				map.set( line-1, new RAFLine(oldCurr.pointer, last.getBytes().length));
				line++;
				last = curr;
			}
			
			writeBytes(paramString);
		  }
	
	/**
	 * Returns the file pointer index of the next line
	 * @return
	 * @throws IOException 
	 */
	public long nextLine() throws IOException{
		this.readLine();
		return getFilePointer();
	}
	
	@Override
	public void seek(long pos) throws IOException{
		super.seek(pos+10);
	}
	
	@Override
	public long getFilePointer() throws IOException {
		return super.getFilePointer()-10;
	}
	
	@Override
	public long length() throws IOException {
		return super.length()-10;
	}
	
	@Override
	public void close() throws IOException{
		//write hte identifier and line count
		super.seek(0);
		super.writeShort(IDENTIFIER);
		super.seek(2);
		super.writeLong(numLines);
		FileOutputStream fileOut = 
		         new FileOutputStream(fname + EXT);
		         ObjectOutputStream out = new ObjectOutputStream(fileOut); //serialize out header file
		         out.writeObject(map); //serialization
		         out.close();
		         fileOut.close();
		super.close();
		File f = new File(fname);
		File g = new File(fname + EXT);
		long timeStamp = System.currentTimeMillis();
		f.setLastModified(timeStamp);//time stamp them so they match and any modifications dont break the file
		g.setLastModified(timeStamp);
	}
	
	/**
	 * Converts and generates proper files for a given file path
	 * @param in
	 * @throws IOException
	 */
	public static void convertToIRAF(File in) throws IOException{
		File out = new File("auysfdhiuasf23423asdjn2.sgsgsg"); //temp file
		RandomAccessFile fileIn = new RandomAccessFile( in, "r" );
		RandomAccessFile o = new RandomAccessFile(out, "rw");
		RandomAccessFile exist = new RandomAccessFile(in, "rw");
		if(isRaf(exist)){ //check that its not already a IRAF
			System.out.println("Already RAF");
			fileIn.close();
			o.close();
			exist.close();
			return;
		}
		exist.close();
		
		ArrayList<RAFLine> map = new ArrayList<RAFLine>(10000); //map of all data
		
		LineNumberReader  lnr = new LineNumberReader(new FileReader( in ));
		lnr.skip(Long.MAX_VALUE);
		long count = lnr.getLineNumber();
		lnr.close();
		o.seek(0);
		o.writeShort(IDENTIFIER);
		o.seek(2);
		o.writeLong(count);
		String line;
		long pointer = 0;
		
		//line by line rewrite the file so that its binary header is present
		while((line = fileIn.readLine())!=null){
			RAFLine x = new RAFLine(pointer, (line+System.lineSeparator()).getBytes().length );
			map.add( x);
			pointer = fileIn.getFilePointer();
			o.writeBytes(line + System.lineSeparator());
		}
		fileIn.close();
		o.close();
		in.delete();
		Files.move(Paths.get(out.getPath()), Paths.get(in.getPath())); //rename the file to the existing file
		out = new File("auysfdhiuasf23423asdjn2.sgsgsg");
		out.delete();
		FileOutputStream fileOut =
		         new FileOutputStream(in.getName() + EXT);
		         ObjectOutputStream outS = new ObjectOutputStream(fileOut);
		         outS.writeObject(map); //serialize out hte header file data
		         outS.close();
		         fileOut.close();
		File h = new File(in.getName() + EXT);
		File j = new File(in.getName());
		long stamp = System.currentTimeMillis();
		h.setLastModified(stamp);
		j.setLastModified(stamp);
	}
	
	//returns 
	private static ArrayList<RAFLine> getHashMap(File file) throws IOException{
		ArrayList<RAFLine> map = new ArrayList<RAFLine>(10000);
		if(!file.exists() || file.isDirectory())
			return map;
		RandomAccessFile fileIn = new RandomAccessFile( file, "r" );
		String line;
		int linecount = 0;
		long pointer = 0;
		while((line = fileIn.readLine())!=null){
			RAFLine x = new RAFLine(pointer, (line+System.lineSeparator()).getBytes().length );
			map.add( x);
			linecount++;
			pointer = fileIn.getFilePointer();
		}
		fileIn.close();
		return map;
	}

	public ArrayList<RAFLine> getMap() {
		return map;
	}

	public long getNumLines() {
		return numLines;
	}

}
