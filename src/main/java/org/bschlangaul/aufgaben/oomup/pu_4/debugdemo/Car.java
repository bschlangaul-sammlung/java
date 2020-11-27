package org.bschlangaul.aufgaben.oomup.pu_4.debugdemo;

/**
 * Class Car - nonsense class for debugger demo
 *
 * @author Michael Kölling
 * @version 13 August 1999
 */
public class Car {
  private int numberOfFrontSeats;
  private int numberOfBackSeats;

  /**
   * Create a car with seat numbers
   *
   * @param frontSeats Die Anzahl der Vordersitze.
   * @param backSeats Die Anzahl der Hintersitze.
   */
  public Car(int frontSeats, int backSeats) {
    numberOfFrontSeats = frontSeats;
    numberOfBackSeats = backSeats;
  }

  /**
   * Return the number of seats in the car.
   *
   * @return Die Anzahl der Sitze.
   */
  public int seats() {
    int totalSeats;

    totalSeats = numberOfFrontSeats;
    totalSeats += numberOfBackSeats;

    return totalSeats;
  }
}
