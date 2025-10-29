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

  public static void menu(Scanner scanner) {
    boolean running = true;
    while (running) {
      clear();
      System.out.println("Welcome to the Flashcards app\n   Choose one of the following: \n");
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
