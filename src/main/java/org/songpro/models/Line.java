package org.songpro.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Line {
  private List<Part> parts;
  private String comment;
  private String tablature;

  public List<Measure> getMeasures() {
    return measures;
  }

  public void setMeasures(List<Measure> measures) {
    this.measures = measures;
  }

  private List<Measure> measures;

  public Line() {
    this.parts = new ArrayList<>();
  }

  public List<Part> getParts() {
    return this.parts;
  }

  public void setParts(List<Part> parts) {
    this.parts = parts;
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

  public boolean hasMeasures() {
    return !measures.isEmpty();
  }
}
