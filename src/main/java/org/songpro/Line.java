package org.songpro;

import java.util.ArrayList;
import java.util.List;

public class Line {
  private List<Part> parts;
  private String comment;
  private String tablature;

  public Line() {
    this.parts = new ArrayList<>();
  }

  public List<Part> getParts() {
    return this.parts;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public boolean hasComment() {
    return comment != null;
  }

  public void setTablature(String tablature) {
    this.tablature = tablature;
  }

  public String getTablature() {
    return tablature;
  }

  public boolean hasTablature() {
    return tablature != null;
  }
}
