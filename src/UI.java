import java.util.InputMismatchException;
import java.util.Scanner;

class UI {

  public static void returnToMenu() {
    System.out.println();
    System.out.print("Returning to menu");
    System.out.print(".");
    smallSleep(500);
    System.out.print(".");
    smallSleep(500);
    System.out.print(".");
    smallSleep(500);

  }

  public static void sleep(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void smallSleep(int milliseconds) {
    try {
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void clear() {
    System.out.println("\033[H\033[2J");
    System.out.flush();
  }

  public static void menu(Scanner scanner, CardManager file) {
    boolean running = true;
    while (running) {
      clear();
      System.out.print("Welcome to the Flashcards app   ");
      if (file.getScore() > 0) {
        System.out.print("       Score: " + file.getScore() + "\n");

      }
      System.out.println("Choose one of the following:");
      System.out.println("1: Play\n2: Load Cards\n3: Add Cards\n4: Delete Cards\n5: Quit\n");

      int response = 0;
      try {
        response = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("\nPlease enter the proper selection\nPress [enter] to continue:\n");
        scanner.nextLine();
        continue;
      }
      switch (response) {
        case 1:

          break;
        case 2:
          break;
        case 3:
          addCardChoice(scanner, file);
          break;
        case 4:
          deleteCard(scanner, file);
          break;
        case 5:
          running = false;
          break;
        default:
          break;

      }

    }
  }

  static private void deleteCard(Scanner scanner, CardManager file) {
    boolean active = true;
    boolean repeat = true;
    while (active) {
      clear();
      System.out.println("Please select a card to delete:\nType [99] to exit.");
      for (int i = 0; i < file.cards.size(); i++) {
        System.out.println((i + 1) + ": " + file.cards.get(i).getQuestion());
      }
      while (true) {
        int choice = 0;
        try {
          choice = (scanner.nextInt() - 1);
          if (choice == 98) {
            active = false;
            repeat = false;
            scanner.nextLine();
            returnToMenu();
            break;
          } else if (choice >= 0 && choice < file.cards.size()) {
            scanner.nextLine();
            file.deleteCard(file.cards.get(choice));
            break;
          } else {
            System.out.println("Please enter a valid input...");
          }
        } catch (InputMismatchException e) {
          System.out.println("Please enter a valid input...");
          scanner.nextLine();
        }
      }
      if (repeat) {

        System.out.println("Would you like to delete another card? [1: Yes, 2: No]");
        while (true) {
          int choice = 0;
          try {
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
              break;
            } else if (choice == 2) {
              active = false;
              break;
            }
          } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input...");
            scanner.nextLine();
          }
        }
      }

    }
  }

  static private void addCardChoice(Scanner scanner, CardManager file) {
    String errorMessage = "Please enter a valid input...";
    boolean active = true;
    clear();
    while (active) {
      int choice = 0;
      System.out.println(
          "Would you like to make a general `Question/Answer` card or a `Multiple Choice` card?\n1: Question/Answer\n2: Multiple Choice\n");
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        scanner.nextLine();
      }
      clear();
      if (choice == 1) {
        System.out.println("Please enter a question: ");
        String question = scanner.nextLine();
        System.out.println("Please enter an answer: ");
        String answer = scanner.nextLine();
        file.addCard(new Flashcard(question, answer));
        System.out.println("Card added...");
      } else if (choice == 2) {
        String[] answers;
        System.out.println("Please enter a question: ");
        String question = scanner.nextLine();
        int choices = 0;
        while (true) {
          System.out.println("Please enter the number of multiple choices you'd like [between 2 and 5]: ");
          try {
            choices = scanner.nextInt();
            scanner.nextLine();
          } catch (InputMismatchException e) {
            scanner.nextLine();
          }
          if (choices >= 2 && choices <= 5) {
            answers = new String[choices];
            break;
          } else {
            clear();
            System.out.println(errorMessage);
          }
        }

        for (int i = 0; i < answers.length; i++) {
          System.out.println("Enter your answer " + (i + 1) + ": ");
          String answer = scanner.nextLine();
          answers[i] = answer;
        }
        while (true) {
          System.out.println("Which answer is the correct one: ");
          for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ": " + answers[i]);
          }
          int rightAnswer = 0;
          try {
            rightAnswer = scanner.nextInt() - 1;
            scanner.nextLine();
          } catch (InputMismatchException e) {
            clear();
            System.out.println(errorMessage);
            scanner.nextLine();
            continue;
          }
          if (rightAnswer >= 0 && rightAnswer < answers.length) {
            file.addCard(new ChoiceCard(question, answers, rightAnswer));
            clear();
            System.out.println("Card added...");
            break;

          } else {
            clear();
            System.out.println(errorMessage);
          }
        }
      } else {
        clear();
        continue;
      }
      int repeat = 0;
      while (true) {
        System.out.println("Would you like to add another card? [1: yes, 2: No]");
        try {
          repeat = scanner.nextInt();
          scanner.nextLine();
        } catch (InputMismatchException e) {
          scanner.nextLine();
        }
        if (repeat == 2) {
          clear();
          active = false;
          returnToMenu();
          break;
        } else if (repeat == 1) {
          clear();
          break;
        } else {
          clear();
          System.out.println(errorMessage);
        }
      }
    }
  }
}
