import java.util.Scanner;

import java.util.ArrayList;

class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    UI.menu(scanner, new CardManager(new ArrayList<>(), "Test", 4));
  }
}
