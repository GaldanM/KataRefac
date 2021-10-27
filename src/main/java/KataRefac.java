import java.util.List;

public class Voiture {
  public Long Id = null;
  public String Couleur;
  public Ligne Type;
  public Long NombreDeKm;

  private PignonMoteur pignonMoteur = new PignonMoteur();
  private SoupapeMoteur soupapeMoteur = new SoupapeMoteur();
  private Integer huileLevelMoteur = 100;

  DbContext dbContext = new DbContext("myConnectionString");

  public Voiture(String couleur, Ligne type, Long nombreDeKm) {
    this.Couleur = couleur;
    this.Type = type;
    this.NombreDeKm = nombreDeKm;
  }

  public Voiture(Long id, String couleur, Ligne type, Long nombreDeKm) {
    this.Id = id;
    this.Couleur = couleur;
    this.Type = type;
    this.NombreDeKm = nombreDeKm;
  }

  public void Save() {
    if (this.Id == null) {
      this.dbContext.Voitures.Add(new VoitureEntity(this));
    } else {
      this.dbContext.Voitures.Update(this.Id, this.Couleur, this.Type);
    }

    this.dbContext.SaveChanges();
  }

  public Voiture Get(Long id) {
    return this.dbContext.Voitures
        .Where(v => v.Id == id)
        .Select(v => new Voiture(v.Id, v.Couleur, v.Type, v.NombreKilometres));
  }

  public List<Voiture> GetAll() {
    List<Voiture> allVoitures = this.dbContext
        .Where(v => v.IsDeleted == false)
        .Select(v => new Voiture(v.Id, v.Couleur, v.Type, v.NombreKilometres));

    return allVoitures;
  }

  public String GetCurrentModele() {
    return "Cette voiture est une " + this.Type;
  }

  public Voiture GetVoitureAndUpdateKms() {
    Voiture voiture = this.Get(this.Id);
    voiture.NombreDeKm = NombreDeKm++;
    this.Save();
    return voiture;
  }

  public Boolean HasToitOuvrant() {
    switch (this.Type) {
      case Ligne.Peugeot208Ligne1:
        return true;
      case Ligne.QashqaiTekna:
        return true;
      case Ligne.QashqaiVisia:
        return true;
      case Ligne.QashqaiNConnecta:
        return false;
      case Ligne.QashqaiAcenta:
        return true;
      case Ligne.ScenicLigne1:
        return false;
      case Ligne.ScenicLigne2:
        return false;
      case Ligne.Peugeot208Ligne2:
        return true;
      default:
        return false;
    }
  }

  public Boolean Demarrer() {
    try
    {
      this.pignonMoteur.Engrener();
      var lancementOk = this.pignonMoteur.Enclencher();
      if (this.huileLevelMoteur < 20) {
        throw new Exception("Voiture impossible a dÃ©marrer, niveau d'huile trop faible");
      }
      return lancementOk;
    }
    catch (Exception exception) {
      throw new Exception("Le moteur n'a pas pu être démarré");
    }
  }

  public static Float GetNumberOfSeats(Ligne type) {
    if (type == Ligne.QashqaiAcenta) {
      return 4;
    }
    if (type == Ligne.QashqaiNConnecta) {
      return 4;
    }
    if (type == Ligne.QashqaiTekna) {
      return 5;
    }
    return 0;
  }
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