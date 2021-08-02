package org.bschlangaul.examen.examen_66116.jahr_2020.fruehjahr;

public class ComputeSum {
  public static int[] computeSum(int[][] input) {
    int[] output = new int[input.length];
    for (int i = 0; i < input.length; i++) {
      int[] numbers = input[i];
      int sum = 0;
      for (int j = 0; j < numbers.length; j++) {
        sum += numbers[j];
      }
      output[i] = sum;
    }
    return output;
  }

  public static void main(String[] args) {
    int[][] input = new int[3][];
    input[0] = new int[] { 1, 2, 3 };
    input[1] = new int[] { 4, 5 };
    input[2] = new int[] { 6 };
    int[] output = computeSum(input);
    for (int i = 0; i < output.length; i++) {
      System.out.println(output[i]);
    }
  }
}
