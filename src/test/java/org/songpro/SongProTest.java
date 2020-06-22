package org.songpro;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SongProTest {
  @Nested
  class Parse {
    @Test
    public void itParsesAttributes() {
      Song song = SongPro.parse(
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
      Song song = SongPro.parse(
          "!difficulty=Easy\n" +
              "!spotify_url=https://open.spotify.com/track/5zADxJhJEzuOstzcUtXlXv?si=SN6U1oveQ7KNfhtD2NHf9A\n"
      );

      assertThat(song.getCustom("difficulty")).isEqualTo("Easy");
      assertThat(song.getCustom("spotify_url")).isEqualTo("https://open.spotify.com/track/5zADxJhJEzuOstzcUtXlXv?si=SN6U1oveQ7KNfhtD2NHf9A");
    }

    @Test
    public void itParsesChordsAndLyrics() {
      Song song = SongPro.parse("[G]Don't go 'round tonight");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getChord()).isEqualTo("G");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("Don't go 'round tonight");
    }

    @Test
    public void itParsesLyricsBeforeChords() {
      Song song = SongPro.parse("It's [D]bound to take your life");

      assertThat(song.getSections().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().size()).isEqualTo(1);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().size()).isEqualTo(2);
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getChord()).isEqualTo("");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(0).getLyric()).isEqualTo("It's ");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getChord()).isEqualTo("D");
      assertThat(song.getSections().get(0).getLines().get(0).getParts().get(1).getLyric()).isEqualTo("bound to take your life");
    }
  }
}
