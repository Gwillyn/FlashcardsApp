import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

class CardManager {

  String name;
  int highscore;
  List<Flashcard> cards;

  CardManager(List<Flashcard> cards, String name, int highscore) {
    this.cards = new ArrayList<>(cards);
    this.name = name;
    this.highscore = highscore;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getHighScore() {
    return highscore;
  }

  public void setScore(int highscore) {
    this.highscore = highscore;
  }

  public void addCard(Flashcard card) {
    cards.add(card);

  }

  public void deleteCard(Flashcard card) {
    cards.remove(card);

  }

  public void playCards(Scanner scanner) {
    int score = 0;
    for (Flashcard card : cards) {
      UI.clear();
      System.out.println("Question: \n\n" + card.getQuestion() + "\n");
      if (card instanceof ChoiceCard choiceCard) {
        String[] answers = choiceCard.getAnswers();
        int rightAnswer = choiceCard.getRightAnswer();
        for (int i = 0; i < answers.length; i++) {
          System.out.println((i + 1) + ": " + answers[i]);
        }
        System.out.println("\n\nPlease select the right answer: ");
        while (true) {
          int choice = 0;
          try {
            choice = (scanner.nextInt() - 1);
            scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println("\nPlease select a valid input...\n");
            scanner.nextLine();
          }
          if (choice < 0 || choice >= answers.length) {
            System.out.println("\nPlease select a valid input...\n");
          } else if (choice == rightAnswer) {
            score++;
            System.out.println("\nThat is correct!            Score: " + score);
            System.out.println("\nPress [enter] to continue\n");
            scanner.nextLine();
            break;
          } else {
            System.out.println("\nWrong answer!");
            System.out.println("\nPress [enter] to continue\n");
            scanner.nextLine();
            break;
          }
        }

      } else {
        System.out.println("\nEnter the one-word answer you think it is: \n");
        String choice = scanner.nextLine();
        if (choice.toLowerCase().equals(card.getAnswer().toLowerCase())) {
          score++;
          System.out.println("\nThat is correct!              Score " + score);
          System.out.println("\nPress [enter] to continue\n");
          scanner.nextLine();
        } else {
          System.out.println("\nWrong answer!");
          System.out.println("\nPress [enter] to continue\n");
          scanner.nextLine();
        }

      }
      if (score > highscore) {
        highscore = score;
      }

    }
  }

}
