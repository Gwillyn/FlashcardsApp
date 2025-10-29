import java.util.List;
import java.util.ArrayList;

class CardManager {

  String name;
  List<Flashcard> cards;

  CardManager(List<Flashcard> cards, String name) {
    this.cards = new ArrayList<>();
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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
