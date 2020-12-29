package org.bschlangaul.examen.examen_66116_2019_09.file_system;

public class File extends Element {

  public File(String name, Element parent) {
    super(name, parent);
    parent.addChild(this);
  }

  public void delete() {
    System.out.println("The File “" + name + "” was deleted.");
  }

  public boolean isDirectory() {
    return false;
  }

  /**
   * Eine Datei kann keine Kinder haben. Deshalb eine Methode mit leerem
   * Methodenrumpf.
   */
  public void addChild(Element child) {
  }

}
