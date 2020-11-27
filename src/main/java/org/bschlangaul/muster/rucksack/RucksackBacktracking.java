package org.bschlangaul.muster.rucksack;

// https://books.google.de/books?id=DAorddWEgl0C&pg=PA217&lpg=PA217&dq=knapsack+backtracking+java&source=bl&ots=o4RI313pMP&sig=ACfU3U2cUALy0yNC3iVW7y7HjiKDQLyC2g&hl=de&sa=X&ved=2ahUKEwiCvJjP6pLrAhWFGuwKHbtwAR84ChDoATAFegQIChAB#v=onepage&q=knapsack%20backtracking%20java&f=false
// Foundations of Algorithms Using Java Seite 225
public class RucksackBacktracking {

  static int maxprofit;
  static int W;
  static int[] p;
  static int[] w;
  static String[] include;
  static String[] bestset;
  static int numbest;
  static int n;

  public static void knapsack(int i, int profit, int weight) {
    // This set is best so far Set numbest to number of items
    // considered, Set bestset to this solution.
    if (weight <= W && profit > maxprofit) {
      maxprofit = profit;
      numbest = i;
      bestset = include;
    }

    if (promising(i, profit, weight)) {
      include[i + 1] = "yes";
      knapsack(i + 1, profit + p[i + 1], weight + w[i + 1]);
      include[i + 1] = "no";
      knapsack(i + 1, profit, weight);
    }

  }

  public static boolean promising(int i, int weight, int profit) {
    int j, k;
    int totweight;
    float bound;

    // Node is promising only if we should expand to its children. There
    // must be some capacity left for the children.

    if (weight >= W)
      return false;
    else {
      j = i + 1;
      bound = profit;
      totweight = weight;
      while (j <= n && totweight + w[j] <= W) {
        totweight = totweight + w[j];
        bound = bound + p[j];
        j++;
      }

      k = j;
      if (k <= n) {
        bound = bound + (W - totweight) * p[k] / w[k];
      }
      return bound > maxprofit;
    }
  }

  public static void main(String[] args) {
    p = new int[] { 60, 100, 120 };
    w = new int[] { 10, 20, 30 };
    W = 50;
    n = 3;
    knapsack(0, 0, 0);
    System.out.println(maxprofit);
    for (int i = 0; i < numbest; i++) {
      System.out.println(bestset[i]);
    }
  }

}
