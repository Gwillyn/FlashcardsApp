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
}
