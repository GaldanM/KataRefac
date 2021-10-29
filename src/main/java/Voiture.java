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

  public Boolean Demarrer() throws Exception {
    final Integer HUILE_LEVEL_TOO_LOW_THRESHOLD = 20;

    try {
      this.pignonMoteur.Engrener();
      Boolean lancementOk = this.pignonMoteur.Enclencher();
      if (this.huileLevelMoteur < HUILE_LEVEL_TOO_LOW_THRESHOLD) {
        throw new Exception("Voiture impossible a dÃ©marrer, niveau d'huile trop faible");
      }
      return lancementOk;
    } catch (Exception exception) {
      throw new Exception("Le moteur n'a pas pu être démarré");
    }
  }

  public String DisplayLigne() {
    return "Cette voiture est une " + this.type;
  }

  public Boolean HasToitOuvrant() {
    return this.type.hasToitOuvrant();
  }

  public static Float GetNumberOfSeats(Ligne type) {
    return type.getNumberOfSeats();
  }

  public enum Ligne {
    QashqaiTekna(true, 5F),
    QashqaiVisia(true, 0F),
    QashqaiNConnecta(false, 4F),
    QashqaiAcenta(true, 4F),
    ScenicLigne1(false, 0F),
    ScenicLigne2(false, 0F),
    Peugeot208Ligne1(true, 0F),
    Peugeot208Ligne2(true, 0F);

    private final Boolean hasToitOuvrant;
    private final Float numberOfSeats;

    Ligne(Boolean hasToitOuvrant, Float numberOfSeats) {
      this.hasToitOuvrant = hasToitOuvrant;
      this.numberOfSeats = numberOfSeats;
    }

    public Boolean hasToitOuvrant() {
      return this.hasToitOuvrant;
    }

    public Float getNumberOfSeats() {
      return this.numberOfSeats;
    }
  }
}