package org.bschlangaul.baum;

/* Generic Min/Max Binary Heap
* for /r/javaexamples
*
*/
import java.util.Arrays;

@SuppressWarnings("unchecked")

/**
 * Creates an array-based binary heap. Defaults to 'min' (Priority Queue)
 *
 * @author /u/Philboyd_Studge
 */
public class BinaryHeap<T extends Comparable<T>> {
  private static final int DEFAULT_CAPACITY = 10;
  private T[] halde;
  private int füllstand;
  private boolean min;

  /**
   * Default Constructor
   * <p>
   * default capacity of 9 (0 index is not used)
   * <p>
   * default type of heap is min
   */
  public BinaryHeap() {
    halde = (T[]) new Comparable[DEFAULT_CAPACITY];
    füllstand = 0;
    min = true;
  }

  /**
   * Constructor - takes an array of type T and a boolean for min/max
   *
   * @param array T[] array
   * @param min   boolean true = min heap, false = max heap
   */
  public BinaryHeap(T[] array, boolean min) {
    halde = (T[]) new Comparable[DEFAULT_CAPACITY];
    füllstand = 0;
    this.min = min;

    // add each element to the heap
    for (T each : array) {
      add(each);
    }
  }

  /**
   * Constructor - specify boolean true = min heap, false = max heap
   *
   * @param min boolean true = min heap, false = max heap
   */
  public BinaryHeap(boolean min) {
    halde = (T[]) new Comparable[DEFAULT_CAPACITY];
    füllstand = 0;
    this.min = min;

  }

  /**
   * get the heap array note: can cause casting issues
   *
   * @return Array of type T[]
   */
  public T[] gibHaldenFeld() {
    return Arrays.copyOfRange(halde, 1, füllstand + 1);
  }

  /**
   * adds a generic type T to heap
   * <p>
   * percolates down the tree
   *
   * @param value type T value
   */
  public void add(T value) {
    // resize if needed
    if (this.füllstand >= halde.length - 1) {
      halde = this.resize();
    }

    füllstand++;
    halde[füllstand] = value;
    bubbleUp();
  }

  /**
   * Removes min or max item from heap
   * <p>
   * re-heapifies
   *
   * @return value of T that is minimum or maximum value in heap
   */
  public T remove() {
    T result = peek();

    swap(1, füllstand);
    halde[füllstand] = null;
    füllstand--;

    bubbleDown();

    return result;
  }

  /**
   * Remove specific object from heap
   *
   * @param value type T
   * @return true if found and removed
   */
  public boolean remove(T value) {
    for (int i = 0; i < halde.length; i++) {
      if (value.equals(halde[i])) {
        System.out.println(i);
        swap(i, füllstand);
        halde[füllstand] = null;
        füllstand--;
        // bubbleUp();
        bubbleDown();
        return true;
      }
    }
    return false;
  }

  /**
   * Removes min or max item from heap same as <code>remove()</code> but does not
   * throw exception on empty
   * <p>
   * re-heapifies
   *
   * @return value of T that is minimum or maximum value in heap; or
   *         <code>null</code> if empty
   */
  public T poll() {
    if (isEmpty())
      return null;

    T result = peek();

    swap(1, füllstand);
    halde[füllstand] = null;
    füllstand--;

    bubbleDown();

    return result;
  }

  /**
   * Checks if heap is empty
   *
   * @return <code>true</code> if empty
   */
  public boolean isEmpty() {
    return füllstand == 0;
  }

  /**
   * returns min/max value without removing it
   *
   * @return value type T
   * @throws IllegalStateException if empty
   */
  public T peek() {
    if (isEmpty())
      throw new IllegalStateException();
    return halde[1];
  }

  /**
   * Length/size of heap
   *
   * @return int size of heap
   */
  public int length() {
    return füllstand;
  }

  /**
   * Add DEFAULT_CAPACITY to length of <code>heap</code> array
   *
   * @return new array of type T
   */
  private T[] resize() {
    // add 10 to array capacity
    return Arrays.copyOf(halde, halde.length + DEFAULT_CAPACITY);
  }

  /**
   * percolates new values up based on min/max
   */
  private void bubbleUp() {
    int index = füllstand;
    if (min) {
      while (hatEltern(index) && (parent(index).compareTo(halde[index]) > 0)) {
        swap(index, gibIndexEltern(index));
        index = gibIndexEltern(index);
      }
    } else {
      while (hatEltern(index) && (parent(index).compareTo(halde[index]) < 0)) {
        swap(index, gibIndexEltern(index));
        index = gibIndexEltern(index);
      }

    }
  }

  /**
   * percolates values down based on min/max
   */
  private void bubbleDown() {
    int index = 1;
    if (min) {
      while (hatLinks(index)) {
        // find smaller of child values
        int smaller = gibIndexLinks(index);
        if (hatRechts(index) && halde[gibIndexLinks(index)].compareTo(halde[gibIndexRechts(index)]) > 0) {
          smaller = gibIndexRechts(index);
        }
        if (halde[index].compareTo(halde[smaller]) > 0) {
          swap(index, smaller);
        } else
          break;
        index = smaller;
      }
    } else {
      while (hatLinks(index)) {
        // find larger of child values
        int larger = gibIndexLinks(index);
        if (hatRechts(index) && halde[gibIndexLinks(index)].compareTo(halde[gibIndexRechts(index)]) < 0) {
          larger = gibIndexRechts(index);
        }
        if (halde[index].compareTo(halde[larger]) < 0) {
          swap(index, larger);
        } else
          break;
        index = larger;
      }
    }
  }

  /**
   * if child has a parent
   *
   * @param i integer - index
   * @return true if index > 1
   */
  private boolean hatEltern(int i) {
    return i > 1;
  }

  /**
   * Get left index mathematically
   *
   * @param i index
   * @return index of left node from index i
   */
  private int gibIndexLinks(int i) {
    return i * 2;
  }

  /**
   * Get right index mathematically
   *
   * @param i index
   * @return index of right node from index i
   */
  private int gibIndexRechts(int i) {
    return i * 2 + 1;
  }

  /**
   * Test to see if node has left child
   *
   * @param i index
   * @return true if it does
   */
  private boolean hatLinks(int i) {
    return gibIndexLinks(i) <= füllstand;
  }

  /**
   * Test to see if node has right child
   *
   * @param i index
   * @return true if it does
   */
  private boolean hatRechts(int i) {
    return gibIndexRechts(i) <= füllstand;
  }

  /**
   * get index of parent from child node
   *
   * @param i index
   * @return index of parent
   */
  private int gibIndexEltern(int i) {
    return i / 2;
  }

  /**
   * get parent value
   *
   * @param i index
   * @return value of type T
   */
  private T parent(int i) {
    return halde[gibIndexEltern(i)];
  }

  /**
   * Swap two values in heap
   *
   * @param index1 int first index
   * @param index2 int second index
   */
  private void swap(int index1, int index2) {
    T temp = halde[index1];
    halde[index1] = halde[index2];
    halde[index2] = temp;
  }

  /**
   * Overridden toString method
   *
   * @return String all values in heap without null values
   */
  @Override
  public String toString() {
    String retval = "";
    for (T each : halde) {
      if (each != null)
        retval += each + " : ";
    }
    return retval + "\n";

  }

}
