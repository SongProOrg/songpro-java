package org.songpro.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Part {
  private String chord;
  private String lyric;

  public String getChord() {
    return chord;
  }

  public String getLyric() {
    return lyric;
  }

  public void setChord(String chord) {
    this.chord = chord;
  }

  public void setLyric(String lyric) {
    this.lyric = lyric;
  }
}
