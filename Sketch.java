import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  PImage background;
  PImage character;

  boolean leftPressed = false;
  boolean rightPressed = false;
  int intDirection = 1;


  int intPosX = 200;
  int intPosY = 200;
  
  double dblVelY = 1;

  PImage[] arrMC = new PImage[2];
  int intFrame = 0;
	

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
    background = loadImage("OIG1..jpg");
    background.resize(background.width*width/1024, background.height*height/512);

    arrMC[0] = loadImage("CatMCLeft.png");
    arrMC[1] = loadImage("CatMCRight.png");

    for (int i = 0; i < arrMC.length; i++){
      arrMC[i].resize(arrMC[i].width/2, arrMC[i].height/2);   
    }

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    image(background, 0, 0);

    movement();

    drawCharacter();

  }

  public void drawCharacter() {

    if(intDirection == 1){
      intFrame = 1;
    }
    else if (intDirection == -1){
      intFrame = 0;
    }
    else {
      intFrame = 1;
    }

    character = arrMC[intFrame];

    intPosY += dblVelY;

    if (intPosY >= 220 || intPosY <= 180) {
      dblVelY = -dblVelY;
    }

    image(character, intPosX, intPosY);

  }
  
  // define other methods down here.

  public void movement() {

    if (leftPressed || rightPressed) {
      intPosX += 5 * intDirection;
    }

  }

  public void keyPressed() {
    if (key == 'a') {
      leftPressed = true;
      intDirection = -1;
    }
    else if (key == 'd') {
      rightPressed = true;
      intDirection = 1;
    }
  }
  
  public void keyReleased() {
    if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }
  }
}