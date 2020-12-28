package org.bschlangaul.examen.examen_66116_2019_09.file_system;

import java.util.List;

public class Directory extends Element {
  private List<Element> children;

  public Directory(String name, Element parent) {
    super(name, parent);
  }

  public void delete() {
    System.out.println("The directory “" + name + "” was deleted and it’s children were also deleted.");
    for (int i = 0; i < children.size(); i++) {
      Element child = children.get(i);
      child.delete();
    }
  }
}
