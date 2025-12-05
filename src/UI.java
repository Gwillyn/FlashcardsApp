import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

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
      System.out.println("Welcome to the Flashcards app\n");
      if (file.getHighScore() > 0) {
        System.out.println("       High-Score: " + file.getHighScore() + "/" + file.cards.size());

      } else {
        System.out.println("       No High Score!");
      }
      System.out.println("\nChoose one of the following:");
      System.out.println("1: Play\n2: Load Cards\n3: Add Cards\n4: Delete Cards\n5: Reset Score\n6: Quit\n");

      int response = 0;
      try {
        response = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("\nPlease enter the proper selection\nPress [enter] to continue:\n");
        scanner.nextLine();
        continue;
      }

      // Main game loop UI.
      switch (response) {
        case 1:
          playCards(file, scanner);
          break;
        case 2:
          loadCards(scanner);
          break;
        case 3:
          addCardChoice(scanner, file);
          break;
        case 4:
          deleteCard(scanner, file);
          break;
        case 5:
          resetScore(file, scanner);
          break;
        case 6:
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
              returnToMenu();
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

  private static void addCardChoice(Scanner scanner, CardManager file) {
    String errorMessage = "Please enter a valid input...";
    boolean active = true;
    clear();
    while (active) {
      int choice = 0;
      System.out.println(
          "Would you like to make a general `Question/Answer` card or a `Multiple Choice` card?\n1: Question/Answer\n2: Multiple Choice\n3: Menu\n");
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        scanner.nextLine();
      }
      if (choice == 1) {
        System.out.println("\nPlease enter a question: ");
        String question = scanner.nextLine();
        System.out.println("\nPlease enter an answer: ");
        String answer = scanner.nextLine();
        file.addCard(new Flashcard(question, answer));
        System.out.println("\nCard added...");
      } else if (choice == 2) {
        String[] answers;
        System.out.println("\nPlease enter a question: ");
        String question = scanner.nextLine();
        int choices = 0;
        while (true) {
          System.out.println("\nPlease enter the number of multiple choices you'd like [between 2 and 5]: ");
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
            System.out.println(errorMessage);
          }
        }

        for (int i = 0; i < answers.length; i++) {
          System.out.println("\nEnter your answer " + (i + 1) + ": ");
          String answer = scanner.nextLine();
          answers[i] = answer;
        }
        while (true) {
          System.out.println("\nWhich answer is the correct one: ");
          for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ": " + answers[i]);
          }
          int rightAnswer = 0;
          try {
            rightAnswer = scanner.nextInt() - 1;
            scanner.nextLine();
          } catch (InputMismatchException e) {
            System.out.println(errorMessage);
            scanner.nextLine();
            continue;
          }
          if (rightAnswer >= 0 && rightAnswer < answers.length) {
            file.addCard(new ChoiceCard(question, answers, rightAnswer));
            System.out.println("\nCard added...");
            break;

          } else {
            System.out.println(errorMessage);
          }
        }
      } else if (choice == 3) {
        active = false;
        returnToMenu();
        break;
      } else {
        clear();
        continue;
      }
      int repeat = 0;
      while (true) {
        System.out.println("\nWould you like to add another card? [1: yes, 2: No]");
        try {
          repeat = scanner.nextInt();
          scanner.nextLine();
        } catch (InputMismatchException e) {
          scanner.nextLine();
        }
        if (repeat == 2) {
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

  private static void resetScore(CardManager file, Scanner scanner) {
    clear();
    System.out.println("Would you like to reset your high-score to 0? [1: Yes, 2: No]");
    while (true) {
      int choice = 0;
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (InputMismatchException e) {
        System.out.println("Please select a valid input...");
        scanner.nextLine();
        continue;
      }
      if (choice == 1) {
        clear();
        file.setScore(0);
        System.out.println("High-Score was reset!");
        returnToMenu();
        break;
      } else if (choice == 2) {
        returnToMenu();
        break;
      } else {
        System.out.println("Please select a valid input...");
      }
    }
  }

  public static void playCards(CardManager file, Scanner scanner) {
    int score = 0;
    for (Flashcard card : file.cards) {
      clear();
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
        String choice = scanner.nextLine().trim();
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
      if (score > file.highscore) {
        file.highscore = score;
      }

    }
  }

  public static void loadCards(Scanner scanner) {
    System.out.println("From the selection, please enter the the file you would like to load: \n");
    File[] files = FileManager.getFiles();

    FileManager.listFile();
    System.out.println();
    int choice;
    while (true) {
      try {
        System.out.println(files.length);
        choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > files.length) {
          System.out.println("Please select a valid input...");
          continue;
        }
        break;
      } catch (InputMismatchException e) {
        System.out.println("Please select a valid input...");
      }
    }
    FileManager.loadFile(files[choice - 1].toString());
    scanner.nextLine();

  }
}
