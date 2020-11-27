package org.bschlangaul.entwurfsmuster.zustand.enwiki;

class LowerCaseState implements State {
  @Override
  public void writeName(StateContext context, String name) {
    System.out.println(name.toLowerCase());
    context.setState(new MultipleUpperCaseState());
  }
}
