package org.songpro.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Song {
  private String title;
  private String artist;
  private String capo;
  private String key;
  private String tempo;
  private String year;
  private String album;
  private String tuning;

  @JsonProperty("custom")
  private Map<String, String> custom;
  private List<Section> sections;

  public Song() {
    sections = new ArrayList<>();
    custom = new HashMap<>();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getCapo() {
    return capo;
  }

  public void setCapo(String capo) {
    this.capo = capo;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getTempo() {
    return tempo;
  }

  public void setTempo(String tempo) {
    this.tempo = tempo;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public String getTuning() {
    return tuning;
  }

  public void setTuning(String tuning) {
    this.tuning = tuning;
  }

  public String getCustom(String key) {
    return custom.get(key);
  }

  public void setCustom(String key, String value) {
    custom.put(key, value);
  }

  public List<Section> getSections() {
    return this.sections;
  }
}
