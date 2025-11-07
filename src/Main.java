import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Flashcard> testCards = new ArrayList<>();
    testCards.add(new Flashcard("What is 2 + 2?", "4"));
    testCards.add(new Flashcard("Capital of France?", "Paris"));
    testCards.add(new Flashcard("Color of the sky?", "Blue"));
    CardManager cards = new CardManager(testCards, "Test", 0);

    UI.menu(scanner, cards);
  }
}
