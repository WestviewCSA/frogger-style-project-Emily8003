import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//for any debugging code we add
	public static boolean debugging = false;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("BackgroundMusic.wav", true);
	//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	aether aether = new aether(300, 675); 
	background dragonspine = new background();
	
	//a row of aether2 objects
	slimes[] row1 = new slimes[6];
	slimes[] row2 = new slimes[6];
	slimes[] row3 = new slimes[6];
	slimes[] row4 = new slimes[6];
	AlbedoFlower[] flower1 = new AlbedoFlower[6];
	AlbedoFlower2[] flower2 = new AlbedoFlower2[4];
	AlbedoFlower[] flower3 = new AlbedoFlower[6];

		//arraylist of lives
	ArrayList<LifeImage> lives = new ArrayList<LifeImage>();
	
	
	//frame width/height
	int width = 760;
	int height = 780;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		
		
		//paint background
		dragonspine.paint(g);
		
				
		for(AlbedoFlower obj : flower1) {
			
			obj.paint(g);

		}
		for(AlbedoFlower2 obj : flower2) {
			
			obj.paint(g);

		}
		for(AlbedoFlower obj : flower3) {
			
			obj.paint(g);

		}
		
		//paint the other objects on the screen
		aether.paint(g);
		
		//have the row1 ojects paint on the screen
		//for each object in row one
		for(slimes obj : row1) {
			
			obj.paint(g);
			if(obj.collided(aether)) {
				resetAether();
			}
			
		}
		for(slimes obj : row2) {
			
			obj.paint(g);
			if(obj.collided(aether)) {
				resetAether();
			}

		}
		for(slimes obj : row3) {
			
			obj.paint(g);
			if(obj.collided(aether)) {
				resetAether();
			}

		}
		for(slimes obj : row4) {
			
			obj.paint(g);
			if(obj.collided(aether)) {
				resetAether();
			}

		}
				
		for(LifeImage obj : lives) {
			//draw the LifeImage objects
			obj.paint(g);
		}
		
		//collision method
		//for each slime obj in row1 array
		for(slimes obj : row1) {
			//invoke the collided metof for your clas - 
			//pass te main character as your object
			if(obj.collided(aether)) {
				
			}
		}
		
		boolean riding = false;
		for(AlbedoFlower obj : flower1) {
			if(obj.collided(aether)) {
				aether.setVx(obj.getVx());
				riding = true;
				break;
			}
		}
		
		for(AlbedoFlower2 obj : flower2) {
			if(obj.collided(aether)) {
				aether.setVx(obj.getVx());
				riding = true;
				break;
			}
		}

		for(AlbedoFlower obj : flower3) {
			if(obj.collided(aether)) {
				aether.setVx(obj.getVx());
				riding = true;
				break;
			}
		}

		//aether stops moving if not on ridable obj
		//limit it in the Y

		if(aether.getY()<70) {
			riding = false;
			aether.setVx(0);
		}else if(!riding && aether.getY() < 260) {
			riding = false;
			resetAether();
			//if !riding any the duck is in the "Water" area
			//reset back to starting
		}else if(!riding && aether.getY() > 260) {
			riding = false;
			aether.setVx(0);
		}
		
		//you win box
		Rectangle winRect = new Rectangle(0, 0, 760, 50);
		//aether hitbox for win box
		Rectangle main = new Rectangle(
				aether.getX(),
				aether.getY()+3,
				aether.getWidth(), aether.getHeight()-6);

		//paint text
		if(main.intersects(winRect)) {
			g.setFont(new Font("Courier", Font.BOLD, 50));
			g.setColor(Color.black);
			g.drawString("YOU WIN!!", 250, 350);
			g.setFont(new Font("Courier", Font.BOLD, 20));
			g.drawString("Press ENTER to restart", 270, 420);
		}
		
		if(lives.size() <= 0) {
			g.setFont(new Font("Courier", Font.BOLD, 50));
			g.setColor(Color.black);
			g.drawString("GOOD TRY", 250, 350);
			g.setFont(new Font("Courier", Font.BOLD, 20));
			g.drawString("Press ENTER to restart", 270, 420);
		}
		
	}
	
	public void resetAether() {
		aether.setVx(0);
		aether.x = 300;
		aether.y = 675;
		if(lives.size()>0) {
			lives.remove(lives.size()-1);
		}
		
	}

	
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
	}
	
	public Frame() {
		JFrame f = new JFrame("gensiin iapct");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
//		backgroundMusic.play();

	/* 
	 * setup any 1D array here! - create the obkects that go in them
	 */
		//traverse the array
		for(int i =0; i < row1.length; i++) {
			
			row1[i] = new slimes(i*150, 320);
			
		}
		
		for(int i =0; i < row2.length; i++) {
			
			row2[i] = new slimes(i*150, 420);
			
		}

		for(int i =0; i < row3.length; i++) {
			
			row3[i] = new slimes(i*150, 520);
			
		}
		
		for(int i =0; i < row4.length; i++) {
			
			row4[i] = new slimes(i*150, 620);
			
		}


		//lives
		//START WIT SIX ATTEMPTS
		for(int i = 0; i < 6; i++) {
			
			this.lives.add(new LifeImage(i*20+10, 710));
			
		}
		
		
		//flower
		for(int i =0; i < flower1.length; i++) {
			
			flower1[i] = new AlbedoFlower(i*150, 210);
			
		}
		for(int i =0; i < flower2.length; i++) {
			
			flower2[i] = new AlbedoFlower2(i*200, 150);
			
		}
		for(int i =0; i < flower3.length; i++) {
			
			flower3[i] = new AlbedoFlower(i*150, 90);
			
		}

		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		
		SimpleAudioPlayer bloop = new SimpleAudioPlayer("bloop.wav", false);

		
		if(arg0.getKeyCode()==38) {
			//move aether up
			aether.move(0);
			bloop.play();

		}else if(arg0.getKeyCode()==40) {
			//down
			aether.move(1);
			bloop.play();

		}else if(arg0.getKeyCode()==37) {
			//left
			aether.move(2);
			bloop.play();

		}else if(arg0.getKeyCode()==39) {
			//right
			aether.move(3);
			bloop.play();

		}
		
		if(arg0.getKeyCode()==10 && aether.getY()<50) {
			resetAether();
		}
		
		if(arg0.getKeyCode()==10 && lives.size()<=0) {
			resetAether();
			for(int i = 0; i < 6; i++) {
				
				this.lives.add(new LifeImage(i*20+10, 710));
				
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
