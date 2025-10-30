import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

class Main {

  public static void main(String[] args) {
    final String path = "data/data.txt";

    int qtyNumbers = 0;
    double totalValue = 0.0d;
    double largestValue = 0.0d;
    double smallestValue = Double.POSITIVE_INFINITY;

    // Inspired by https://stackoverflow.com/a/811860/22407308
    try (InputStream inputStream = new FileInputStream(path)) {
      Reader reader = new BufferedReader(new InputStreamReader(inputStream));
      try (Scanner scanner = new Scanner(reader)) {
        double value;
        while (scanner.hasNextDouble()) {
          value = scanner.nextDouble();

          qtyNumbers++;
          totalValue += value;
          if (value > largestValue) largestValue = value;
          if (value < smallestValue) smallestValue = value;
        }
      }
    } catch (IOException e) {
      System.err.format("I/O Error: %s\n", e.getMessage());
      System.exit(1);
    }

    if (qtyNumbers == 0) {
      System.err.println("Error: no numbers found in file.");
      System.exit(1);
    }

    double avgValue = totalValue / (double) qtyNumbers;
    double range = largestValue - smallestValue;

    System.out.format("NUMBER STATISTICS\n");
    System.out.format("File name:           %30s\n", path);
    System.out.format("Quantity of numbers: %30d\n", qtyNumbers);
    System.out.format("Average value:       %30.2f\n", avgValue);
    System.out.format("Largest value:       %30.2f\n", largestValue);
    System.out.format("Smallest value:      %30.2f\n", smallestValue);
    System.out.format("Range:               %30.2f\n", range);
  }
}
