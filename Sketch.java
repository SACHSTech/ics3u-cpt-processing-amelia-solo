import processing.core.PApplet;

public class Sketch extends PApplet {

  String[] selectedLevelQuestion;
  String[][] selectedLevelChoices;
  char[] selectedLevelAnswers;

  // Questions, choices, and correct answers for the easy level
  String[] easyQuestions = { "1. What is the name of the reindeer with the red nose?",
      "2. Which colour combination does Santa wear?",
      "3. What do children leave out for Santa on Christmas Eve?",
      "4. Where does Santa live?",
      "5. What does Santa give to children on the naughty list?",
      "6. On which day of December is Christmas Celebrated?",
      "7. How does Santa enter people's homes?",
      "8. Who helps Santa make Toys",
      "9. Finish the lyrics: jingle bells, jingle bells... ",
      "10. What do people hang by the fireplace?" };
  String[][] easyChoices = {
      { "Dasher", "Cupid", "Rudoplh", "Red-Nosey" },
      { "Red and White", "Red and Green", "Green and White", "White and Orange" },
      { "Gifts", "Cookies", "Cake", "Eggnog" },
      { "The North Pole", "The South Pole", "Canada", "Iceland" },
      { "Toys", "Cavities", "An ugly Christmas sweater", "Coal" },
      { "15", "24", "26", "25" },
      { "The front door", "The back door", "The chimney", "The Magic Portal" },
      { "The naughty list children", "Elves", "Fairies", "Reindeer" },
      { "jingle jingle", "jingle bell rock", "jingle all the way", "jingle jingle sleigh" },
      { "socks", "candy canes", "presents", "stockings" }
  };
  char[] easyAnswsers = { 'c', 'a', 'b', 'a', 'd', 'd', 'c', 'b', 'c', 'd' };

  // Questions, choices, and correct answers for the hard level
  String[] hardQuestions = { "1. In 'Home Alone', where was Kevin's family going for vacation?",
      "2. What popular Christmas song was written for Thanksgiving?",
      "3. In the song 'Twelve Days of Christmas,' what is given on the 7th day?",
      "4. Which bird is often on the front of Christmas cards?",
      "5. In the 1950s, what colour of Christmas trees became popular?",
      "6. Which country did Eggnog originate in?",
      "7. What is the best-selling Christmas song of all time?",
      "8. What was Frosty the Snowmans nose made out of?",
      "9. When were gingerbread houses invented?",
      "10. Where does the advent calendar tradition come from?" };
  String[][] hardChoices = {
      { "Paris", "Venice", "London", "New York" },
      { "Deck the Halls", "Jingle Bells", "Silent Night", "Little Drummer Boy" },
      { "Swans", "Golden Rings", "Geese", "Drummers" },
      { "Eagles", "Swans", "Doves", "Robins" },
      { "Red", "Green", "White", "Pink" },
      { "America", "Germany", "England", "Russia" },
      { "Last Christmas", "White Christmas", "Mistletoe", "All I want for Christmas" },
      { "Carrot", "Pebble", "Button", "Snow" },
      { "15th century", "16th century", "18th century", "19th century" },
      { "Germany", "France", "America", "England" }
  };
  char[] hardAnswers = { 'a', 'b', 'a', 'd', 'd', 'c', 'b', 'c', 'b', 'a' };

  int level = 0;
  int currentQuestion = 0;
  int correctAnswerCount = 0;
  boolean correctAnswer = false;
  boolean answerEntered = false;

  public void settings() {
    size(600, 600);
  }

  // Array for the falling snow
  float[] circleY = new float[40];

  public void setup() {
    background(132, 164, 240);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
  }

  public void draw() {
    if (level == 0) {
      background(132, 164, 240);

      // Array for falling snow
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

      fill(0);
      textSize(20);
      text("Welcome to Christmas Queries!", 160, 100);
      text("Choose a level to get started:", 170, 150);

      fill(255);
      ellipse(300, 500, 800, 300);

      fill(132, 164, 240);
      textSize(15);
      text("It's Christmas Eve and the Grinch is holding Santa captive!", 100, 450);
      text("It's now up to you to save Christmas...", 160, 475);
      text("Convince the Grinch to free Santa by proving your Christmas spirit:", 75, 500);
      text("Correctly answer at least 7/10 Christmas queries.", 125, 525);

      // Buttons for selecting level
      stroke(197, 209, 237);
      fill(255);
      ellipse(200, 350, 100, 100);
      ellipse(400, 350, 100, 100);
      fill(0);
      textSize(20);
      text("Easy", 175, 350);
      text("Hard", 375, 350);

      // Draws snowman
      stroke(197, 209, 237);
      fill(255);
      ellipse(300, 350, 100, 100);
      ellipse(300, 270, 100, 100);

      stroke(0);
      fill(0);
      ellipse(315, 260, 5, 5);
      ellipse(285, 260, 5, 5);
      text("⌣", 300, 280);

      // Goes to whichever level the user selected
    } else {
      if (level == 1) {
        selectedLevelQuestion = easyQuestions;
        selectedLevelAnswers = easyAnswsers;
        selectedLevelChoices = easyChoices;
      } else if (level == 2) {
        selectedLevelQuestion = hardQuestions;
        selectedLevelAnswers = hardAnswers;
        selectedLevelChoices = hardChoices;
      }
      startQuiz();
      return;
    }
  }

  // Method that allows the user to click on the easy or hard level
  public void mouseClicked() {
    if (mouseX >= 150 && mouseX <= 250 && mouseY >= 300 && mouseY <= 400) {
      level = 1;
    } else if (mouseX >= 350 && mouseX <= 450 && mouseY >= 300 && mouseY <= 400) {
      level = 2;
    }
  }

  /*
   * This method displays each question, the choices, the correct answer, and
   * whether the user won or lost
   * 
   * @return void
   **/
  public void startQuiz() {
    // Displays question and choices
    if (answerEntered == false) {
      background(132, 164, 240);
      text(selectedLevelQuestion[currentQuestion], 10, 100);
      text("(type 'a', 'b', 'c', or 'd')", 200, 250);
      text("a) " + selectedLevelChoices[currentQuestion][0], 50, 400);
      text("b) " + selectedLevelChoices[currentQuestion][1], 350, 400);
      text("c) " + selectedLevelChoices[currentQuestion][2], 50, 450);
      text("d) " + selectedLevelChoices[currentQuestion][3], 350, 450);
    }

    // Determines if the answer is correct
    if (keyPressed) {
      if (answerEntered == false && key >= 'a' && key <= 'd') {
        answerEntered = true;
        correctAnswer = key == selectedLevelAnswers[currentQuestion];
        if (correctAnswer) {
          correctAnswerCount += 1;
        }
      }
      // Tells user if the answer is correct
      if (answerEntered && currentQuestion < selectedLevelQuestion.length) {
        if (correctAnswer) {
          background(134, 209, 143);
          text("Correct!", 260, 300);
          text("Click the right arrow to move to the next question", 100, 400);
        } else {
          background(232, 58, 58);
          text("Incorrect!", 260, 300);
          text("Click the right arrow to move to the next question", 100, 400);
        }
      }

      // Moves to the next question when user clicks the right arrow
      if (keyCode == RIGHT) {
        if (currentQuestion < selectedLevelQuestion.length - 1 && answerEntered) {
          currentQuestion += 1;
          answerEntered = false;

          // Tells the user if they won or lost at the end of the questions
        } else if (currentQuestion == selectedLevelQuestion.length - 1) {
          if (correctAnswerCount >= 7) {
            background(132, 164, 240);
            text("Congratulations, the Grinch was so impressed by your", 40, 225);
            text("Christmas spirit that he freed Santa!", 75, 250);
            text("You've saved Christmas!", 150, 300);
          } else {
            background(132, 164, 240);
            text("You lost!", 250, 250);
            text("The Grinch successfully stole Christmas.", 125, 300);
          }
        }
      }
    }
  }
}
