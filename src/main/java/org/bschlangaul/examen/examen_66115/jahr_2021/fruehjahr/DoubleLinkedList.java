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

    Item aktuell = search(val);
    if (aktuell != null) {
      if (head.next.equals(head)) {
        head = null;
      } else {
        if (aktuell.equals(head)) {
          head = aktuell.next;
        }
        aktuell.prev.next = aktuell.next;
        aktuell.next.prev = aktuell.prev;
      }
    }

    Item item = null;
    if (head != null) {
      item = head;
      while (!item.next.equals(head)) {
        if (item.val == val) {
          return item;
        }
        item = item.next;
      }
    }
    return item;
  }

  public void delete(Object val) {
    Item item = search(val);
    if (item != null) {
      if (head.next.val == head.val) {
        head = null;
      } else {
        if (item.val == head.val) {
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

  public void searchAndPrint(Object val) {
    Item item = search(val);
    System.out.println(item.val);
  }

  public void printAll() {
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

    list.delete("a");
    list.printAll();
  }
}
