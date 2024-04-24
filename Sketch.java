import processing.core.PApplet;
import processing.core.PImage;
public class Sketch extends PApplet {
	
  // Initialize global variables
	PImage imgBackground;
  PImage imgBee;

  float fltRectX;
  float fltRectY;

  float fltRectSpeedX = 2;
  float fltRectSpeedY = 1;

  float fltBeeX = 100;
  float fltBeeY = 100;

  float fltBeeSpeedX = 3;
  float fltBeeSpeedY;


  public void settings() {
    
    // set size of screen
    size(610, 510);

    // set size of images
    imgBackground = loadImage("background.png");
    imgBee = loadImage("cartoon-bee.png");
    imgBee.resize(imgBee.width / 6,imgBee.height / 6);

    // stating initial starting position
    fltRectX = width / 2;
    fltRectY = height / 2;

    fltBeeX = width / 2;
    fltBeeY = height / 2; 
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);

  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // setting background
	  image(imgBackground, 0, 0);

    // rectangle linear movement
    rect(fltRectX, fltRectY, 30, 15);
    fltRectX += fltRectSpeedX;
    fltRectY += fltRectSpeedY;

    // collision detection for rectangle
    if (fltRectX < 0 || fltRectX > width - 30) {
      fltRectSpeedX *= -1;
    }
  
    if (fltRectY < 0  || fltRectY > height - 15) {
      fltRectSpeedY *= -1;
    }
    
    // bee sinozoidal movement
    image(imgBee, fltBeeX, fltBeeY);
    fltBeeSpeedY = 5 * (float) (Math.sin((fltBeeX) * (float)(Math.PI / 180)));
    fltBeeX += fltBeeSpeedX;

    // keeps bee in Y-position threshold, so sinozoidal movement doesn't push bee beyond screen size
    if (fltBeeY + fltBeeSpeedY < 150  || fltBeeY + fltBeeSpeedY > height - 150) {}
    else{
      fltBeeY += fltBeeSpeedY;
    }
    // collision detection of screen width constraints
    if (fltBeeX < -40 || fltBeeX > width - 110) {
      fltBeeSpeedX *= -1;
      fltBeeSpeedY *= -1;
    }
    
  }
}