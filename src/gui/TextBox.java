package gui;

import processing.core.PApplet;

public class TextBox extends PApplet{
	   /**
	 * 0.1
	 */
	private static final long serialVersionUID = 1L;
	private int X = 0, Y = 0, H = 35, W = 200;
	   public int TEXTSIZE = 24;
	   
	   // COLORS
	   public int Background = color(140, 140, 140);
	   public int Foreground = color(0, 0, 0);
	   public int BackgroundSelected = color(160, 160, 160);
	   public int Border = color(30, 30, 30);
	   
	   public boolean BorderEnable = false;
	   public int BorderWeight = 1;
	   
	   public String Text = "";
	   public int TextLength = 0;

	   private boolean selected = false;
	   
	   public TextBox() {
	      // CREATE OBJECT DEFAULT TEXTBOX
	   }
	   
	   public TextBox(int x, int y, int w, int h) {
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
	      applet.fill(Foreground);
	      applet.textSize(TEXTSIZE);
	      applet.textAlign(BASELINE);
	      applet.text(Text, X + (applet.textWidth("a") / 2), Y + TEXTSIZE);
	   }
	   
	   // IF THE KEYCODE IS ENTER RETURN 1
	   // ELSE RETURN 0
	   public boolean keyPressed(char KEY, int KEYCODE,PApplet applet) {
	      if (selected) {
	         if (KEYCODE == (int)BACKSPACE) {
	            backspace();
	         } else if (KEYCODE == 32) {
	            // SPACE
	            addText(' ',applet);
	         } /*else if (KEYCODE == (int)ENTER) {
	            }
	          */
	          else {
	            // CHECK IF THE KEY IS A LETTER OR A NUMBER
	            boolean isKeyCapitalLetter = (KEY >= 'A' && KEY <= 'Z');
	            boolean isKeySmallLetter = (KEY >= 'a' && KEY <= 'z');
	            boolean isKeyNumber = (KEY >= '0' && KEY <= '9');
	      
	            if (isKeyCapitalLetter || isKeySmallLetter || isKeyNumber) {
	               addText(KEY,applet);
	            }
	         }
	         return true;
	      }
	      
	      return false;
	   }
	   
	   private void addText(char text,PApplet applet) {
	      // IF THE TEXT WIDHT IS IN BOUNDARIES OF THE TEXTBOX
	      if (applet.textWidth(Text + text) < W) {
	         Text += text;
	         TextLength++;
	      }
	   }
	   
	   private void backspace() {
	      if (TextLength - 1 >= 0) {
	         Text = Text.substring(0, TextLength - 1);
	         TextLength--;
	      }
	   }
	   
	   // FUNCTION FOR TESTING IS THE POINT
	   // OVER THE TEXTBOX
	   private boolean overBox(int x, int y) {
	      if (x >= X && x <= X + W) {
	         if (y >= Y && y <= Y + H) {
	            return true;
	         }
	      }
	      
	      return false;
	   }
	   
	   public void pressed(int x, int y) {
	      if (overBox(x, y)) {
	         selected = true;
	      } else {
	         selected = false;
	      }
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

	public String getText() {
		return Text;
	}
	public void setText(String s) {
		Text=s;
		TextLength=s.length();
	}
}