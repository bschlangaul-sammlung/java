package org.bschlangaul.examen.examen_66115.jahr_2021.fruehjahr;

class DoubleLinkedList {
  private Item head;

  public DoubleLinkedList() {
    head = null;
  }

  public Item append(Object val) {
    if (head == null) {
      head = new Item(val, null, null);
      head.prev = head;
      head.next = head;
    } else {
      Item item = new Item(val, head.prev, head);
      head.prev.next = item;
      head.prev = item;
    }
    return head.prev;
  }

  public Item search(Object val) {
    Item item = null;
    if (head != null) {
      item = head;
      do {
        if (item.val == val) {
          return item;
        }
        item = item.next;
      } while (!item.equals(head));
    }
    return null;
  }

  public void delete(Object val) {
    Item item = search(val);
    if (item != null) {
      if (head.next.equals(head)) {
        head = null;
      } else {
        if (item.equals(head)) {
          head = item.next;
        }
        item.prev.next = item.next;
        item.next.prev = item.prev;
      }
    }
  }

  class Item {
    private Object val;
    private Item prev;
    private Item next;

    public Item(Object val, Item prev, Item next) {
      this.val = val;
      this.prev = prev;
      this.next = next;
    }
  }

  /**
   * Hilfsmethode zum Debuggen.
   *
   * @param val Der Wert nach dem gesucht werden soll.
   */
  private void searchAndPrint(Object val) {
    Item item = search(val);
    if (item != null)
      System.out.println(item.val);
    else
      System.out.println(item);
  }

  /**
   * Hilfsmethode zum Debuggen.
   */
  private void printAll() {
    System.out.print("All items: ");
    if (head != null) {
      System.out.print(head.val + " ");
      Item item = head;

      while (!item.next.equals(head)) {
        System.out.print(item.next.val + " ");
        item = item.next;
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    DoubleLinkedList list = new DoubleLinkedList();
    list.append("a");
    list.append("b");
    list.append("c");
    list.printAll();

    System.out.println("Test search");

    list.searchAndPrint("a");
    list.searchAndPrint("b");
    list.searchAndPrint("c");
    list.searchAndPrint("x");

    list.delete("a");
    list.printAll();
    list.delete("b");
    list.printAll();
    list.delete("c");
    list.printAll();
    list.delete("x");
  }
}
