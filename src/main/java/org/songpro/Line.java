package org.songpro;

import java.util.ArrayList;
import java.util.List;

public class Line {
  private List<Part> parts = new ArrayList<>();
  private String comment;
  private String tablature;

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
