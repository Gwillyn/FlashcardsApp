import java.util.List;
import java.util.ArrayList;

class CardManager {

  String name;
  int score;
  List<Flashcard> cards;

  CardManager(List<Flashcard> cards, String name, int score) {
    this.cards = new ArrayList<>();
    this.name = name;
    this.score = score;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void addCard(Flashcard card) {
    cards.add(card);

  }

  public void deleteCard(Flashcard card) {
    cards.add(card);

  }

  public void playCards() {
    UI.clear();
    for (Flashcard card : cards) {
      System.out.println("Question: \n" + card.getQuestion());

    }

  }

}
