package org.songpro.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Section {
  private String name;
  private List<Line> lines;

  public Section() {
  }

  public Section(String name) {
    this.name = name;
    this.lines = new ArrayList<>();
  }

  public List<Line> getLines() {
    return this.lines;
  }

  public void setLines(List<Line> lines) {
    this.lines = lines;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
