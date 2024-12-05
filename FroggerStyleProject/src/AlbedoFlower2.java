import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class AlbedoFlower2{
	private Image flower; //, backward, left, right; 
	private AffineTransform tx;
	
	//attribute of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.5;		//change to scale image
	double scaleHeight = 1.5; 		//change to scale image
	
	public AlbedoFlower2() {
		flower 	= getImage("/imgs/"+"albedoflower.png"); //load the image for anemo

		//alter these
		//hit box
		width = 65;
		height = 65;
		
		//placement on JFrame
		x = 0;
		y = 0;
		
		//if your movement will not be hopping base
		vx = -1;
		vy = 0;
		
		//better movement
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	public int getVx() {
		return vx;
	}
	
	public boolean collided(aether character) {
		
		//rep eac obj as a rectangle
		//thenn chedck if they are interscting
		Rectangle main = new Rectangle(
				character.getX(),
				character.getY()+3,
				character.getWidth(), character.getHeight()-6);
		
		Rectangle thisObject = new Rectangle(x+10, y+10, width-20, height-20);
		
		//user built-in method to check intersection (COLLICION)
		return main.intersects(thisObject);
	}

	
	
	
	//2nd constructor - allow setting x and y during construction
	public AlbedoFlower2(int x, int y) {
		
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
				if(x<-75) {
					int ran = (int) (Math.random()*75) + 700;
					x = ran;
				}
		
		init(x,y);
		
		g2.drawImage(flower, tx, null);

			//draw hit box based on x, y, width, height
			//for collision detection
			if(Frame.debugging) {
				//draw hitbox only if debugging
				g.setColor(Color.green);
				g.drawRect(x+5, y+5, width, height);
			}
			
		}

	
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = AlbedoFlower2.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
