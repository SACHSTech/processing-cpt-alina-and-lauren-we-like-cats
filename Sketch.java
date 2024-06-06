import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  PImage background;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(1400, 700);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
    background = loadImage("White Background.jpg");
    background.resize(background.width*width/2560, background.height*height/1440);
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
	// sample code, delete this stuff
    image(background, 0, 0);

  }
  
  // define other methods down here.
}