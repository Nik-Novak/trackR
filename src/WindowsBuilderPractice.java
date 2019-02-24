/*
 * Josh Mitchell - 1422856
 * Nik Novak - 1406602
 * Matthew Shortt - 1417616
 * Phillip Pavlich - 1414960
 * Erin Varey - 1400605
 * 
 * class: WindowsBuilderPractice
 * This class deals with the questions for the program. They ask the user a series of questions related to the song that the
 * user just got a preview of. The answers that the user gives are stored in an array and are used to calculate a future song 
 * based on the way the user answered the questions.
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.TextField;
public class WindowsBuilderPractice {
	
	private JFrame frmTrackr;
	private JTextField txtStrongly;
	private JTextField txtDisagree;
	private JTextField txtMaybe;
	private JTextField txtAgree;
	private JTextField txtStrongly_1;
	private JTextField txtI;
	private int count =0;
	
	private static int numOfQuestions=4;
	protected static int [] questionResponses=new int[numOfQuestions];
	private JSlider slider;
	public List<Song> DATA;
	public int valueChecker;
	/**
	 * The constructor launches the question process.
	 */
	public WindowsBuilderPractice(List<Song> DATA) {
		this.DATA=DATA;
		
		initialize();
	}
	/**
	 * QA calls on the question process and prints out the artist that was listened to
	 */
	public static void QA(List<Song> DATA) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowsBuilderPractice window = new WindowsBuilderPractice(DATA);
					window.frmTrackr.setVisible(true);
					System.out.println(DATA.get(0).getArtist_name());
					System.out.println(DATA.get(0).getTitle());
					System.out.println(DATA.get(0).getArtist_7digitalid());
					System.out.println(DATA.get(0).getTrack_7digitalid());
					System.out.println(" ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * initialize is a method that explains the series of questions that the user will need to answer, button to continue
	 */
	private void initialize() {
		frmTrackr = new JFrame();
		frmTrackr.setTitle("TrackR");
		frmTrackr.getContentPane().setBackground(new Color(255, 255, 255));
		frmTrackr.setBounds(100, 100, 700, 500);
		frmTrackr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrackr.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Click Here to Begin!");
		btnNewButton.addActionListener(new ActionListener() {//when button begin is clicked
			public void actionPerformed(ActionEvent arg0) {
				frmTrackr.getContentPane().removeAll();///this is how to remove stuff
				frmTrackr.getContentPane().repaint();
				
				questionNumber();	
			}
		});
		btnNewButton.setToolTipText("Start!");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(260, 300, 191, 63);
		frmTrackr.getContentPane().add(btnNewButton);
	
		JTextArea txtrAfterListeningTo = new JTextArea();
		txtrAfterListeningTo.setBackground(SystemColor.info);
		txtrAfterListeningTo.setEditable(false);
		txtrAfterListeningTo.setWrapStyleWord(true);
		txtrAfterListeningTo.setFont(new Font("Calibri", Font.PLAIN, 18));
		txtrAfterListeningTo.setLineWrap(true);
		txtrAfterListeningTo.setText("Now that you have listened to this song, we will ask you a series of questions to help pick a song that fits your interests. Please answer the following questions honestly based on the numbering system to the left of the questions.");
		txtrAfterListeningTo.setBounds(79, 65, 500, 91);
		frmTrackr.getContentPane().add(txtrAfterListeningTo);
		
		
		JLabel lblHello = new JLabel("");
		Image musicPNG = new ImageIcon(this.getClass().getResource("/music1.png")).getImage();
		lblHello.setBounds(0, 0, 682, 453);
		
		lblHello.setIcon(new ImageIcon(musicPNG));
		frmTrackr.getContentPane().add(lblHello);
	
		
	}
	
	/**
	 * questionNumber is a method that determines which question number the player is on
	 */
	private void questionNumber(){
		if(count<numOfQuestions){
			checkQ(count);
			count++;
		}
		else{
			end();
		}
	}
	
	/**
	 * checkQ is a method that declares the values for the sliders and the question that will be asked
	 * @param i
	 */
	private void checkQ(int i){
		switch (i){
			case 0:
				String a="I liked the mood of the song!";
				String[] moodStr={"0 - No!","1 - Don't Care", "2 - Yes"};
				questions(a,moodStr);
				break;
				
			case 1:
				String b="I liked the tempo of this song!";
				String[] tempoStr={"0 - I don't care about tempo!","1 - Song is way too fast", "2 - Song is too fast", "3 - I liked the tempo", "4 - Song is too slow","5 - Song is way too slow"};
				questions(b,tempoStr);
				break;
			
			case 2:
				String d="I liked the loudness of the song!";
				String[] loudnessStr={"0 - I don't care about loudness!","1 - Song is way too loud", "2 - Song is too loud", "3 - I liked the loudness", "4 - Song is too quiet","5 - Song is way too quiet"};
				questions(d,loudnessStr);
				break;
			case 3:
				String e="Artist popularity matters to me.";
				String[] artistStr={"0 - Irrelevant Category","1 - Strongly Disagree", "2 - Disagree", "3 - Neutral", "4 - Agree","5 - Strongly Agree"};
				questions(e,artistStr);
				break;
					
			default:
				System.out.println("There was an error!");
		}
	}
	
	/**
	 * questions method is the screen in which each question is asked. Features such as sliders to record the users answers are used
	 * @param question
	 * @param conditions
	 */
	private void questions(String question, String[] conditions ){
		JTextField txtStrongly_2 = new JTextField();
		txtStrongly_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtStrongly_2.setBackground(new Color(255, 255, 0));
		Border redline1 = BorderFactory.createLineBorder(Color.red,3);
		txtStrongly_2.setBorder(redline1);
		txtStrongly_2.setEditable(false);
		txtStrongly_2.setText("Q"+(count+1));
		txtStrongly_2.setBounds(625, 25, 30, 30);
		frmTrackr.getContentPane().add(txtStrongly_2);
		
		//JSlider slider = new JSlider();
		slider = new JSlider();
		slider.setBounds(320, 230, 300, 62);
		Border blueline = BorderFactory.createLineBorder(Color.blue,3);
		slider.setBorder(blueline);
		
		//this next part is unique for phil's
		slider.setBackground(Color.YELLOW);
		
	    slider.setMajorTickSpacing(1);
	    slider.setMinorTickSpacing(1);
	    if(!question.equals("I liked the mood of the song!")){
	    slider.setMinimum(0);
	    slider.setMaximum(5);
	    slider.setValue(3);
	    }
	    else{
		    slider.setMinimum(0);
		    slider.setMaximum(2);
		    slider.setValue(1);
	    }
	    slider.setPaintLabels(true);
	    slider.setPaintTicks(true);
	    slider.setSnapToTicks(true);
	    frmTrackr.getContentPane().add(slider,BorderLayout.SOUTH);
	    
	    //this next part is unique to matts
	    /*
	    JTextField sliderBackground = new JTextField();
		sliderBackground.setBackground(new Color(255, 255, 0));
		sliderBackground.setBounds(190, 240, 300, 62);
		frmTrackr.getContentPane().add(sliderBackground);
		*/
	    
		if(!question.equals("I liked the mood of the song!")){
		txtStrongly_1 = new JTextField();
		txtStrongly_1.setEditable(false);
		txtStrongly_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtStrongly_1.setText(conditions[conditions.length-1]);
		txtStrongly_1.setBounds(0, 0, 290, 38);
		frmTrackr.getContentPane().add(txtStrongly_1);
		txtStrongly_1.setColumns(10);
		
		txtAgree = new JTextField();
		txtAgree.setEditable(false);
		txtAgree.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAgree.setText(conditions[conditions.length-2]);
		txtAgree.setBounds(0, 38, 290, 38);
		frmTrackr.getContentPane().add(txtAgree);
		txtAgree.setColumns(10);
		
		txtMaybe = new JTextField();
		txtMaybe.setEditable(false);
		txtMaybe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaybe.setText(conditions[conditions.length-3]);
		txtMaybe.setBounds(0, 76, 290, 38);
		frmTrackr.getContentPane().add(txtMaybe);
		txtMaybe.setColumns(10);
		
		txtDisagree = new JTextField();
		txtDisagree.setEditable(false);
		txtDisagree.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDisagree.setText(conditions[conditions.length-4]);
		txtDisagree.setBounds(0, 114, 290, 38);
		frmTrackr.getContentPane().add(txtDisagree);
		txtDisagree.setColumns(10);
		
		txtStrongly = new JTextField();
		txtStrongly.setEditable(false);
		txtStrongly.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtStrongly.setText(conditions[conditions.length-5]);
		txtStrongly.setBounds(0, 152, 290, 38);
		frmTrackr.getContentPane().add(txtStrongly);
		txtStrongly.setColumns(10);
		
		txtI = new JTextField();
		txtI.setEditable(false);
		txtI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtI.setText(conditions[conditions.length-6]);
		txtI.setBounds(0, 190, 290, 38);
		frmTrackr.getContentPane().add(txtI);
		txtI.setColumns(10);
		}
		else{
		txtStrongly_1 = new JTextField();
		txtStrongly_1.setEditable(false);
		txtStrongly_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtStrongly_1.setText(conditions[conditions.length-1]);
		txtStrongly_1.setBounds(0, 0, 200, 38);
		frmTrackr.getContentPane().add(txtStrongly_1);
		txtStrongly_1.setColumns(10);
		
		txtAgree = new JTextField();
		txtAgree.setEditable(false);
		txtAgree.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAgree.setText(conditions[conditions.length-2]);
		txtAgree.setBounds(0, 38, 200, 38);
		frmTrackr.getContentPane().add(txtAgree);
		txtAgree.setColumns(10);
		
		txtMaybe = new JTextField();
		txtMaybe.setEditable(false);
		txtMaybe.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtMaybe.setText(conditions[conditions.length-3]);
		txtMaybe.setBounds(0, 76, 200, 38);
		frmTrackr.getContentPane().add(txtMaybe);
		txtMaybe.setColumns(10);
		}
	    
	    
	    
	    //button to allow the user to proceed to the next question
	    
		JButton btnNewButton = new JButton("Next Question");
		btnNewButton.addActionListener(new ActionListener() {//when button begin is clicked
			public void actionPerformed(ActionEvent arg0) {
				questionResponses[count-1]=slider.getValue()-3;
				
				frmTrackr.getContentPane().removeAll();///this is how to remove stuff
				frmTrackr.getContentPane().repaint();
				
				questionNumber();
			}
		});
		btnNewButton.setToolTipText("Next");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(467, 350, 191, 63);
		frmTrackr.getContentPane().add(btnNewButton);
		
		txtStrongly_1 = new JTextField();
		txtStrongly_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtStrongly_1.setEditable(false);
		txtStrongly_1.setText(question);
	
		Border redline = BorderFactory.createLineBorder(Color.red,3);
		txtStrongly_1.setBorder(redline);
		txtStrongly_1.setBackground(new Color(255, 255, 0));
		txtStrongly_1.setBounds(330, 150, 285, 37);
		frmTrackr.getContentPane().add(txtStrongly_1);
		
	
		
		JLabel lblHello = new JLabel("");
		Image musicPNG = new ImageIcon(this.getClass().getResource("/music4.png")).getImage();
		lblHello.setBounds(0, 0, 682, 453);
		
		lblHello.setIcon(new ImageIcon(musicPNG));
		frmTrackr.getContentPane().add(lblHello);
		
	} 
	/**
	 * method to proceed to the calculation of the next song for the user
	 */
	private void end(){
		frmTrackr.dispose();
		computeSimilarSongs.test(this.DATA);
		
	}
	
}