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
  }
}
