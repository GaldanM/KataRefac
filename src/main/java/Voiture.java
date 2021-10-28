import java.util.List;

public class Voiture {
  public Long id;
  public String couleur;
  public Ligne type;
  public Long nombreDeKm;

  private final PignonMoteur pignonMoteur;
  private final SoupapeMoteur soupapeMoteur = new SoupapeMoteur();
  private final Integer huileLevelMoteur;

  private final DbContext dbContext;

  public Voiture(Long id, String couleur, Ligne type, Long nombreDeKm, DbContext dbContext, PignonMoteur pignonMoteur, Integer huileLevelMoteur) {
    this.id = id;
    this.couleur = couleur;
    this.type = type;
    this.nombreDeKm = nombreDeKm;
    this.dbContext = dbContext;
    this.pignonMoteur = pignonMoteur;
    this.huileLevelMoteur = huileLevelMoteur;
  }

  public void Save() {
    if (this.id == null) {
      this.id = (long) (this.dbContext.findAll().size() + 1);
      this.dbContext.add(new VoitureEntity(this));
    } else {
      this.dbContext.update(this.id, this.couleur, this.type);
    }
  }

  public Voiture Get(Long id) {
    VoitureEntity voitureEntity = this.dbContext.findById(id);
    return new Voiture(
        voitureEntity.id, voitureEntity.couleur, voitureEntity.type, voitureEntity.nombreDeKm,
        this.dbContext, this.pignonMoteur, 100
    );
  }

  public List<Voiture> GetAll() {
    return this.dbContext
        .findAll()
        .stream().map(voitureEntity ->
            new Voiture(
                voitureEntity.id, voitureEntity.couleur, voitureEntity.type, voitureEntity.nombreDeKm,
                this.dbContext, this.pignonMoteur, 100
            ))
        .toList();
  }

  public String GetCurrentModele() {
    return "Cette voiture est une " + this.type;
  }

  public Voiture GetVoitureAndUpdateKms() {
    Voiture voiture = this.Get(this.id);
    voiture.nombreDeKm = nombreDeKm++;
    this.Save();
    return voiture;
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

  enum Ligne {
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