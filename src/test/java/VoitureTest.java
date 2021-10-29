import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class VoitureTest {
  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void getCurrentModele(Voiture.Ligne ligne) {
    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L, 100, null);

    assertThat(voiture.GetCurrentModele()).isEqualTo("Cette voiture est une " + ligne);
  }

  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void hasToitOuvrant(Voiture.Ligne ligne) {
    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L, 100, null);

    switch (voiture.type) {
      case QashqaiTekna, QashqaiVisia, QashqaiAcenta,
          Peugeot208Ligne1, Peugeot208Ligne2 -> assertThat(voiture.HasToitOuvrant()).isEqualTo(true);
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
    VoitureRepository voitureRepository = new VoitureRepositoryInMemory("");
    PignonMoteurRepository pignonMoteurRepository = new PignonMoteurRepositoryInMemory("");
    VoitureService voitureService = new VoitureService(voitureRepository, pignonMoteurRepository);

    PignonMoteur pignonMoteur = new PignonMoteur(true);
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1,
        0L, 100, pignonMoteur
    );
    voitureService.Save(voiture);

    assertThat(voitureService.GetAll().size()).isEqualTo(1);
  }

  @Test
  void updateVoiture() {
    VoitureRepository voitureRepository = new VoitureRepositoryInMemory("");
    PignonMoteurRepository pignonMoteurRepository = new PignonMoteurRepositoryInMemory("");
    VoitureService voitureService = new VoitureService(voitureRepository, pignonMoteurRepository);

    PignonMoteur pignonMoteur = new PignonMoteur(true);
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1,
        0L, 100, pignonMoteur
    );
    voitureService.Save(voiture);
    Voiture voitureToUpdate = voitureService.GetAll().get(0);
    voitureToUpdate.couleur = "Rouge";
    voitureService.Save(voitureToUpdate);

    assertThat(voitureService.Get(1L).couleur).isEqualTo("Rouge");
    assertThat(voitureService.GetAll().size()).isEqualTo(1);
  }

  @Test
  void shouldDemarre() {
    PignonMoteur pignonMoteur = new PignonMoteur(true);
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1,
        0L, 100, pignonMoteur
    );

    try {
      assertThat(voiture.Demarrer()).isTrue();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldNotDemarreWhenPignonDoesNotEnclenche() {
    PignonMoteur pignonMoteur = new PignonMoteur(false);
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1,
        0L, 100, pignonMoteur
    );

    try {
      assertThat(voiture.Demarrer()).isFalse();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void shouldNotDemarreWhenHuileLevelTooLow() {
    PignonMoteur pignonMoteur = new PignonMoteur(true);
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1,
        0L, 10, pignonMoteur
    );

    assertThatThrownBy(voiture::Demarrer)
        .isInstanceOf(Exception.class)
        .hasMessage("Le moteur n'a pas pu être démarré");
  }

  @Test
  void GetVoitureAndUpdateKms() {
    VoitureRepository voitureRepository = new VoitureRepositoryInMemory("");
    PignonMoteurRepository pignonMoteurRepository = new PignonMoteurRepositoryInMemory("");
    VoitureService voitureService = new VoitureService(voitureRepository, pignonMoteurRepository);

    PignonMoteur pignonMoteur = new PignonMoteur(true);
    Voiture voiture = new Voiture(
        null, "Bleue", Voiture.Ligne.Peugeot208Ligne1, 0L,
        100, pignonMoteur);
    voitureService.Save(voiture);

    Voiture sameVoiture = voitureService.GetVoitureAndUpdateKms(1L);

    assertThat(sameVoiture.id).isEqualTo(1);
    assertThat(sameVoiture.nombreDeKm).isEqualTo(1);
  }
}
