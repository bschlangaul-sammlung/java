package org.bschlangaul.entwurfsmuster.zustand.enwiki;

interface State {
  void writeName(StateContext context, String name);
}
