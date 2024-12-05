import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class background{
	private Image background; //, backward, left, right; 	
	private AffineTransform tx;
	
	//attribute of this class
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;				//collision detection (hit box)
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image
	
	public background() {
		background 	= getImage("/imgs/"+"background.png"); //load the image for background

		//alter these		
		//placement on JFrame
		x = 0;
		y = 0;
				
		//better movement
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//2nd constructor - allow setting x and y during construction
	public background(int x, int y) {
		
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
		
		init(x,y);
		
		g2.drawImage(background, tx, null);

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
			URL imageURL = background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
