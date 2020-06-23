package org.songpro;

import java.util.ArrayList;
import java.util.List;

public class Section {
  private String name;
  private List<Line> lines = new ArrayList<>();

  public List<Line> getLines() {
    return this.lines;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
