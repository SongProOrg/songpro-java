package org.songpro;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.songpro.models.Song;

import static org.assertj.core.api.Assertions.assertThat;

public class SongProTest {
  private Song song;

  @Nested
  class Parse {
    @Test
    public void itParsesAttributes() {
      song = SongPro.parse(
          "@title=Bad Moon Rising\n" +
              "@artist=Creedence Clearwater Revival\n" +
              "@capo=1st Fret\n" +
              "@key=C# Minor\n" +
              "@tempo=120\n" +
              "@year=1975\n" +
              "@album=Foo Bar Baz\n" +
              "@tuning=Eb Standard\n"
      );

      assertThat(song.getTitle()).isEqualTo("Bad Moon Rising");
      assertThat(song.getArtist()).isEqualTo("Creedence Clearwater Revival");
      assertThat(song.getCapo()).isEqualTo("1st Fret");
      assertThat(song.getKey()).isEqualTo("C# Minor");
      assertThat(song.getTempo()).isEqualTo("120");
      assertThat(song.getYear()).isEqualTo("1975");
      assertThat(song.getAlbum()).isEqualTo("Foo Bar Baz");
      assertThat(song.getTuning()).isEqualTo("Eb Standard");
    }

    @Test
    public void itParsesCustomAttributes() {
      song = SongPro.parse(
          "!difficulty=Easy\n" +
              "!spotify_url=https://open.spotify.com/track/5zADxJhJEzuOstzcUtXlXv?si=SN6U1oveQ7KNfhtD2NHf9A\n"
      );

      assertThat(song.getCustom("difficulty")).isEqualTo("Easy");
      assertThat(song.getCustom("spotify_url")).isEqualTo("https://open.spotify.com/track/5zADxJhJEzuOstzcUtXlXv?si=SN6U1oveQ7KNfhtD2NHf9A");
    }

    @Test
    public void itParsesSectionNames() {
      song = SongPro.parse("# Verse 1");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getName()).isEqualTo("Verse 1");
    }

    @Test
    public void itParsesMultipleSectionNames() {
      song = SongPro.parse(
          "# Verse 1\n" +
              "# Chorus\n"
      );

      assertThat(song.getSections().size()).isEqualTo(2);
      assertThat(song.getSections().get(0).getName()).isEqualTo("Verse 1");
      assertThat(song.getSections().get(1).getName()).isEqualTo("Chorus");
    }

    @Test
    public void itParsesLyrics() {
      song = SongPro.parse("I don't see! a bad, moon a-rising. (a-rising)");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("I don't see! a bad, moon a-rising. (a-rising)");
    }

    @Test
    public void itParsesSpecialCharacters() {
      song = SongPro.parse("singing sömething with Röck dots");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("singing sömething with Röck dots");
    }

    @Test
    public void itParsesChords() {
      song = SongPro.parse("[D] [D/F#] [C] [A7]");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(4);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getChord()).isEqualTo("D");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo(" ");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getChord()).isEqualTo("D/F#");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getLyric()).isEqualTo(" ");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(2).getChord()).isEqualTo("C");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(2).getLyric()).isEqualTo(" ");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(3).getChord()).isEqualTo("A7");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(3).getLyric()).isEqualTo("");
    }

    @Test
    public void itParsesChordsAndLyrics() {
      song = SongPro.parse("[G]Don't go 'round tonight");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getChord()).isEqualTo("G");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("Don't go 'round tonight");
    }

    @Test
    public void itParsesLyricsBeforeChords() {
      song = SongPro.parse("It's [D]bound to take your life");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(2);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getChord()).isEqualTo("");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("It's ");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getChord()).isEqualTo("D");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getLyric()).isEqualTo("bound to take your life");
    }

    @Test
    public void itParsesLyricsBeforeAndAfterChords() {
      song = SongPro.parse("It's a[D]bout a [E]boy");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(3);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getChord()).isEqualTo("");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("It's a");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getChord()).isEqualTo("D");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getLyric()).isEqualTo("bout a ");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(2).getChord()).isEqualTo("E");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(2).getLyric()).isEqualTo("boy");
    }
    
    @Test
    public void itParsesTablature() {
      song = SongPro.parse("# Riff\n|-3---5-|\n|---4---|\n");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).hasTablature()).isEqualTo(true);
      assertThat(song.getSections().get(0).getLines().get(0).getTablature()).isEqualTo("|-3---5-|");
      assertThat(song.getSections().get(0).getLines().get(1).hasTablature()).isEqualTo(true);
      assertThat(song.getSections().get(0).getLines().get(1).getTablature()).isEqualTo("|---4---|");
    }
    
    @Test
    public void itParsesComments() {
      song = SongPro.parse("# Comment\n> This is a comment.\n");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).hasComment()).isEqualTo(true);
      assertThat(song.getSections().get(0).getLines().get(0).getComment()).isEqualTo("This is a comment.");
    }
  }
}
