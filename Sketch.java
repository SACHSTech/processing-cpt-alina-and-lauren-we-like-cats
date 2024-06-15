import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  // Shop variables
  int intBoxWidth = 160;
  int intBoxHeight = 160;
	
  // background and character PImages
  PImage background;
  PImage character;
  PImage woodworker;

  // movement variables. Checking for keys pressed and direction faced
  boolean leftPressed = false;
  boolean rightPressed = false;
  int intDirection = 1;

  int intBGX;

  // starting position
  int intPosX = 100;
  int intPosY = 200;
  int intBaseY = 200;
  
  // velocity for floating
  double dblVelY = 1;

  // arrays for the animations and backgrounds
  PImage[] arrBackground = new PImage[3];
  PImage[] arrMC = new PImage[2];
  PImage[] arrWorker = new PImage[3];
  int intFrame = 0;
  int intWdWork = 0;

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

    arrWorker[0] = loadImage("WoodworkerCat.png");
    arrWorker[1] = loadImage("WoodworkerCatGlow.png");
    arrWorker[2] = loadImage("WoodworkerCatClick.png");

    for (int i = 0; i < arrWorker.length; i++) {
      arrWorker[i].resize(200, 200);
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

    woodworker();

    drawCharacter();
  }

  public void woodworker() {

    if (intLevel == 2) {
      
      woodworker = arrWorker[intWdWork];

      image(woodworker, 2000 + intBGX, 400);

      if (isHovering(2000 + intBGX, 400, woodworker.width, woodworker.height)) {
        intWdWork = 1;
        if (mousePressed) {
          intWdWork = 2;
        }
      }
      else {
        intWdWork = 0;
      }
    }

  }

  /**
   * Check if the mouse is hovering over something
   * @param x The x-coordinates of the item envelope the x-coordinate of the mouse
   * @param y The y-coordinates of the item envelope the y-coordinates of the mouse
   * @param width The width added onto the x-coordinate for the other x-side
   * @param height The height added onto the y-coordinate for the other y-side
   * @return true or false of if the mouse is within the boundaries
   */

  public boolean isHovering(int x, int y, int width, int height) {
    return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
  }

  public void drawBackground() {
    background = arrBackground[intLevel];

    if (intLevel == 0 || intLevel == 1) {
      intBaseY = 200;
      image(background, 0, 0);
    }
    else if (intLevel == 2) {
      intBaseY = 350;
      intBGX = -100 - intPosX;
      image(background, intBGX, 0);
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
        if (intLevel == 2) {
          intPosY = 350;
        }
        else {
          intPosY = 200;
        }
      }
      else if (intPosX >= -100) {
        intPosX -= 5;
      }
    }
    else if (rightPressed) {
      if (intLevel != (arrBackground.length - 1) && intPosX >= 1200) {
        intLevel ++;
        intPosX = 100;
        if (intLevel == 2) {
          intPosY = 350;
        }
        else {
          intPosY = 200;
        }
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