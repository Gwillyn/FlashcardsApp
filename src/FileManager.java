import java.io.*;
import java.io.File;

class FileManager {

  static File f = new File("../saves/");
  static File[] files = f.listFiles();

  public static File[] getFiles() {
    return files;
  }

  public static File getFile() {
    return f;
  }

  public static void loadFile(String file) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      reader.close();
    } catch (IOException e) {
      System.out.println("Failed");
    }

  }

  public static void listFile() {
    if (files != null) {
      int i = 1;
      for (File file : files) {
        System.out.print(i + ": ");
        System.out.print(file.getName());
        System.out.println();
        i++;
      }
    } else {
      System.out.println("There are no save files...");
      UI.returnToMenu();
    }
  }

}
