package org.bschlangaul.examen.examen_66115.jahr_2014.herbst;

/**
 * https://www.studon.fau.de/file2860857_download.html
 */
public class Stapel {
  public Element top;

  public Stapel() {
    top = null;
  }

  /**
   * @param element Das Element, dass hinzugefügt werden soll zur Stapel.
   */
  public void push(Element element) {
    element.setNext(top);
    top = element;
  }

  /**
   * @return Das Element oder null, wenn der Stapel leer ist.
   */
  public Element pop() {
    if (top == null) {
      return null;
    }
    Element element = top;
    top = top.getNext();
    return element;
  }

  /**
   * @return Wahr wenn der Stapel leer ist.
   */
  public boolean isEmpty() {
    return top == null;
  }

  /**
   * @param s Stapel s
   * @param t Stapel t
   *
   * @return Ein neuer Stapel.
   */
  public static Stapel merge(Stapel s, Stapel t) {
    // Die beiden Stapel unsortiert aneinander hängen.
    Stapel mergedStack = new Stapel();
    while (!s.isEmpty()) {
      mergedStack.push(s.pop());
    }
    while (!t.isEmpty()) {
      mergedStack.push(t.pop());
    }
    // https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
    Stapel tmpStack = new Stapel();
    while (!mergedStack.isEmpty()) {
      Element tmpElement = mergedStack.pop();
      while (!tmpStack.isEmpty() && tmpStack.top.getValue() > tmpElement.getValue()) {
        mergedStack.push(tmpStack.pop());
      }
      tmpStack.push(tmpElement);
    }
    return tmpStack;
  }

  public static void main(String[] args) {
    Stapel sa = new Stapel();
    sa.push(new Element(1));
    sa.push(new Element(2));
    sa.push(new Element(4));
    sa.push(new Element(5));
    sa.push(new Element(7));
    sa.push(new Element(8));
    Stapel sb = new Stapel();
    sb.push(new Element(2));
    sb.push(new Element(3));
    sb.push(new Element(6));
    sb.push(new Element(9));
    sb.push(new Element(10));

    Stapel sc = Stapel.merge(sa, sb);

    while (!sc.isEmpty()) {
      System.out.print(sc.pop().getValue() + ", ");
    }
  }
}
