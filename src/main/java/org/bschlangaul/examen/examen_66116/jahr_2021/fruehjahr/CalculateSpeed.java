package org.bschlangaul.examen.examen_66116.jahr_2021.fruehjahr;

public class CalculateSpeed {
  private double kilometers;
  private double minutes;

  public CalculateSpeed(double k, double m) {
    this.kilometers = k;
    this.minutes = m;

  }

  // Display the speed
  void speed() {
    double speed;
    speed = kilometers / (minutes / 60);
    System.out.println("A car traveling " + kilometers + " kilometers in " + minutes + " minutes travels at " + speed
        + " kilometers per hour");
  }

  public static void main(String args[]) {
    CalculateSpeed car = new CalculateSpeed(110.0, 120.0);

    // Display car speed
    car.speed();

    // Display bicycle speed
    double speed;

    speed = 20.0 / (80.0 / 60);
    // So steht es in der Angabe
    // System.out.println("A bicycle traveling " + kilometers + " kilometers in " + minutes + " minutes travels at "
    // + speed + " kilometers per hour");
    // Ohne Fehler:
    System.out.println("A bicycle traveling " + car.kilometers + " kilometers in " + car.minutes + " minutes travels at "
        + speed + " kilometers per hour");
  }
}
