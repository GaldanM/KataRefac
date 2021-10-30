public class VoitureEntity {
  private final Long id;
  private final String couleur;
  private final Voiture.Ligne type;
  private final Integer huileLevelMoteur;
  private final Boolean isDeleted;

  private Long nombreDeKm;

  public VoitureEntity(Long id, String couleur, Voiture.Ligne type, Long nombreDeKm, Integer huileLevelMoteur) {
    this.id = id;
    this.couleur = couleur;
    this.type = type;
    this.nombreDeKm = nombreDeKm;
    this.huileLevelMoteur = huileLevelMoteur;
    this.isDeleted = false;
  }

  public Long getId() {
    return id;
  }

  public String getCouleur() {
    return couleur;
  }

  public Voiture.Ligne getType() {
    return type;
  }

  public Long getNombreDeKm() {
    return nombreDeKm;
  }

  public Integer getHuileLevelMoteur() {
    return huileLevelMoteur;
  }

  public Boolean getIsDeleted() {
    return isDeleted;
  }

  public void setNombreDeKm(Long nombreDeKm) {
    this.nombreDeKm = nombreDeKm;
  }
}
