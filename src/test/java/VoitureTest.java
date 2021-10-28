import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.*;

public class VoitureTest {
  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void getCurrentModele(Voiture.Ligne ligne) {
    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L);

    assertThat(voiture.GetCurrentModele()).isEqualTo("Cette voiture est une " + ligne);
  }

  @ParameterizedTest
  @EnumSource(Voiture.Ligne.class)
  void hasToitOuvrant(Voiture.Ligne ligne) {
    Voiture voiture = new Voiture(null, "Bleue", ligne, 0L);

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
}
