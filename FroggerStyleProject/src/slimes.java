import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class slimes{
	private Image anemo; //, backward, left, right; 
	private Image electro;
	private Image geo;
	private Image cryo;
	private AffineTransform tx;
	
	//attribute of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.5;		//change to scale image
	double scaleHeight = 1.5; 		//change to scale image
	
	public slimes() {
		anemo 	= getImage("/imgs/"+"anemoslime-pixilart.png"); //load the image for anemo
		electro 	= getImage("/imgs/"+"electroslime-pixilart.png"); //load the image for electro
		geo 		= getImage("/imgs/"+"geoslime-pixilart.png"); //load the image for geo
		cryo 		= getImage("/imgs/"+"cryoslime-pixilart.png"); //load the image for cryo

		//alter these
		//hit box
		width = 40;
		height = 40;
		
		//placement on JFrame
		x = 0;
		y = 0;
		
		//if your movement will not be hopping base
		vx = 1;
		vy = 0;
		
		//better movement
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//collision detection wit main character class
	public boolean collided(aether character) {
		
		//rep eac obj as a rectangle
		//thenn chedck if they are interscting
		Rectangle main = new Rectangle(
				character.getX(),
				character.getY()+3,
				character.getWidth(), character.getHeight()-6);
		
		Rectangle thisObject = new Rectangle(x, y, width, height);
		
		//user built-in method to check intersection (COLLICION)
		return main.intersects(thisObject);
	}
	
	//2nd constructor - allow setting x and y during construction
	public slimes(int x, int y) {
		
		//call the default constructor for all tbe noremal stuidd
		this(); //invokes default constructir
		
		//do the speific task for THBIS cunstruictior
		this.x = x;
		this.y = y; 
		
		
	}
	
	double pick = Math.random()*4;


	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x += vx;
		y += vy;	
		
		//for infinate scrolling, tp to the other side once it leaves the other side
				if(x>750) {
					int ran = (int) (Math.random()*50) + 75;
					x = -ran;
				}
		
		init(x,y);
		
		if(pick>3) {
			g2.drawImage(anemo, tx, null);
		}else if(pick>2) {
			g2.drawImage(electro, tx, null);
		}else if(pick>1) {
			g2.drawImage(geo, tx, null);
		}else {
			g2.drawImage(cryo, tx, null);
		}

		//draw hit box based on x, y, width, height
			//for collision detection
			if(Frame.debugging) {
				//draw hitbox only if debugging
				g.setColor(Color.green);
				g.drawRect(x+17, y+13, width, height);
			}
			
		}

	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = slimes.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
