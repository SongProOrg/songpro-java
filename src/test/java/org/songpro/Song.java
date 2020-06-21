package org.songpro;

import jdk.nashorn.internal.objects.NativeInt8Array;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
}
