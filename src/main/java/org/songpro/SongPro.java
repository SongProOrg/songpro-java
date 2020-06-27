package org.songpro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class SongPro {
  private final static Pattern SECTION_REGEX = Pattern.compile("#\\s*([^$]*)");
  private final static Pattern ATTRIBUTE_PATTERN = Pattern.compile("@(\\w*)=([^%]*)");
  private final static Pattern CUSTOM_ATTRIBUTE_PATTERN = Pattern.compile("!(\\w*)=([^%]*)");
  private final static Pattern CHORDS_AND_LYRICS_REGEX = Pattern.compile("(\\[[\\w#b/]+])?([^\\[]*)", CASE_INSENSITIVE);
  private final static Pattern COMMENT_REGEX = Pattern.compile(">\\s*([^$]*)");

  public static Song parse(String lines) {
    Song song = new Song();
    Section currentSection = null;

    for (String text : lines.split("\n")) {
      if (text.startsWith("@")) {
        processAttribute(song, text);
      } else if (text.startsWith("!")) {
        processCustomAttribute(song, text);
      } else if (text.startsWith("#")) {
        currentSection = processSection(song, text);
      } else {
        processLyricsAndChords(song, currentSection, text);
      }
    }

    return song;
  }

  private static Section processSection(Song song, String text) {
    Matcher matcher = SECTION_REGEX.matcher(text);

    Section currentSection = new Section("");

    if (matcher.matches()) {
      String name = matcher.group(1).trim();
      currentSection.setName(name);
      song.getSections().add(currentSection);
    }

    return currentSection;
  }

  private static void processLyricsAndChords(Song song, Section currentSection, String text) {
    if (text.isEmpty()) {
      return;
    }

    if (currentSection == null) {
      currentSection = new Section("");
      song.getSections().add(currentSection);
    }

    Line line = new Line();

    if (text.startsWith("|-")) {
      line.setTablature(text);
    } else if (text.startsWith(">")) {
      Matcher matcher = COMMENT_REGEX.matcher(text);

      if (matcher.matches()) {
        String comment = matcher.group(1).trim();
        line.setComment(comment);
      }
    } else {
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
    }

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
