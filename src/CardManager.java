import java.util.List;
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

  public void playCards() {
    int score = 0;
    UI.clear();
    for (Flashcard card : cards) {
      System.out.println("Question: \n" + card.getQuestion());
      UI.sleep(1);

    }

  }

}
