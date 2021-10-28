public class VoitureEntity {
  public Long id;
  public String couleur;
  public Voiture.Ligne type;
  public Long nombreDeKm;
  public Boolean isDeleted;

  public VoitureEntity(Voiture voiture) {
    this.id = voiture.id;
    this.couleur = voiture.couleur;
    this.type = voiture.type;
    this.nombreDeKm = voiture.nombreDeKm;
    this.isDeleted = false;
  }
}
