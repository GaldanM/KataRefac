import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class VoitureTest {
  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void getCurrentModele(Voiture.Ligne ligne) {
    DbContext dbContext = createDbContext();
    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L, dbContext);

    assertThat(voiture.GetCurrentModele()).isEqualTo("Cette voiture est une " + ligne);
  }

  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void hasToitOuvrant(Voiture.Ligne ligne) {
    DbContext dbContext = createDbContext();

    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L, dbContext);

    switch (voiture.type) {
      case Peugeot208Ligne1, QashqaiTekna, QashqaiVisia, QashqaiAcenta, Peugeot208Ligne2 -> assertThat(voiture.HasToitOuvrant()).isEqualTo(true);
      case QashqaiNConnecta, ScenicLigne1, ScenicLigne2 -> assertThat(voiture.HasToitOuvrant()).isEqualTo(false);
    }
  }

  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void GetNumberOfSeats(Voiture.Ligne ligne) {
    switch (ligne) {
      case QashqaiAcenta, QashqaiNConnecta -> assertThat(Voiture.GetNumberOfSeats(ligne)).isEqualTo(4F);
      case QashqaiTekna -> assertThat(Voiture.GetNumberOfSeats(ligne)).isEqualTo(5F);
      default -> assertThat(Voiture.GetNumberOfSeats(ligne)).isEqualTo(0F);
    }
  }

  @Test
  void saveVoiture() {
    DbContext dbContext = createDbContext();

    Voiture voiture = new Voiture(null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L, dbContext);
    voiture.Save();

    assertThat(voiture.GetAll().size()).isEqualTo(1);
  }

  @Test
  void updateVoiture() {
    DbContext dbContext = createDbContext();
    Voiture voiture = new Voiture(null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L, dbContext);
    voiture.Save();
    Voiture voitureToUpdate = new Voiture(1L, "Rouge", voiture.type, voiture.nombreDeKm, dbContext);
    voitureToUpdate.Save();

    assertThat(voiture.Get(1L).couleur).isEqualTo("Rouge");
    assertThat(voiture.GetAll().size()).isEqualTo(1);
  }

  @Test
  void shouldDemarre() {
    Voiture voiture = new Voiture(null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L, null);

    assertThatCode(voiture::Demarrer).doesNotThrowAnyException();
  }

  private DbContext createDbContext() {
    return new DbContextInMemory("");
  }
}
