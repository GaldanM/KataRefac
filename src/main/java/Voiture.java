public class Voiture {
  public Long id;
  public String couleur;
  public Ligne type;
  public Long nombreDeKm;
  public Integer huileLevelMoteur;
  public PignonMoteur pignonMoteur;
  public SoupapeMoteur soupapeMoteur = new SoupapeMoteur();

  public Voiture(Long id, String couleur, Ligne type, Long nombreDeKm, Integer huileLevelMoteur, PignonMoteur pignonMoteur) {
    this.id = id;
    this.couleur = couleur;
    this.type = type;
    this.nombreDeKm = nombreDeKm;
    this.huileLevelMoteur = huileLevelMoteur;
    this.pignonMoteur = pignonMoteur;
  }

  public String GetCurrentModele() {
    return "Cette voiture est une " + this.type;
  }

  public Boolean HasToitOuvrant() {
    return switch (this.type) {
      case Peugeot208Ligne1, QashqaiTekna, QashqaiVisia, QashqaiAcenta, Peugeot208Ligne2 -> true;
      case QashqaiNConnecta, ScenicLigne1, ScenicLigne2 -> false;
    };
  }

  public Boolean Demarrer() throws Exception {
    try {
      this.pignonMoteur.Engrener();
      Boolean lancementOk = this.pignonMoteur.Enclencher();
      if (this.huileLevelMoteur < 20) {
        throw new Exception("Voiture impossible a dÃ©marrer, niveau d'huile trop faible");
      }
      return lancementOk;
    } catch (Exception exception) {
      throw new Exception("Le moteur n'a pas pu être démarré");
    }
  }

  public static Float GetNumberOfSeats(Ligne type) {
    if (type == Ligne.QashqaiAcenta) {
      return 4F;
    }
    if (type == Ligne.QashqaiNConnecta) {
      return 4F;
    }
    if (type == Ligne.QashqaiTekna) {
      return 5F;
    }
    return (float) 0;
  }

  public enum Ligne {
    QashqaiTekna,
    QashqaiVisia,
    QashqaiNConnecta,
    QashqaiAcenta,
    ScenicLigne1,
    ScenicLigne2,
    Peugeot208Ligne1,
    Peugeot208Ligne2
  }
}