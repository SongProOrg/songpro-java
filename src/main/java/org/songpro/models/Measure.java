package org.songpro.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Measure {
  private List<String> chords;

  public List<String> getChords() {
    return chords;
  }

  public void setChords(List<String> chords) {
    this.chords = chords;
  }
}
