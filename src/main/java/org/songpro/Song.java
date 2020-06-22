package org.songpro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Song {
  private String title;
  private String artist;
  private String capo;
  private String key;
  private String tempo;
  private String year;
  private String album;
  private String tuning;
  private Map<String, String> customAttributes = new HashMap<>();
  private List<Section> sections = new ArrayList<>();

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
    return customAttributes.get(key);
  }

  public void setCustomAttribute(String key, String value) {
    customAttributes.put(key, value);
  }

  public List<Section> getSections() {
    return this.sections;
  }
}
