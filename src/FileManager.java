import java.io.*;
import java.util.Scanner;

class FileManager {
  public static void loadFile(String file) throws IOException {
    FileReader fileReader = new FileReader(file);
    BufferedReader reader = new BufferedReader(fileReader);
    String line;
    while ((line = reader.readLine()) != null) {
      String[] sections = line.split("::", 2);
      if (sections.length == 2) {
        String question = parts[0];
        String answer = parts[1];

      }
    }

  }
}
