package org.songpro;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SongPro {
  private final static Pattern ATTRIBUTE_PATTERN = Pattern.compile("@(\\w*)=([^%]*)");
  private final static Pattern CUSTOM_ATTRIBUTE_PATTERN = Pattern.compile("!(\\w*)=([^%]*)");

  public static Song parse(String text) {
    Song song = new Song();

    List<String> lines = Arrays.asList(text.split("\n"));

    lines.forEach((line) -> {
      if (line.startsWith("@")) {
        processAttribute(song, line);
      } else if (line.startsWith("!")) {
        processCustomAttribute(song, line);
      }
    });

    return song;
  }

  private static void processAttribute(Song song, String line) {
    Matcher matcher = ATTRIBUTE_PATTERN.matcher(line);
    if (matcher.matches()) {
      String key = matcher.group(1);
      String value = matcher.group(2).replace("\n", "");

      // TODO: Can we do this dynamically?
      switch (key) {
        case "title":
          song.setTitle(value);
          break;
        case "artist":
          song.setArtist(value);
          break;
        case "capo":
          song.setCapo(value);
          break;
        case "key":
          song.setKey(value);
          break;
        case "tempo":
          song.setTempo(value);
          break;
        case "year":
          song.setYear(value);
          break;
        case "album":
          song.setAlbum(value);
          break;
        case "tuning":
          song.setTuning(value);
          break;
      }
    }
  }

  private static void processCustomAttribute(Song song, String line) {
    Matcher matcher = CUSTOM_ATTRIBUTE_PATTERN.matcher(line);
    if (matcher.matches()) {
      String key = matcher.group(1);
      String value = matcher.group(2).replace("\n", "");

      song.setCustomAttribute(key, value);
    }
  }

}
