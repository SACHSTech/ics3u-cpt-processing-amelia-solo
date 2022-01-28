import processing.core.PApplet;

public class Sketch extends PApplet {

  String[] easyQuestions = {"What is the name of the reindeer with the red nose?", 
                    "Which colour combination does Santa wear?",
                    "What do children leave out for Santa on Christmas Eve?",
                    "Where does Santa live?",
                    "What does Santa give to children on the naughty list?",
                    "On which day of December is Christmas Celebrated?",
                    "How does Santa enter people's homes?",
                    "Who helps Santa make Toys",
                    "Finish the lyrics: jingle bells, jingle bells. ",
                    "What do people hang by the fireplace?"};
  String[][] easyChoices = { 
    {"Dasher", "Cupid", "Rudoplh", "Red-Nosey"}, 
    {"Red and White", "Red and Green", "Green and White", "White and Orange"}, 
    {"Gifts", "Cookies", "Cake", "Eggnog"},
    {"The North Pole", "The South Pole", "Canada", "Iceland"},
    {"Toys", "Cavities", "An ugly Christmas sweater", "Coal"},
    {"15", "24", "26", "25"},
    {"The front door", "The back door", "The chimney", "The Magic Portal"},
    {"The naughty list children", "Elves", "Fairies", "Reindeer"},
    {"jingle jingle", "jingle bell rock", "jingle all the way", "jingle jingle sleigh"},
    {"socks", "candy canes", "presents", "stockings"}
  };
  char[] easyAnswsers = {'c', 'a', 'b', 'a', 'd', 'd', 'c', 'b', 'c', 'd'};

  String[] medQ = {"q1", "q2"};
  String[][] medChoices = { {"a. q1 Answer 1", "b. q1 Answer 2"}, {"a. q2 Answer 1", "b. q2 Answer 2"} };
  char[] medAnswsers = {'a', 'b'};

  String[] selectedLevelQuestion ;
  String[][] selectedLevelChoices;
  char[] selectedLevelAnswers;
  int level = 0;
  int currentQuestion = 0;
  int correctAnswerCount;

  boolean showAnswerScreen = false;



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
    if(level != 0 ) {
      if( level == 1 ){
        selectedLevelQuestion = easyQuestions;
        selectedLevelAnswers = easyAnswsers;
        selectedLevelChoices = easyChoices;
      }else if( level == 2 ){
        selectedLevelQuestion = medQ;
        selectedLevelAnswers = medAnswsers;
        selectedLevelChoices = medChoices;
      }
      startQuiz();
      return;
    }

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
    if (mouseX >= 150 && mouseX <= 250 && mouseY >= 300 && mouseY <= 400) {
      level = 1;
    }
  }

  public void startQuiz(){
    background(132, 164, 240);
    
    text( selectedLevelQuestion[currentQuestion], 10, 100);
    text("(type 'a', 'b', 'c', or 'd')", 200, 150);
    text("a) " + selectedLevelChoices[currentQuestion][0], 200, 300);
    text("b) " + selectedLevelChoices[currentQuestion][1], 200, 350);
    text("c) " + selectedLevelChoices[currentQuestion][2], 200, 400);
    text("b) " + selectedLevelChoices[currentQuestion][3], 200, 450);
    
    if(keyPressed) {
      if(key >= 'a' && key <= 'd') {
        boolean isCorrect = key == selectedLevelAnswers[currentQuestion];
        if( isCorrect ){
          correctAnswerCount += 1;
          background(134, 209, 143);
          text("Correct!", 260, 300);
          text("Click --> to move to the next question", 100, 400);
        }else {
          background(232, 58, 58);
          text("Incorrect!", 260, 300);
          text("Click --> to move to the next question", 100, 400);
        }
      }
     
      if(keyCode == RIGHT){
        background(132, 164, 240);
        if( currentQuestion < selectedLevelQuestion.length - 1 ){
          currentQuestion += 1;
        }
      }
    }
  }
}
