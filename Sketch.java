import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
	
  // background and character PImages
  PImage shop;
  PImage wand;
  PImage background;
  PImage character;
  PImage woodworker;

  // movement variables. Checking for keys pressed and direction faced
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean upPressed = false;
  int intDirection = 1;

  int intBGX;

  // starting position
  int intPosX = 100;
  int intPosY = 200;
  int intBaseY = 200;
  
  // velocity for floating
  double dblVelY = 1;

  // Shop variables
  int intGold = 100;
  int intCost = 0;

  boolean blnShopOn = false;
  int intShopStatus = 0;
  int intBoxSide = 76;

  PImage[] arrShop = new PImage[17];

  int[][] itemCoordinates = {
    {570, 196}, // The first stick
    {722, 196}, // second stick
    {570, 334}, // third stick
    {722, 334}, // fourth stick
    {921, 58}, // X button
    {580, 572}, // Yes button
    {781, 572}, // No button
    {504, 456} // Description screen
  };

  // Wands
  PImage[] arrWand = new PImage[5];
  int intWand = 0;

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

    arrWand[0] = loadImage("Wand0.png");
    arrWand[1] = loadImage("Wand1.png");
    arrWand[2] = loadImage("Wand2.png");
    arrWand[3] = loadImage("Wand3.png");
    arrWand[4] = loadImage("Wand4.png");

    for (int i = 0; i < arrWand.length; i++) {
      arrWand[i].resize(70, 70);
    }

    arrShop[0] = loadImage("ShopScreenFull.png");
    arrShop[1] = loadImage("ShopScreenHover1.png");
    arrShop[2] = loadImage("ShopScreenHover2.png");
    arrShop[3] = loadImage("ShopScreenHover3.png");
    arrShop[4] = loadImage("ShopScreenHover4.png");
    arrShop[5] = loadImage("ShopScreenClick1.png");
    arrShop[6] = loadImage("ShopScreenClick2.png");
    arrShop[7] = loadImage("ShopScreenClick3.png");
    arrShop[8] = loadImage("ShopScreenClick4.png");
    arrShop[9] = loadImage("ShopScreenDeny1.png");
    arrShop[10] = loadImage("ShopScreenDeny2.png");
    arrShop[11] = loadImage("ShopScreenDeny3.png");
    arrShop[12] = loadImage("ShopScreenDeny4.png");
    arrShop[13] = loadImage("ShopScreenBought1.png");
    arrShop[14] = loadImage("ShopScreenBought2.png");
    arrShop[15] = loadImage("ShopScreenBought3.png");
    arrShop[16] = loadImage("ShopScreenBought4.png");

    for (int i = 0; i< arrShop.length; i++) {
      arrShop[i].resize(600, 600);
    }

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

    drawShop();
  }

  /**
   * Draw the woodworker, placement depends on the x-coordinate of the background.
   */
  public void woodworker() {

    if (intLevel == 2) {
      
      woodworker = arrWorker[intWdWork];

      image(woodworker, 2000 + intBGX, 400);

      if (isHovering(2000 + intBGX, 400, woodworker.width, woodworker.height)) {
        intWdWork = 1;
        if (mousePressed) {
          intWdWork = 2;
          blnShopOn = true;
        }
      }
      else {
        intWdWork = 0;
      }
    }

  }

  /**
   * Drawing the shop.
   */
  public void drawShop() {
    if (blnShopOn) {
      // if nothing has been clicked
      if (intShopStatus < 5) {
        if (isHovering(itemCoordinates[0][0], itemCoordinates[0][1], intBoxSide, intBoxSide)) {
          intShopStatus = 1;
          if (mousePressed) {
            intShopStatus = 5;
            intCost = 100;
          }
        }
        else if (isHovering(itemCoordinates[1][0], itemCoordinates[1][1], intBoxSide, intBoxSide)) {
          intShopStatus = 2;
          if (mousePressed) {
            intShopStatus = 6;
            intCost = 200;
          }
        }
        else if (isHovering(itemCoordinates[2][0], itemCoordinates[2][1], intBoxSide, intBoxSide)) {
          intShopStatus = 3;
          if (mousePressed) {
            intShopStatus = 7;
            intCost = 500;
          }
        }
        else if (isHovering(itemCoordinates[3][0], itemCoordinates[3][1], intBoxSide, intBoxSide)) {
          intShopStatus = 4;
          if (mousePressed) {
            intShopStatus = 8;
            intCost = 1000;
          }
        }
        else if (isHovering(itemCoordinates[4][0], itemCoordinates[4][1], 67, 67)) {
          if (mousePressed) {
            blnShopOn = false;
          }
        }
      }
      if (intShopStatus >= 5) {
        // press yes
        if (isHovering(itemCoordinates[5][0], itemCoordinates[5][1], 41, 19)) {
          // if have enough money
          if (mousePressed && intGold >= intCost) {
            intGold -= intCost;
            intWand = intShopStatus - 5;
            intShopStatus += 8;
            wait(100);
          }
          // not enough money
          else if (mousePressed && intGold < intCost) {
            intShopStatus += 4;
            wait(100);
          }
        }
        // press no
        else if (isHovering(itemCoordinates[6][0], itemCoordinates[6][1], 27, 19)) {
          if (mousePressed) {
            intShopStatus = 0;
          }
        }
        // exit either purchase or deny message
        if (keyPressed && key == 'e') {
          intShopStatus = 0;
        }
      }
      shop = arrShop[intShopStatus];
      image(shop, 400, 50);
    }
  }

  /**
   * pauses the code for a specified amount of time. Used when the code may detect continuous 'mouse pressed' when it's
   * only a click.
   * @param milliseconds the amount of milliseconds the code needs to pause for.
   */
  public void wait(int milliseconds) {
    try {
      Thread.sleep(milliseconds); // Sleep for 1000 milliseconds (1 second)
    } 
    catch (InterruptedException e) {
      e.printStackTrace();
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

  /**
   * Drawing the background depending on the level, making sure the moving backgrounds are properly drawn.
   */
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

  /**
   * draws the character with hover and direction faced
   */
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

  /**
   * Movement based on keyPressed and keyReleased
   */
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

  /**
   * to check if the specified key is pressed
   */
  public void keyPressed() {
    if (key == 'a') {
      leftPressed = true;
      intDirection = -1;
    }
    else if (key == 'd') {
      rightPressed = true;
      intDirection = 1;
    }
    else if (key == 'w' && intLevel == 3) {
      upPressed = true;
    }
  }
  
  /**
   * to check if the specified key is released
   */
  public void keyReleased() {
    if (key == 'a') {
      leftPressed = false;
    }
    else if (key == 'd') {
      rightPressed = false;
    }
    else if (key == 'w' && intLevel == 3) {
      upPressed = false;
    }
  }
}