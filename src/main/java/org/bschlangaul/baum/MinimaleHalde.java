package org.bschlangaul.baum;

import java.util.Arrays;

/**
 * Feld-Implementation einer minimalen Halde (nach
 * <a href="https://codegym.cc/groups/posts/min-heap-in-java">codegym.cc</a>)
 */
public class MinimaleHalde {
  private int[] halde;
  private int index;
  private int size;

  public MinimaleHalde(int size) {
    this.size = size;
    this.index = 0;
    halde = new int[size];
  }

  private int parent(int i) {
    return (i - 1) / 2;
  }

  private int leftChild(int i) {
    return (i * 2) + 1;
  }

  private int rightChild(int i) {
    return (i * 2) + 2;
  }

  private boolean isLeaf(int i) {
    if (rightChild(i) >= size || leftChild(i) >= size) {
      return true;
    }
    return false;
  }

  public void insert(int element) {
    if (index >= size) {
      return;
    }
    halde[index] = element;
    int current = index;

    while (halde[current] < halde[parent(current)]) {
      swap(current, parent(current));
      current = parent(current);
    }
    index++;
  }

  // removes and returns the minimum element from the heap
  public int remove() {
    // since its a min heap, so root = minimum
    int popped = halde[0];
    halde[0] = halde[--index];
    minHeapify(0);
    return popped;
  }

  // heapify the node at i
  private void minHeapify(int i) {
    // If the node is a non-leaf node and any of its child is smaller
    if (!isLeaf(i)) {
      if (halde[i] > halde[leftChild(i)] || halde[i] > halde[rightChild(i)]) {
        if (halde[leftChild(i)] < halde[rightChild(i)]) {
          swap(i, leftChild(i));
          minHeapify(leftChild(i));
        } else {
          swap(i, rightChild(i));
          minHeapify(rightChild(i));
        }
      }
    }
  }

  // builds the min-heap using the minHeapify
  public void minHeap() {
    for (int i = (index - 1 / 2); i >= 1; i--) {
      minHeapify(i);
    }
  }

  // Function to print the contents of the heap
  public void printHeap() {
    for (int i = 0; i < (index / 2); i++) {
      System.out.print("Parent : " + halde[i]);
      if (leftChild(i) < index)
        System.out.print(" Left : " + halde[leftChild(i)]);
      if (rightChild(i) < index)
        System.out.print(" Right :" + halde[rightChild(i)]);
      System.out.println();
    }
  }

  // swaps two nodes of the heap
  private void swap(int x, int y) {
    int tmp;
    tmp = halde[x];
    halde[x] = halde[y];
    halde[y] = tmp;
  }

  public static void main(String[] arg) {
    MinimaleHalde minHeap = new MinimaleHalde(7);
    minHeap.insert(3);
    minHeap.insert(13);
    minHeap.insert(7);
    minHeap.insert(16);
    minHeap.insert(21);
    minHeap.insert(12);
    minHeap.insert(9);
    minHeap.minHeap();

    System.out.println("The Min Heap is : " + Arrays.toString(minHeap.halde));
    minHeap.printHeap();
    System.out.println("\nThe Min Value is : " + minHeap.remove());
    System.out.println("\nThe Min Heap is :" + Arrays.toString(minHeap.halde));
    minHeap.printHeap();
  }
}
