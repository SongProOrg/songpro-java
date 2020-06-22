package org.songpro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class SongPro {
  private final static Pattern ATTRIBUTE_PATTERN = Pattern.compile("@(\\w*)=([^%]*)");
  private final static Pattern CUSTOM_ATTRIBUTE_PATTERN = Pattern.compile("!(\\w*)=([^%]*)");
  private final static Pattern CHORDS_AND_LYRICS_REGEX = Pattern.compile("(\\[[\\w#b/]+])?([^\\[]*)", CASE_INSENSITIVE);

  public static Song parse(String text) {
    Song song = new Song();
    Section currentSection = null;

    List<String> lines = Arrays.asList(text.split("\n"));

    lines.forEach((line) -> {
      if (line.startsWith("@")) {
        processAttribute(song, line);
      } else if (line.startsWith("!")) {
        processCustomAttribute(song, line);
      } else {
        processLyricsAndChords(song, currentSection, text);
      }
    });

    return song;
  }

  private static void processLyricsAndChords(Song song, Section currentSection, String text) {
    if (text.isEmpty()) {
      return;
    }

    if (currentSection == null) {
      currentSection = new Section();
      song.getSections().add(currentSection);
    }

    Line line = new Line();

    Matcher matcher = CHORDS_AND_LYRICS_REGEX.matcher(text);

    while (matcher.find()) {
      if (matcher.groupCount() == 2) {
        Part part = new Part();

        if (matcher.group(1) != null) {
          part.setChord(matcher.group(1).trim().replace("[", "").replace("]", ""));
        } else {
          part.setChord("");
        }

        if (!matcher.group(2).equals("")) {
          part.setLyric(matcher.group(2));
        } else {
          part.setLyric("");
        }

        if (!(part.getChord().equals("") && part.getLyric().equals(""))) {
          line.getParts().add(part);
        }
      }
    }
//    captures.each_slice(2) do |pair|
//        part = Part.new
//        chord = pair[0]&.strip || ''
//        part.chord = chord.delete('[').delete(']')
//        part.lyric = pair[1] || ''
//
//    line.parts << part unless (part.chord == '') && (part.lyric == '')

    currentSection.getLines().add(line);
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
