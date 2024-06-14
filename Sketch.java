import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  // background and character PImages
  PImage background;
  PImage character;
  PImage woodworker;

  // movement variables. Checking for keys pressed and direction faced
  boolean leftPressed = false;
  boolean rightPressed = false;
  int intDirection = 1;


  // starting position
  int intPosX = 100;
  int intPosY = 200;
  int intBaseY = 200;
  
  // velocity for floating
  double dblVelY = 1;

  // arrays for the animations and backgrounds
  PImage[] arrBackground = new PImage[3];
  PImage[] arrMC = new PImage[2];
  PImage[] arrWoodworker = new PImage[6];
  int intWorkerFrame = 0;
  int intFrame = 0;

  // intBGX is the x-coordinate for the background in longer backgrounds
  int intBGX;

  // level to indicate background
  int intLevel = 2;

  // Character size must change
  int[][] charDimensions = {
    {807/2, 428},
    {807/2, 428},
    {807/4, 214}
  };
	

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

    arrBackground[0] = loadImage("Bedroom.png");
    arrBackground[1] = loadImage("Kitchen.jpg");
    arrBackground[2] = loadImage("Town.png");

    arrMC[0] = loadImage("CatMCLeft.png");
    arrMC[1] = loadImage("CatMCRight.png");

    arrWoodworker[0] = loadImage("WoodworkerCat1.png");
    arrWoodworker[1] = loadImage("WoodworkerCat2.png");
    arrWoodworker[2] = loadImage("WoodworkerCat3.png");
    arrWoodworker[3] = loadImage("WoodworkerCat4.png");
    arrWoodworker[4] = loadImage("WoodworkerCat5.png");
    arrWoodworker[5] = loadImage("WoodworkerCat6.png"); 

    for (int i = 0; i < arrWoodworker.length; i++) {
      arrWoodworker[i].resize(200, 200);
    }

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  
    for (int i = 0; i < arrMC.length; i++){
      arrMC[i].resize(charDimensions[intLevel][0], charDimensions[intLevel][1]);   
    }

    drawBackground();

    movement();

    drawCharacter();

  }

  public void woodworkerAnimation() {

    woodworker = arrWoodworker[intWorkerFrame];

    if (intLevel == 2) {
      image(woodworker, 1300, 400);
    }

    // random number to randomize when woodworker blinks
    float fltX = random(0, 100);
    
    if (fltX < 0.5) {
      // Closing eyes
      for (int i = 0; i < arrWoodworker.length; i++) {
        intWorkerFrame = i;
      }
      // Opening eyes
      for (int i = 5; i >= 0; i--) {
        intWorkerFrame = i;
      }
    }

  }

  public void drawBackground() {
    background = arrBackground[intLevel];

    if (intLevel == 0 || intLevel == 1) {
      image(background, 0, 0);
    }
    else if (intLevel == 2) {
      intBaseY = 400;
      int intBGX = 100 + intPosX;
      image(background, -intBGX, 0);
    }
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

    if (intPosY >= intBaseY + 20 || intPosY <= intBaseY - 20) {
      dblVelY = -dblVelY;
    }

    image(character, intPosX, intPosY);

  }
  
  // define other methods down here.

  public void movement() {

    if (leftPressed) {
      if (intLevel != 0 && intPosX <= -100) {
        intLevel --;
        intPosX = 1200;
      }
      else if (intPosX >= -100) {
        intPosX -= 5;
      }
    }
    else if (rightPressed) {
      if (intLevel != (arrBackground.length - 1) && intPosX >= 1200) {
        intLevel ++;
        intPosX = 100;
      }
      else if (intPosX <= 1200) {
        intPosX += 5;
      }
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