package org.bschlangaul.examen.examen_66115.jahr_2021.fruehjahr;

@SuppressWarnings({ "unused" })
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
    return new Item(1, null, null);
  }

  public void delete(Object val) {
    //
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
}
