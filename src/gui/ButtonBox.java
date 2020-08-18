package gui;
import processing.core.PApplet;

public class ButtonBox extends PApplet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1855702762825110969L;
	private int X = 0, Y = 0, H = 35, W = 200;
	   public int TEXTSIZE = 18;
	   public String text = "Submit";
	   
	   // COLORS
	   public int Background = color(140, 140, 140);
	   public int Foreground = color(0, 0, 0);
	   public int BackgroundSelected = color(160, 160, 160);
	   public int Border = color(30, 30, 30);
	   
	   public boolean BorderEnable = false;
	   public int BorderWeight = 1;
	   
	   private boolean selected = false;
	   
	   public ButtonBox() {
		      // CREATE OBJECT DEFAULT TEXTBOX
		   }
		   
	   public ButtonBox(int x, int y, int w, int h) {
		      X = x; Y = y; W = w; H = h;
		   }
	   
	   public void draw(PApplet applet) {
		      // DRAWING THE BACKGROUND
		      if (selected) {
		         applet.fill(BackgroundSelected);
		      } else {
		    	  applet.fill(Background);
		      }
		      
		      if (BorderEnable) {
		    	  applet.strokeWeight(BorderWeight);
		    	  applet.stroke(Border);
		      } else {
		    	  applet.noStroke();
		      }
		      
		      applet.rect(X, Y, W, H);
		      
		      // DRAWING THE TEXT ITSELF
		      applet.fill(255);
		      applet.textSize(TEXTSIZE);
		      applet.textAlign(BASELINE);
		      applet.text(text, (2*X+W-applet.textWidth(text)) / 2, Y + TEXTSIZE+4);
		   }
	   
	   public boolean pressed(int x, int y) {
		      if (overBox(x, y)) {
		         selected = true;
		         return true;
		      } else {
		         selected = false;
		         return false;
		      }
		   }
	   
	   private boolean overBox(int x, int y) {
		      if (x >= X && x <= X + W) {
		         if (y >= Y && y <= Y + H) {
		            return true;
		         }
		      }
		      
		      return false;
		   }

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getH() {
		return H;
	}

	public void setH(int h) {
		H = h;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		W = w;
	}

	public int getTEXTSIZE() {
		return TEXTSIZE;
	}

	public void setTEXTSIZE(int tEXTSIZE) {
		TEXTSIZE = tEXTSIZE;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
