import java.util.InputMismatchException;
import java.util.Scanner;

class UI {

  public static void returnToMenu(Scanner scanner) {
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
      System.out.println("1: Play\n2: Load Cards\n3: Add Cards\n4: Delete Cards\n5: quit\n");

      int response = 0;
      try {
        response = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("\nPlease enter the proper selection\nPress [enter] to continue:\n");
        scanner.nextLine();
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
          break;
        case 5:
          running = false;
          break;
        default:
          break;

      }

    }
  }

  static private void addCardChoice(Scanner scanner, CardManager file) {
    boolean active = true;
    while (active) {
      clear();
      int choice = 0;
      System.out.println(
          "Would you like to make a general `Question/Answer` card or a `Multiple Choice` card?\n1: Question/Answer\n2: Multiple Choice\n");
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception e) {
        System.out.println("Please enter a valid choice...");
        sleep(2);
        scanner.nextLine();
      }
      clear();
      if (choice == 1) {
        System.out.println("Please enter a question: ");
        String question = scanner.nextLine();
        System.out.println("Please enter an answer: ");
        String answer = scanner.nextLine();
        file.addCard(new Flashcard(question, answer));
        break;
      } else if (choice == 2) {
        String[] answers;
        boolean mActive = true;
        System.out.println("Please enter a question: ");
        String question = scanner.nextLine();
        int choices = 0;
        while (mActive) {
          System.out.println("Please enter the number of multiple choices you'd like [between 2 and 5]: ");
          try {
            choices = scanner.nextInt();
            scanner.nextLine();
            break;
          } catch (InputMismatchException e) {
            System.out.println("Enter a valid input...");
            sleep(2);
            scanner.nextLine();
          }
        }
        if (choices >= 2 && choices <= 5) {
          answers = new String[choices];
        } else {
          System.out.println("Please enter a valid input: ");
          continue;
        }
        for (int i = 0; i < answers.length; i++) {
          System.out.println("Enter your question " + (i + 1) + ": ");
          String answer = scanner.nextLine();
          answers[i] = answer;
        }
        while (mActive) {
          System.out.println("Which answer is the correct one: ");
          for (int i = 0; i < answers.length; i++) {
            System.out.println((i + 1) + ": " + answers[i]);
          }
          int rightAnswer = 0;
          try {
            rightAnswer = scanner.nextInt();

          } catch (InputMismatchException e) {
            System.out.println("Please enter a valid input");
          }
          if (rightAnswer > 0 && rightAnswer <= answers.length + 1) {
            rightAnswer--;
            System.out.println("Hurray!!");
            System.out.println(answers[rightAnswer]);
            sleep(2);
            active = false;
            break;
            // Add functionality to add the choice card. Also debug fucntion

          } else {
            System.out.println("Please try again with a valid input...'");
            continue;
          }
        }

      }

    }
  }

}
