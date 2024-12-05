import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class aether2{
	private Image forward; //, backward, left, right; 	
	private AffineTransform tx;
	
	//attribute of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 2.0;		//change to scale image
	double scaleHeight = 2.0; 		//change to scale image
	
	public aether2() {
		forward 	= getImage("/imgs/"+"aether front.png"); //load the image for aether
/*		backward 	= getImage("/imgs/"+"aether back.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"aether left.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"aether right.png"); //load the image for Tree*/

		//alter these
		//hit box
		width = 55;
		height = 90;
		
		//placement on JFrame
		x = -width; //off screen for now
		y = 300;
		
		//if your movement will not be hopping base
		vx = 0;
		vy = 0;
		
		//better movement
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//2nd constructor - allow setting x and y during construction
	public aether2(int x, int y) {
		
		//call the default constructor for all tbe noremal stuidd
		this(); //invokes default constructir
		
		//do the speific task for THBIS cunstruictior
		this.x = x;
		this.y = y; 
		
		
	}
	
	

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x += vx;
		y += vy;	
		
		//for infinate scrolling, tp to the other side once it leaves the other side
		if(x>1500) {
			x = -150;
		}
		
		init(x,y);
		
		
		g2.drawImage(forward, tx, null);

	/*	switch(dir) {
		case 0:
			g2.drawImage(forward, tx, null);
			break;
		case 1:
			g2.drawImage(backward, tx, null);

			break;
		case 2:
			g2.drawImage(left, tx, null);

			break;
		case 3:
			g2.drawImage(right, tx, null);
			break;*/
			
			//draw hit box based on x, y, width, height
			//for collision detection
			if(Frame.debugging) {
				//draw hitbox only if debugging
				g.setColor(Color.green);
				g.drawRect(x, y, width, height);
			}
			
		}

	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = aether2.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
