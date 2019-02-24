import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GD extends JPanel{
	Graph g;
	ArrayList<Song> path;
	ArrayList<Point> locs =  new ArrayList<Point>();
	
	/**
	 * Initialize Graph
	 */
	public GD(){
		super();
		System.out.println("Graph");
		path = computeSimilarSongs.path;
		g = computeSimilarSongs.g;
		JFrame frame = new JFrame();
		for(Song i : computeSimilarSongs.path)
			locs.add(new Point((int)(Math.random()*700)+10, (int)(Math.random()*700))); //assign locations for nodes
		frame.setSize(800,800);
		frame.setFocusable(false);
		frame.setLocationRelativeTo(null);//centers
		frame.setVisible(true);
		frame.setDefaultCloseOperation(3);//ends runtime on close
		frame.setResizable(false);
		frame.getContentPane().add(this);
		
	}
	
	@Override
	public void paint(Graphics g){//O paint
			Graphics2D drawer = (Graphics2D) g;//attaches g to global drawer
			for(int i=0; i!= path.size(); i++){
				drawer.drawString(path.get(i).getTitle(), locs.get(i).x, locs.get(i).y); //draw song names
				drawer.fillOval(locs.get(i).x, locs.get(i).y, 20, 20); //draw song nodes
			}
			for(int i=1; i!=path.size(); i++){
				drawer.drawLine(locs.get(i-1).x+10, locs.get(i-1).y+10, locs.get(i).x+10, locs.get(i).y+10); //draw lines connecting adjacent nodes
			}
		}
}
