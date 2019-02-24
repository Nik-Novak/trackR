

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer extends JFrame{
	/**
	 * 
	 */
	
	private MediaPlayer player;
	private Media m;
	private static final long serialVersionUID = 8820422501978031541L;
	private JButton playback;
	private JButton next;
	private JButton exit;
	Timer time;
	private List<ActionListener> nextListeners = new ArrayList<ActionListener>();
	private List<ActionListener> exitListeners= new ArrayList<ActionListener>();
	
	private int R, G, B, Rswing, Gswing, Bswing;
	
	private ImageIcon PLAY = new ImageIcon("Resources/Play.png");
	private final static ImageIcon NEXT = new ImageIcon("Resources/Next.png");
	private final static ImageIcon EXIT = new ImageIcon("Resources/Exit.png");
	
//	public static void main(String[] args) throws InterruptedException{
//		new MusicPlayer(new Song("0.5817937658450281<S>0.4019975433642836<S>ARD7TVE1187B99BFB1<S>e77e51a5-4761-45b3-9847-2051f811e366<S>4479<S>165270<S>NaN<S>NaN<S>California - LA<S>Casual<S>Fear Itself<S>SOMZWCG12A8C13C480<S>0.6021199899057548<S>I Didn't Mean To<S>3401791<S>[ARV4KO21187FB38008:ARWHM281187FB3D381:ARJGOG11187B98D89F:AR9ODB41187FB459B2:ARXM6VQ1187FB5B1E0:ARNWZ1N1187B9B71BA:ARDWYZZ11F4C8413FA:ARTP3H51187B98FB75:ARWCDXN12454A4D1E8:ARJ54S61187B9ACD39:AR5PF241187B989C1D:ARR7MLL1187B99B636:ARLMHFV1187B9A3833:ARPRERY1187B99E2DC:AR34BCQ1187B9A68E4:ARFWBUC11F4C8413DA:ARPWGMN1187FB560E3:ARVCIVW12454A4D1E7:ARG89HY1187FB3CA15:AR9IGU51187FB40D6B:ARNNOYR11F4C845127:ARZMFNT11F4C8413DD:ARPR9W71187FB3723A:AR5VBGP1187B98EB43:ARFHDOI1187FB57230:ARBSQPF11F4C8413E0:AROYGID11F4C8413DB:ARDXUGZ11F4C84452F:ARMW4I01187B98AEF8:AR7AYQG1187B994B3F:ARHVZEM11F4C841FF9:ARP9H0U1187FB3FEA7:ARVSIGU11F4C8413E6:AROWKNS1187FB59ED5:ARUSTLW11F4C8413DE:ARSKPDX11F4C83D2A9:ARB4D891187B9954F7:ARRIWD31187B9A9B4A:ARNAAQH11F4C8413E1:ARVRVYO11F4C8413DF:ARNIZ5P1187B989AF5:AR6YR7H1187FB40D23:ARVAOCN12454A4CD5D:ARQN3MO1187B98A811:AR6M50Z1187B9A3503:ARYEBOD11F4C83CD89:ARQTORN11F50C4CED2:ARUGOBE1269FCD74CE:ARD3STO1187FB3E36A:ARXU8YQ1187B999861:AR0D3LM1187B9B9592:ARCTCBU11F4C8413DC:ARAVZPG1187B98F815:AR79GH61187B993492:ARBCGAP12086C13383:ARLQK3P1187FB42113:ARGNP1T1187FB5024D:ARDIP5S1187FB57056:ARWG53T1187FB44281:ARV7RUN1187B9AF59C:ARHAEF41187FB38ECB:AR6UET41187B98C8BE:ARYYFT91187FB50113:ARD1KGL1187B98C838:ARKBKTR1187FB39255:ARRPOUR1187FB4D0BA:AR6VXP71187FB5B898:ARRWZGD11F50C4EDAE:ARVCPYN11F4C83B19D:AROKSCN1269FCD6CF1:AR7YOK41187B9A3E0C:ARSVLBA11F50C5126C:ARGVYYD11C8A416342:ARSK1IB1187B996850:ARD39VZ1187B9B9A57:ARIPNAN1187FB3E577:ARK276D1187FB3D8FC:AROWLBR11F4C84790E:ARRX19M1187FB5756D:ARLERFY11F50C4ABF2:ARJ5PHW119B8668EB3:ARQIMRI11F4C845124:AR6S4EF1187B99A528:ARYSPOI11F4C847915:ARVFENS11F50C4ABE5:ARTH9041187FB43E1F:ARRZSQJ1269FB30A5F:ARWFREB1187B98B09D:ARZ3EIB1187B989D35:ARNHXXE11F4C84129E:ARACWVH1187FB55452:AR3QS2R1187B9953C7:ARTXGGI1187B9B3D58:AR92C2I1187FB4161C:ARNNCRQ11F50C4ABE7:ARQPFMZ1187B9B1504:ARPZEFW11F50C47E67:ARSIEKZ122ECCBC0B1:AR0WMTB1187B9A8E0F:ARHRRWZ11F4C846224]<S>[hip hop:underground rap:g funk:alternative rap:gothic rock:west coast rap:rap:club dance:singer-songwriter:chill-out:underground hip hop:rock:gothic:san francisco bay area:indie:american:punk:california:industrial:new york:90s:latin:spanish:dark:ebm:underground:deathrock:west coast:san francisco:producer:oakland:catalan:barcelona:doomsdope:norcal:west coast hip hop:alternative rock]<S>a222795e07cd65b7a530f1346f520649<S>0.0<S>218.93179<S>0.247<S>0.0<S>1<S>0.736<S>-11.197<S>0<S>0.636<S>218.932<S>92.198<S>4<S>0.778<S>TRAAAAW128F429D538<S>0"));
//	}
	
	public MusicPlayer(Song song){
		super("TrackR Music Player");
		
		JFXPanel fxPanel = new JFXPanel();
		File q = new File("data1.dat");
		m=new Media( Paths.get("song.mp3").toUri().toString() );
		MediaPlayer player = new MediaPlayer(m);
		PLAY.setDescription("Pause");
		this.setLayout(null);
		this.setDefaultCloseOperation(1);
		this.setUndecorated(true);
		this.setPreferredSize(new Dimension(400, 200));
		
		playback = new JButton(PLAY);
		playback.setBorder(BorderFactory.createEmptyBorder());
		playback.setContentAreaFilled(false);
		playback.setSize(102,102);
		playback.setVisible(true);
		playback.setLocation(5,this.getPreferredSize().height-playback.getHeight()-5);
		playback.addActionListener(e->{
			if(PLAY.getDescription().equals("Pause")){

			PLAY = new ImageIcon("Resources/Pause.png"); playback.setIcon(PLAY); PLAY.setDescription("Play"); player.play();
			}
			else{
				PLAY = new ImageIcon("Resources/Play.png"); playback.setIcon(PLAY); PLAY.setDescription("Pause"); player.pause();
			}
			});
		
		next = new JButton(NEXT);
		next.setBorder(BorderFactory.createEmptyBorder());
		next.setContentAreaFilled(false);
		next.setSize(102,102);
		next.setVisible(true);
		next.setLocation(this.getPreferredSize().width/2-playback.getWidth()/2,this.getPreferredSize().height-playback.getHeight()-5);
		next.addActionListener(e->{
			for(ActionListener i : nextListeners){
				i.actionPerformed(new ActionEvent(this,0,"Next"));
			}
		});
		
		exit = new JButton(EXIT);
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setContentAreaFilled(false);
		exit.setSize(102,102);
		exit.setVisible(true);
		exit.setLocation(this.getPreferredSize().width-playback.getWidth() - 5,this.getPreferredSize().height-playback.getHeight()-5);
		exit.addActionListener(e->{
			for(ActionListener i : exitListeners){
				i.actionPerformed(new ActionEvent(this,0,"Exit"));
			}
			player.stop();
			this.dispose();
			});
		
		JLabel lbl = new JLabel(song.getTitle());
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		Font old = new Font(lbl.getFont().getName(), lbl.getFont().getStyle(), lbl.getFont().getSize()+10);
		lbl.setFont(old);
		lbl.setLocation(0, 0);
		lbl.setSize(400, 50);
		
		JLabel lbl2 = new JLabel(song.getArtist_name());
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setFont(old);
		lbl2.setLocation(0, 50);
		lbl2.setSize(400, 50);
		
		this.getContentPane().add(lbl);
		this.getContentPane().add(lbl2);
		this.getContentPane().add(playback);
		this.getContentPane().add(next);
		this.getContentPane().add(exit);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		R=(int) (Math.random()*256); G=(int) (Math.random()*256); B= (int) (Math.random()*256);
		Rswing = (int) (Math.random()*2); Gswing = (int)(Math.random()*-2); Bswing = (int) (Math.random()*2);
		int t = (int) (song==null || song.getTempo() <=0? 20: map((long) song.getTempo(), 40, 210, 30, 5));
		time = new Timer(t, e-> {
			
			int Rswitch = (int) (Math.random()*210);
			int Gswitch = (int) (Math.random()*210);
			int Bswitch = (int) (Math.random()*210);
			
			if(Rswitch == 10 || R<2 || R > 253)
				Rswing *=-1;
			if(Gswitch == 10 || R<2 || R > 253)
				Gswing*=-1;
			if(Bswitch == 10 || R<2 || R > 253)
				Bswing*=-1;
			
			R+= (int) (Math.random()*3) - 1 + Rswing;
			G+= (int) (Math.random()*3) - 1 + Gswing;
			B+= (int) (Math.random()*3) - 1 + Bswing;
			if(R>255)
				R=255;
			else if(R<0)
				R=0;
			if(G>255)
				G=255;
			else if(G<0)
				G=0;
			if(B>255)
				B=255;
			else if(B<0)
				B=0;
			this.getContentPane().setBackground(new Color(R, G, B));
		}); 
		time.start();	
	}
	
	protected static boolean getMP3(int track_7digitalid) {
		try {
			Files.delete(Paths.get("song.mp3"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("File did not exist");
		}
		
		try {
			Process p = Runtime.getRuntime().exec("py test7dig.py " + track_7digitalid);
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = null;
		try {
			url = Files.readAllLines(Paths.get("fileurl.txt")).get(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		if(url==null || url.equals(""))
			return false;
		
		
		try {
		URL website;
		website = new URL(url);
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("song.mp3");
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}

	public void addNextListener(ActionListener a){
		nextListeners.add(a);
	}
	
	public void addExitListener(ActionListener a){
		exitListeners.add(a);
	}
	
	private long map(long x, long in_min, long in_max, long out_min, long out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
