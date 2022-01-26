import processing.core.PApplet;

public class Sketch extends PApplet {
	
  public void settings() {
    size(600, 600);

  }

  float[] circleY = new float[40];

  public void setup() {
    background(132, 164, 240);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
   }
  }

  public void draw() {
    
    background(132, 164, 240);
    stroke(255);
    fill(255);
    for (int i = 0; i < circleY.length; i++) {
      float circleX = width * i / circleY.length;
      ellipse(circleX, circleY[i], 15, 15);
  
      circleY[i]++;
  
      if (circleY[i] > height) {
        circleY[i] = 0;
      }
    }

    ellipse(300, 500, 800, 300);

    fill(0);
    textSize(20);
    text("Welcome to Christmas Queries!", 160, 100);

    textSize(20);
    text("Choose a level to get started:", 170, 150);

    stroke(197, 209, 237);
    fill(255);
    ellipse(200, 350, 100, 100);
    ellipse(300, 350, 100, 100);
    ellipse(400, 350, 100, 100);

    fill(0);
    textSize(20);
    text("Easy", 175, 350);
    text("Medium", 265, 350);
    text("Hard", 375, 350);

  }
  
public void mouseClicked() {
    if(mouseX >= 150 && mouseX <= 250 && mouseY >= 300 && mouseY <= 400) {
        background(132, 164, 240);
    }
  } 
}
