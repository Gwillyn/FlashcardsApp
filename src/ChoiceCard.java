class ChoiceCard extends Flashcard {
  int rightAnswer;
  String[] answers;

  ChoiceCard(String question, String[] answers, int rightAnswer) {
    super(question, answers[rightAnswer]);
    this.rightAnswer = rightAnswer;
    this.question = question;
    this.answers = answers;

  }

  public int getRightAnswer() {
    return rightAnswer;
  }

  public String[] getAnswers() {
    return answers;
  }
}
