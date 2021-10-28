import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class VoitureTest {
  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void getCurrentModele(Voiture.Ligne ligne) {
    DbContext dbContext = createDbContext();
    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L, dbContext, null, 100);

    assertThat(voiture.GetCurrentModele()).isEqualTo("Cette voiture est une " + ligne);
  }

  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void hasToitOuvrant(Voiture.Ligne ligne) {
    DbContext dbContext = createDbContext();

    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L, dbContext, null, 100);

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

    Voiture voiture = new Voiture(null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L, dbContext, null, 100);
    voiture.Save();

    assertThat(voiture.GetAll().size()).isEqualTo(1);
  }

  @Test
  void updateVoiture() {
    DbContext dbContext = createDbContext();
    Voiture voiture = new Voiture(null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L, dbContext, null, 100);
    voiture.Save();
    Voiture voitureToUpdate = new Voiture(1L, "Rouge", voiture.type, voiture.nombreDeKm, dbContext, null, 100);
    voitureToUpdate.Save();

    assertThat(voiture.Get(1L).couleur).isEqualTo("Rouge");
    assertThat(voiture.GetAll().size()).isEqualTo(1);
  }

  @Test
  void shouldDemarre() {
    PignonMoteur pignonMoteur = new PignonMoteurWorking();
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L,
        null, pignonMoteur, 100);

    try {
      assertThat(voiture.Demarrer()).isTrue();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldNotDemarreWhenPignonDoesNotEnclenche() {
    PignonMoteur pignonMoteur = new PignonMoteurNotWorking();
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L,
        null, pignonMoteur, 100);

    try {
      assertThat(voiture.Demarrer()).isFalse();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldNotDemarreWhenHuileLevelTooLow() {
    PignonMoteur pignonMoteur = new PignonMoteurWorking();
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L,
        null, pignonMoteur, 10);

    assertThatThrownBy(voiture::Demarrer)
        .isInstanceOf(Exception.class)
        .hasMessage("Le moteur n'a pas pu être démarré");
  }

  @Test
  void GetVoitureAndUpdateKms() {
    DbContext dbContext = createDbContext();

    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L,
        dbContext, null, 100);
    voiture.Save();

    Voiture sameVoiture = voiture.GetVoitureAndUpdateKms();

    assertThat(sameVoiture.id).isEqualTo(1);
    assertThat(sameVoiture.nombreDeKm).isEqualTo(1);
  }

  private DbContext createDbContext() {
    return new DbContextInMemory("");
  }
}
