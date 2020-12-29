package org.bschlangaul.examen.examen_66116_2019_09.file_system;

public class Link extends Element {

  private Element linkedElement;

  public Link(String name, Element parent, Element linkedElement) {
    super(name, parent);
    this.linkedElement = linkedElement;
    parent.addChild(this);
  }

  public void delete() {
    System.out.println("The Symbolic Link “" + name + "” was deleted.");
    linkedElement.delete();
    System.out.println("The linked element “" + name + "” was deleted too.");
  }

  public void addChild(Element child) {
    if (linkedElement.isDirectory())
      linkedElement.addChild(child);
  }

  public boolean isDirectory() {
    return linkedElement.isDirectory();
  }
}
