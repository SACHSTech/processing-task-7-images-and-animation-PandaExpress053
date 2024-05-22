import processing.core.PApplet;
import processing.core.PImage;
public class Sketch extends PApplet {
	/**
   * Description: Creates three objects: diagonal moving rectange, circular moving bee, parabolic moving grasshopper
   * Uses imported images for background and objects
   * @author: E.Fung
   */
  // Initialize global variables
	PImage imgBackground;
  PImage imgBee;
  PImage imgGrassHopper;

  float fltRectX;
  float fltRectY;

  float fltRectSpeedX = 2;
  float fltRectSpeedY = 1;

  float fltBeeX;
  float fltBeeY;

  float fltBeeXPrev = 100;
  float fltBeeYPrev = 100;

  float fltBeeSpeed = 3;

  double dblAng = 0;

  float fltHopY, fltHopX;
  float fltHopYPrev = 0;
  float fltHopSpeed = 2;




  public void settings() {
    
    // set size of screen
    size(610, 510);

    // intialize and resize images
    imgBackground = loadImage("background.png");
    imgBee = loadImage("cartoon-bee.png");
    imgBee.resize(imgBee.width / 6, imgBee.height / 6);
    imgGrassHopper = loadImage("grasshopper.png");
    imgGrassHopper.resize(imgGrassHopper.width / 8,imgGrassHopper.height / 8);

    // stating initial starting position
    fltRectX = width / 2;
    fltRectY = height / 2;

    fltBeeX = width / 2;
    fltBeeY = height / 2; 

    fltHopX = width / 2;
    fltHopY = height / 2; 

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
    
    // bee circular movement
    image(imgBee, fltBeeX, fltBeeY);
    fltBeeY += fltBeeSpeed * (float) (Math.sin((dblAng) * (Math.PI / 180)));
    fltBeeX += fltBeeSpeed * (float) (Math.cos((dblAng) * (Math.PI / 180)));
    dblAng += 1;

    // sees if bee will contact y-position constraints, if so Y_Pos will not change
    if (fltBeeY < 0  || fltBeeY > height - 100) {
      fltBeeY = fltBeeYPrev;
    }
    // sees if bee will contact x-position constraints, if so X_Pos will not change
    if (fltBeeX < -40 || fltBeeX > width - 110) {
      fltBeeX = fltBeeXPrev;
    }

    // resets previous position variables
    fltBeeXPrev = fltBeeX;
    fltBeeYPrev = fltBeeY;

    // grasshopper sinozoidal movement
    image(imgGrassHopper, fltHopX, fltHopY);
    fltHopY += (float) 3 * (Math.sin((fltHopX) * (Math.PI / 180)));
  
    
    // hard stop so that grasshopper stays on screen
    if (fltHopY < 0  || fltHopY > height - 100) {
      fltHopY = fltHopYPrev;
    }
    // sees if grasshopper will contact x-position constraints, if so grasshopper will jump in the other direction
    if (fltHopX < -40 || fltHopX > width - 110) {
      fltHopSpeed *= -1;
    }

    // moving grasshopper x_pos
    fltHopX += fltHopSpeed;

    // resetting previous position variable
    fltHopYPrev = fltHopY;
  }
}