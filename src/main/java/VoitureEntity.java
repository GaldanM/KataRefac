public class VoitureEntity {
  public Long id;
  public String couleur;
  public Voiture.Ligne type;
  public Long nombreDeKm;
  public Boolean isDeleted;
  public Integer huileLevelMoteur;

  public VoitureEntity(Long id, String couleur, Voiture.Ligne type, Long nombreDeKm, Integer huileLevelMoteur) {
    this.id = id;
    this.couleur = couleur;
    this.type = type;
    this.nombreDeKm = nombreDeKm;
    this.huileLevelMoteur = huileLevelMoteur;
    this.isDeleted = false;
  }
}
