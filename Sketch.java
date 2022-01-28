import processing.core.PApplet;

public class Sketch extends PApplet {

  //Questions, choices, and correct answers for the easy level
  String[] easyQuestions = {"What is the name of the reindeer with the red nose?", 
                            "Which colour combination does Santa wear?",
                            "What do children leave out for Santa on Christmas Eve?",
                            "Where does Santa live?",
                            "What does Santa give to children on the naughty list?",
                            "On which day of December is Christmas Celebrated?",
                            "How does Santa enter people's homes?",
                            "Who helps Santa make Toys",
                            "Finish the lyrics: jingle bells, jingle bells... ",
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

  //Questions, choices, and correct answers for the hard level
  String[] hardQuestions = {"In 'Home Alone', where was Kevin's family going for vacation?",
                            "What popular Christmas song was actually written for Thanksgiving?",
                            "In the song 'Twelve Days of Christmas,' what is given on the 7th day?",
                            "Which bird is often on the front of Christmas cards?",
                            "In the 1950s, what colour of Christmas trees became popular?",
                            "Which country did Eggnog originate in?",
                            "What is the best-selling Christmas song of all time?",
                            "What was Frosty the Snowmans nose made out of?",
                            "When were gingerbread houses invented?"};
  String[][] hardChoices = {
      {"Paris", "Venice", "London", "New York"},
      {"Deck the Halls", "Jingle Bells", "Silent Night", "Little Drummer Boy"},
      {"Swans", "Golden Rings", "Geese", "Drummers"},
      {"Eagles", "Swans", "Doves", "Robins"},
      {"Red", "Green", "White", "Pink"},
      {"America", "Germany", "England", "Russia"},
      {"Last Christmas", "White Christmas", "Mistletoe","All I want for Christmas"}
      {"Carrot", "Pebble", "Button", "Snow"},
      {"15th century", "16th century", "18th century", "19th century"}
      {""}
    };
  char[] hardAnswers = {'a', 'b', 'a', 'd', 'd', 'c', 'b', 'c', 'b'};


  String[] selectedLevelQuestion ;
  String[][] selectedLevelChoices;
  char[] selectedLevelAnswers;
  
  int level = 0;
  int currentQuestion = 0;
  int correctAnswerCount = 0;

  boolean showAnswerScreen = false;

  public void settings() {
    size(600, 600);
  }

  //Array for the falling snow
  float[] circleY = new float[40];

  public void setup() {
    background(132, 164, 240);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
  }

  public void draw() {
    //Draws welcome page
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
    text("Choose a level to get started:", 170, 150);

    fill(132, 164, 240);
    textSize(15);
    text("It's Christmas Eve and the Grinch is holding Santa captive!", 100, 450);
    text("It's now up to you to save Christmas...", 160, 475);
    text("Convince the Grinch to free Santa by proving your Christmas spirit:", 75, 500);
    text("Correctly answer at least 7/10 Christmas queries.", 125, 525);

    stroke(197, 209, 237);
    fill(255);
    ellipse(200, 350, 100, 100);
    ellipse(400, 350, 100, 100);
    ellipse(300, 350, 100, 100);
    ellipse(300, 270, 100, 100);
    stroke(0);
    fill(0);
    ellipse(315, 260, 5, 5);
    ellipse(285, 260, 5, 5);
    text("⌣", 300, 280);


    fill(0);
    textSize(20);
    text("Easy", 175, 350);
    text("Hard", 375, 350);

    //Goes to whatever level the user selected
    if(level != 0 ) {
      if( level == 1 ){
        selectedLevelQuestion = easyQuestions;
        selectedLevelAnswers = easyAnswsers;
        selectedLevelChoices = easyChoices;
      }else if( level == 2 ){
        selectedLevelQuestion = hardQuestions;
        selectedLevelAnswers = hardAnswers;
        selectedLevelChoices = hardChoices;
      }
      startQuiz();
      return;

    }
  }

  //Method that allows the user to click on the easy or hard level
  public void mouseClicked() {
    if(mouseX >= 150 && mouseX <= 250 && mouseY >= 300 && mouseY <= 400) {
      level = 1;
    }else if(mouseX >=350 && mouseX <=450 && mouseY >= 300 && mouseY <=400) {
      level = 2;
    }
  }

  //Method that displays each question
  public void startQuiz(){
    background(132, 164, 240);
    text( selectedLevelQuestion[currentQuestion], 10, 100);
    text("(type 'a', 'b', 'c', or 'd')", 200, 150);
    text("a) " + selectedLevelChoices[currentQuestion][0], 200, 300);
    text("b) " + selectedLevelChoices[currentQuestion][1], 200, 350);
    text("c) " + selectedLevelChoices[currentQuestion][2], 200, 400);
    text("b) " + selectedLevelChoices[currentQuestion][3], 200, 450);
    
    //Shows user if their answer is correct or incorrect
    if(keyPressed) {
      if(key >= 'a' && key <= 'd') {
        boolean isCorrect = key == selectedLevelAnswers[currentQuestion];
        if( isCorrect ){
          correctAnswerCount += 1;
          background(134, 209, 143);
          text("Correct!", 260, 300);
          correctAnswerCount += 1;
          text("Click the right arrow to move to the next question", 100, 400);
        }else {
          background(232, 58, 58);
          text("Incorrect!", 260, 300);
          text("Click the right arrow to move to the next question", 100, 400);
        }
      }
     
      //Moves to the next question when user clicks the right arrow
      if(keyCode == RIGHT){
        background(132, 164, 240);
        if(currentQuestion < selectedLevelQuestion.length - 1 ){
          currentQuestion += 1;
        }
      }
    }
  }
}
