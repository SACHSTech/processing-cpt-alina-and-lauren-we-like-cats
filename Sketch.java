import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  PImage background;
  PImage character;

  int intPosX = 700;
  int intPosY = 350;
	

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
    background = loadImage("White Background.jpg");
    background.resize(background.width*width/2560, background.height*height/1440);

    character = loadImage("CatMC.png");
    character.resize(character.width*2, character.height*2);   
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    image(background, 0, 0);

    movement();

    image(character, intPosX, intPosY);

  }
  
  // define other methods down here.

  public void movement() {

    if (keyPressed) {
      if (keyCode == UP) {
        intPosY -= 5;
      }
      else if (keyCode == DOWN) {
        intPosY += 5;
      }
      else if (keyCode == RIGHT) {
        intPosX += 5;
      }
      else if (keyCode == LEFT) {
        intPosX -= 5;
      }
    }

  }
}